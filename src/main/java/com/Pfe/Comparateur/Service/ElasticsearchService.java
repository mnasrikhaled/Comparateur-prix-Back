package com.Pfe.Comparateur.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ElasticsearchService {
    @Value("${elasticsearch.host}")
    private String elasticsearchHost;

    @Value("${elasticsearch.port}")
    private int elasticsearchPort;

    @Value("${elasticsearch.username}")
    private String elasticsearchUsername;

    @Value("${elasticsearch.password}")
    private String elasticsearchPassword;

    public List<Map<String, String>> searchProducts(String prefix) throws IOException {
        RestClientBuilder builder = RestClient.builder(new HttpHost(elasticsearchHost, elasticsearchPort))
                .setHttpClientConfigCallback(httpClientBuilder -> httpClientBuilder.setDefaultCredentialsProvider(
                        new BasicCredentialsProvider() {{
                            setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(elasticsearchUsername, elasticsearchPassword));
                        }}));
        try (RestClient restClient = builder.build()) {
            Request request = new Request("GET", "/auth_db.mytek/_search");
            String query = "{\n" +
                    "  \"size\": 20,\n" +
                    "  \"query\": {\n" +
                    "    \"match_phrase\": {\n" +
                    "      \"Product Name\": \"" + prefix + "\"\n" +
                    "    }\n" +
                    "  }\n" +
                    "}";
            request.setJsonEntity(query);

            Response response = restClient.performRequest(request);
            String responseBody = EntityUtils.toString(response.getEntity());
            return parseProducts(responseBody);
        }
    }

    private List<Map<String, String>> parseProducts(String responseBody) throws IOException {
        List<Map<String, String>> products = new ArrayList<>();
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode responseNode = objectMapper.readTree(responseBody);
        JsonNode hitsNode = responseNode.path("hits").path("hits");
        for (JsonNode hit : hitsNode) {
            JsonNode sourceNode = hit.path("_source");
            String id = hit.path("_id").asText(); // Récupérer l'ID
            String productName = sourceNode.path("Product Name").asText();
            String image = sourceNode.path("Image").asText();
            String link = sourceNode.path("Link").asText();
            Map<String, String> product = new HashMap<>();
            product.put("id", id); // Ajouter l'ID à la liste de produits
            product.put("productName", productName);
            product.put("link", link);
            product.put("image",image);
            products.add(product);
        }
        return products;
    }
}
