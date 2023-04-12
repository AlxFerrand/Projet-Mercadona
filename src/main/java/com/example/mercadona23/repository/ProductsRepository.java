package com.example.mercadona23.repository;

import com.example.mercadona23.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductsRepository extends JpaRepository<Products,Long> {
    public List<Products> findAllByCategoryNameOrderById(String categoryName);
    public List<Products> findAllByOrderById();
    public List<Products> findAllByPriceIsLessThan(double minPrice);
    public List<Products> findAllByPriceIsGreaterThan(double maxPrice);
    public List<Products> findAllByPriceIsBetween(double minPrice, double maxPrice);
    public List<Products> findAllBySalesId(Long salesId);

}
