package com.example.mercadona23.controller;

import com.example.mercadona23.daoService.CategoriesDao;
import com.example.mercadona23.daoService.ProductsDao;
import com.example.mercadona23.model.Articles;
import com.example.mercadona23.model.Products;
import com.example.mercadona23.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;


@Controller
public class RouterController {

    @Autowired
    ProductsDao productsDao;
    @Autowired
    CategoriesDao categoriesDao;
    @Autowired
    GeneratorService generatorService;

    @GetMapping("/")
    public String getHome(){

        return "home";
    }

    @GetMapping("/getCatalogue/{category}")
    public String getCatalogue(Model model,@PathVariable("category") String categoryName){
        List<Articles> articlesList = new ArrayList<>();
        if (!categoryName.equals("all")){
                articlesList = generatorService.generateArticlesList(productsDao.getProductsByCategoryName(categoryName));
        }else {
            articlesList = generatorService.generateArticlesList(productsDao.getProducts());
        }
        model.addAttribute("articlesList",articlesList);
        model.addAttribute("categoriesList",categoriesDao.getCategories());
        return "catalogue";
    }
}
