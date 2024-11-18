package com.Pfe.Comparateur.Dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mytek")

@Data
public class ProductDto {
//    private Product product;
    @Id
    private String id;
    private String Category;
    private  String ProductName;
    private  String Image;
    private  String Description;
    private  String Price;


    public ProductDto(String id,String Category, String ProductName, String Image, String Description, String Price) {
        this.id=id;
        this.Category = Category;
        this.ProductName = ProductName;
        this.Image = Image;
        this.Description = Description;
        this.Price = Price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getProduct_Name() {
        return ProductName;
    }

    public void setProduct_Name(String Product_Name) {
        this.ProductName = Product_Name;
    }
    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
