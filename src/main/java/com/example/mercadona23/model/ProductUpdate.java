package com.example.mercadona23.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductUpdate {
    private Long id;
    private String label;
    private String description;
    private double price;
    private MultipartFile picture;
    private Long salesId;
    private String category;

    @Override
    public String toString() {
        return "ProductUpdate{" +
                "id=" + id +
                ", label='" + label + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", picture=" + picture +
                ", salesId=" + salesId +
                ", category='" + category + '\'' +
                '}';
    }
}
