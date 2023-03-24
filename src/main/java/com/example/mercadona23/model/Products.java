package com.example.mercadona23.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productLabel;
    private String description;
    private double price;
    private String picture;
    private Long salesId;
    private String categoryName;

    public Products() {
    }

    public Products(String productLabel, String description, double price, String picture, Long salesId, String categoryName) {
        this.productLabel = productLabel;
        this.description = description;
        this.price = price;
        this.picture = picture;
        this.salesId = salesId;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductLabel() {
        return productLabel;
    }

    public void setProductLabel(String productLabel) {
        this.productLabel = productLabel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getSalesId() {
        return salesId;
    }

    public void setSalesId(Long salesId) {
        this.salesId = salesId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "Products{" +
                "id=" + id +
                ", productLabel='" + productLabel + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", picture='" + picture + '\'' +
                ", salesId=" + salesId +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Products products)) return false;
        return Double.compare(products.getPrice(), getPrice()) == 0
                && getId().equals(products.getId())
                && getProductLabel().equals(products.getProductLabel())
                && getDescription().equals(products.getDescription())
                && getPicture().equals(products.getPicture())
                && Objects.equals(getSalesId(), products.getSalesId())
                && getCategoryName().equals(products.getCategoryName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProductLabel(), getDescription(), getPrice(), getPicture(), getSalesId(), getCategoryName());
    }
}
