package com.example.mercadona23.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.Objects;


@Entity
public class Sales {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String onDate;
    private String offDate;
    private int discount;
    private String productsList;

    public Sales() {
    }

    public Sales(String onDate, String offDate, int discount) {
        this.onDate = onDate;
        this.offDate = offDate;
        this.discount = discount;
    }

    public Sales(String onDate, String offDate, int discount, String productsList) {
        this.onDate = onDate;
        this.offDate = offDate;
        this.discount = discount;
        this.productsList = productsList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOnDate() {
        return onDate;
    }

    public void setOnDate(String onDate) {
        this.onDate = onDate;
    }

    public String getOffDate() {
        return offDate;
    }

    public void setOffDate(String offDate) {
        this.offDate = offDate;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public ArrayList<Long> getProductsList() {
        String SEPARATEUR = ",";
        if (productsList != null) {
            productsList=productsList.replaceAll("\\{|\\}","");
            String productId[] = productsList.split(SEPARATEUR);
            ArrayList<Long> productsIdList = new ArrayList<>();
            for (int i = 0; i < productId.length; i++) {
                productsIdList.add(Long.valueOf(productId[i]));
            }
            return productsIdList;
        }
        return null;
    }

    public void setProductsList(String productsList) {
        this.productsList = productsList;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "id=" + id +
                ", onDate='" + onDate + '\'' +
                ", offDate='" + offDate + '\'' +
                ", discount=" + discount +
                ", productsList='" + getProductsList() + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Sales sales)) return false;
        return getDiscount() == sales.getDiscount()
                && getId().equals(sales.getId())
                && getOnDate().equals(sales.getOnDate())
                && getOffDate().equals(sales.getOffDate())
                && Objects.equals(getProductsList(), sales.getProductsList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getOnDate(), getOffDate(), getDiscount(), getProductsList());
    }
}
