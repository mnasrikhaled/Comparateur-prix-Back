package com.Pfe.Comparateur.Controller;


import com.Pfe.Comparateur.Service.ElasticsearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ela")
public class ApiController {

    @Autowired
    private ElasticsearchService elasticsearchService;


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/search")
    public List<Map<String, String>> searchProducts(@RequestParam String prefix) {
        try {
            return elasticsearchService.searchProducts(prefix);
        } catch (IOException e) {
            // Gérer les erreurs d'Elasticsearch ici
            e.printStackTrace();
            return null; // Ou renvoyer une réponse d'erreur appropriée
        }
    }
}