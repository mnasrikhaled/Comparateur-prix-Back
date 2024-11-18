package com.Pfe.Comparateur.Models;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "users_alert")
@Data
public class User_alert {

    @Setter
    @Getter
    private User user;

    private Product products;

    @Size(max = 20)
    private double budget;

    private String Price;


    public User_alert() {
    }

    public User_alert(double budget,String Price) {
        this.budget = budget;
        this.Price=Price;
    }

    public  void setProduct(Product products) {
        this.products=products;
    }


    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

//    public String getId() {

}
