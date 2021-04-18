package com.example.unnatiandroidpractical.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class GetSubCategory implements Serializable {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("Result")
    @Expose
    private Result result;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public class Result {

        @SerializedName("Category")
        @Expose
        private ArrayList<Category> category = null;

        public ArrayList<Category> getCategory() {
            return category;
        }

        public void setCategory(ArrayList<Category> category) {
            this.category = category;
        }

    }

    public class Category {

        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("IsAuthorize")
        @Expose
        private Integer isAuthorize;
        @SerializedName("Update080819")
        @Expose
        private Integer update080819;
        @SerializedName("Update130919")
        @Expose
        private Integer update130919;
        @SerializedName("SubCategories")
        @Expose
        private ArrayList<SubCategory> subCategories = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getIsAuthorize() {
            return isAuthorize;
        }

        public void setIsAuthorize(Integer isAuthorize) {
            this.isAuthorize = isAuthorize;
        }

        public Integer getUpdate080819() {
            return update080819;
        }

        public void setUpdate080819(Integer update080819) {
            this.update080819 = update080819;
        }

        public Integer getUpdate130919() {
            return update130919;
        }

        public void setUpdate130919(Integer update130919) {
            this.update130919 = update130919;
        }

        public ArrayList<SubCategory> getSubCategories() {
            return subCategories;
        }

        public void setSubCategories(ArrayList<SubCategory> subCategories) {
            this.subCategories = subCategories;
        }

    }

    public class SubCategory {

        @SerializedName("Id")
        @Expose
        private Integer id;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("Product")
        @Expose
        private ArrayList<Product> product = null;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<Product> getProduct() {
            return product;
        }

        public void setProduct(ArrayList<Product> product) {
            this.product = product;
        }

    }

    public class Product {

        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("PriceCode")
        @Expose
        private String priceCode;
        @SerializedName("ImageName")
        @Expose
        private String imageName;
        @SerializedName("Id")
        @Expose
        private Integer id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPriceCode() {
            return priceCode;
        }

        public void setPriceCode(String priceCode) {
            this.priceCode = priceCode;
        }

        public String getImageName() {
            return imageName;
        }

        public void setImageName(String imageName) {
            this.imageName = imageName;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

    }
}
