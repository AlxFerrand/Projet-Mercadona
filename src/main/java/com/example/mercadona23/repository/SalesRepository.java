package com.example.mercadona23.repository;

import com.example.mercadona23.model.Sales;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalesRepository extends JpaRepository<Sales,Long> {
    public List<Sales> findAllByOnDate(String onDate);
    public List<Sales> findAllByOffDate(String offDate);
    public List<Sales> findAllByDiscountIsLessThan(int maxDiscount);
    public List<Sales> findAllByDiscountIsGreaterThan(int minDiscount);
    public List<Sales> findAllByDiscountIsBetween(int minDiscount, int maxDiscount);
}
