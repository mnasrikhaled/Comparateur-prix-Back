package com.Pfe.Comparateur.Service;

import com.Pfe.Comparateur.Dto.ProductDto;
import com.Pfe.Comparateur.Models.Product;
import com.Pfe.Comparateur.Models.ProductC;
import com.Pfe.Comparateur.Models.ProductWithPriceResponse;
import com.Pfe.Comparateur.Models.WishlistItem;
import com.Pfe.Comparateur.Repository.CategoryRepository;
import com.Pfe.Comparateur.Repository.ProductCRepository;
import com.Pfe.Comparateur.Repository.ProductRepository;
import com.Pfe.Comparateur.Repository.WishListItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCRepository productCRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private WishListItemRepository wishListItemRepository;

    // Méthode pour récupérer tous les produits
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Méthode pour récupérer un produit par son ID
    public Optional<Product> getProductById(String id) {
        return productRepository.findById(id);
    }

    public List<ProductWithPriceResponse> getProductWithPricesById(String _id) {
        List<ProductWithPriceResponse> response = new ArrayList<>();

        // Récupérer le produit par son ID
        Optional<ProductC> productOptional = productCRepository.findById(_id);

        // Vérifier si le produit existe
        if (productOptional.isPresent()) {
            ProductC productC = productOptional.get();

            // Ajouter le produit Mytek par défaut
            if (productC.getMytekProduct() != null) {
                response.add(new ProductWithPriceResponse(
                        productC.getMytekProduct().getProductName(),
                        productC.getProductPrices().getMytekPrice(),
                        productC.getMytekProduct().getLink(),
                        productC.getMytekProduct().getBrandImage(),
                        productC.getMytekProduct().getImage(),
                        productC.getMytekProduct().getDescription(),
                        productC.getMytekProduct().getMarchand()

                ));
            }

            // Ajouter le produit Tunisianet s'il est disponible
            if (productC.getBestTunisianetProduct() != null) {
                response.add(new ProductWithPriceResponse(
                        productC.getBestTunisianetProduct().getProductName(),
                        productC.getProductPrices().getTunisianetPrice(),
                        productC.getBestTunisianetProduct().getLink(),
                        productC.getBestTunisianetProduct().getBrandImage(),
                        productC.getBestTunisianetProduct().getImage(),
                        productC.getBestTunisianetProduct().getDescription(),
                        productC.getBestTunisianetProduct().getMarchand()
                ));
            }

            // Ajouter le produit Zoom TN s'il est disponible
            if (productC.getBestZoomTNProduct() != null) {
                response.add(new ProductWithPriceResponse(
                        productC.getBestZoomTNProduct().getProductName(),
                        productC.getProductPrices().getZoomTNPrice(),
                        productC.getBestZoomTNProduct().getLink(),
                        productC.getBestZoomTNProduct().getBrandImage(),
                        productC.getBestZoomTNProduct().getImage(),
                        productC.getBestZoomTNProduct().getDescription(),
                        productC.getBestZoomTNProduct().getMarchand()

                ));
            }
        }

        // Trier les produits par prix minimum du plus bas au plus haut
        response.sort(Comparator.comparingInt(ProductWithPriceResponse::getPrice));

        return response;
    }

    public List<ProductDto> findByTheme(String theme) {
        Query query = new Query();
        query.addCriteria(Criteria.where("Theme").is(theme));

        List<Product> products = mongoOperations.find(query, Product.class);
        return products.stream()
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getCategory(),
                        product.getProductName(),
                        product.getImage(),
                        product.getDescription(),
                        product.getPrice()))
                .collect(Collectors.toList());
    }

    public List<ProductDto> findByCategory(String Category) {
        Query query = new Query();
        query.addCriteria(Criteria.where("Category").is(Category));

        List<Product> products = mongoOperations.find(query, Product.class);
        return products.stream()
                .map(product -> new ProductDto(
                        product.getId(),
                        product.getTheme(),
                        product.getProductName(),
                        product.getImage(),
                        product.getDescription(),
                        product.getPrice()))
                .collect(Collectors.toList());
    }
//    public List<ProductDto> getAllCategory() {
//        return  categoryRepository.findAll();
//    }
}