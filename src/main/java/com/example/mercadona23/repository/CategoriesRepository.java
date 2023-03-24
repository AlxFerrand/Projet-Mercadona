package com.example.mercadona23.repository;

import com.example.mercadona23.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriesRepository extends JpaRepository<Categories,String> {
}
