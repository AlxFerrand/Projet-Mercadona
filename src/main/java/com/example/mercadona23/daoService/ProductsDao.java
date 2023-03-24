package com.example.mercadona23.daoService;

import com.example.mercadona23.model.Products;
import com.example.mercadona23.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsDao {
    @Autowired
    ProductsRepository productsRepository;

    public List<Products> getProducts(){return productsRepository.findAll();}
    public List<Products> getProductsByCategoryName(String categoryName){
        return productsRepository.findAllByCategoryName(categoryName);
    }
    public List<Products> getProductsByPriceIsLessThan(double minPrice){
        return productsRepository.findAllByPriceIsLessThan(minPrice);
    }
    public List<Products> getProductsByPriceIsGreaterThan(double maxPrice){
       return productsRepository.findAllByPriceIsGreaterThan(maxPrice);
    }
    public List<Products> getProductsByPriceIsBetween(double minPrice, double maxPrice){
        return productsRepository.findAllByPriceIsBetween(minPrice,maxPrice);
    }
    public List<Products> getProductsBySalesId(Long salesId){
        return productsRepository.findAllBySalesId(salesId);
    }
    public Products getProduct(Long id){return productsRepository.findById(id).get();}
    public void addProduct(Products newEntry){productsRepository.save(newEntry);}
    public void updateProducts(Products update,Long id){}
    public void deleteProducts(Long id){}
}
