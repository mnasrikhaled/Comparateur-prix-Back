package com.Pfe.Comparateur.Models;

import org.springframework.data.mongodb.core.mapping.Field;

public class ProductWithPriceResponse {
    @Field("product Name")
    private String productName;
    private int price;
    private String link;
    @Field("Brand Image")
    private String brandImage;
    private String image;
    private String description;
    private String Marchand;



    // Constructeur
    public ProductWithPriceResponse(String productName, int price, String link, String brandImage, String image, String description,String Marchand) {
        this.productName = productName;
        this.price = price;
        this.link = link;
        this.brandImage = brandImage;
        this.image = image;
        this.description = description;
        this.Marchand=Marchand;
    }

    // Getters et Setters
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getBrandImage() {
        return brandImage;
    }

    public void setBrandImage(String brandImage) {
        this.brandImage = brandImage;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMarchand() {
        return Marchand;
    }

    public void setMarchand(String marchand) {
        Marchand = marchand;
    }
}
