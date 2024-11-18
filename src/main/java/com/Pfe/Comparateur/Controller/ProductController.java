package com.Pfe.Comparateur.Controller;


import com.Pfe.Comparateur.Dto.ProductDto;
import com.Pfe.Comparateur.Models.Product;
import com.Pfe.Comparateur.Models.ProductWithPriceResponse;
import com.Pfe.Comparateur.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    // Endpoint pour récupérer tous les produits
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // Endpoint pour récupérer un produit par son ID
    @GetMapping("/id/{id}")
    public Optional<Product> getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }
    @GetMapping("/product-with-prices/{id}")
    public List<ProductWithPriceResponse> getProductWithPricesById(@PathVariable String id) {
        return productService.getProductWithPricesById(id);
    }
    @GetMapping("/theme/{theme}")
    public List<ProductDto> getProductsByTheme(@PathVariable String theme) {
        return productService.findByTheme(theme);
    }

    @GetMapping("/category/{Category}")
    public List<ProductDto> getProductsByCategory(@PathVariable String Category) {
        return productService.findByCategory(Category);
    }


}

