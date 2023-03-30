package com.example.mercadona23.model;

public class Articles {
    private String name;
    private String description;
    private String picture;
    private String categoryName;
    private double basePrice;
    private double price;
    private int discount;
    private String onDateDiscount;
    private String offDateDiscount;

    public Articles(String name, String description, String picture, String categoryName, double basePrice) {
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.categoryName = categoryName;
        this.basePrice = basePrice;
    }
    public Articles(Products p){
        this.name = p.getProductLabel();
        this.description = p.getDescription();
        this.picture = p.getPicture();
        this.categoryName = p.getCategoryName();
        this.basePrice = p.getPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice() {
        this.price = Math.round(this.getBasePrice()*(100-this.getDiscount()))/100.0;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getOnDateDiscount() {
        return onDateDiscount;
    }

    public void setOnDateDiscount(String onDateDiscount) {
        this.onDateDiscount = onDateDiscount;
    }

    public String getOffDateDiscount() {
        return offDateDiscount;
    }

    public void setOffDateDiscount(String offDateDiscount) {
        this.offDateDiscount = offDateDiscount;
    }

    @Override
    public String toString() {
        return "Articles{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", picture='" + picture + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", basePrice=" + basePrice +
                ", price=" + price +
                ", discount=" + discount +
                ", onDateDiscount='" + onDateDiscount + '\'' +
                ", offDateDiscount='" + offDateDiscount + '\'' +
                '}';
    }
}
