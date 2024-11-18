package com.Pfe.Comparateur.Models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
@Document(collection = "mytek")
public class Product {
    @Id
    private String id;
    @Field("Product Name")
    private String productName;
    @Field("Price")
    private String price;
    @Field("Old Price")
    private String oldPrice;
    @Field("Availability")
    private String availability;
    @Field("Image")
    private String image;
    @Field("Brand Image")
    private String brandImage;
    @Field("Link")
    private String link;
    @Field("Description")
    private String description;
    @Field("Category")
    private String Category;
    @Field("Theme")
    private String Theme;
    @Field("Marchand")
    private String Marchand;

    public String getMarchand() {
        return Marchand;
    }

    public void setMarchand(String marchand) {
        Marchand = marchand;
    }

    public String getTheme() {
        return Theme;
    }

    public void setTheme(String theme) {
        Theme = theme;
    }
// Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getBrandImage() {
        return brandImage;
    }

    public void setBrandImage(String brandImage) {
        this.brandImage = brandImage;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
