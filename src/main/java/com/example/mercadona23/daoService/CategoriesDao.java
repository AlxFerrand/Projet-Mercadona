package com.example.mercadona23.daoService;

import com.example.mercadona23.model.Categories;
import com.example.mercadona23.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriesDao {
    @Autowired
    CategoriesRepository categoriesRepository;

    public List<Categories> getCategories(){return categoriesRepository.findAll();}
    public Categories getCategory(String catName){return categoriesRepository.findById(catName).get();}
    public void addCategory(Categories newEntry){categoriesRepository.save(newEntry);}
    public void updateCategories(Categories update,String catName){}
    public void deleteCategories(String catName){}
}
