package com.example.mercadona23.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Categories {
    @Id
    private String catName;

    public Categories() {}
    public Categories(String catName) {
        this.catName = catName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "catName='" + catName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Categories that)) return false;
        return getCatName().equals(that.getCatName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCatName());
    }
}
