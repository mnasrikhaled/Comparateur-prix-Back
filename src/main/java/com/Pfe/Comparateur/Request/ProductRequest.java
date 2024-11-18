package com.Pfe.Comparateur.Request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class ProductRequest {

    @NotBlank
        @Size(max = 20)
        private String Category;

    @NotBlank
        @Size(max = 50)
        private String Product_Name;

    @NotBlank
        @Size(max = 30)
        private String Price;
    @NotBlank
        @Size(max = 30)
        private String Old_Price;
    @NotBlank
        @Size(max = 30)
        private String Availability;

    @NotBlank
        @Size(max = 300)
        private String Description;

    @NotBlank
        @Size(max = 300)
        private String Image;

    @NotBlank
        @Size(max = 300)

    @NotBlank
        private String Link;

    @NotBlank
        private String Theme;
    @NotBlank
        private String Brand_Image;


        public String getCategory() {
            return Category;
        }

        public void setCategory(String Category) {
            this.Category = Category;
        }

        public String getProduct_Name() {
            return Product_Name;
        }

        public void setProduct_Name(String Product_Name) {
            this.Product_Name = Product_Name;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public String getOldprice() {
            return Old_Price;
        }

        public void setOldprice(String Old_Price) {
            this.Old_Price = Old_Price;
        }

        public String getAvailability() {
            return Availability;
        }

        public void setAvailability(String Availability) {
            this.Availability = Availability;
        }

        public String getDescription() {
            return Description;
        }

        public void setDescription(String Description) {
            this.Description = Description;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String Image) {
            this.Image = Image;
        }

        public String getLink() {
            return Link;
        }

        public void setLink(String Link) {
            this.Link = Link;
        }

        public String getTheme() {
            return Theme;
        }

        public void setTheme(String theme) {
            Theme = theme;
        }

        public String getBrand_Image() {
            return Brand_Image;
        }

        public void setBrand_Image(String brand_Image) {
            Brand_Image = brand_Image;
        }
    }





