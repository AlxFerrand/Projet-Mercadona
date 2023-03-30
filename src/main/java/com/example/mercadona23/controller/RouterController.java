package com.example.mercadona23.controller;

import com.example.mercadona23.daoService.ProductsDao;
import com.example.mercadona23.service.GeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RouterController {

    @Autowired
    ProductsDao productsDao;
    @Autowired
    GeneratorService generatorService;

    @GetMapping("/")
    public String getHome(){

        return "home";
    }

    @GetMapping("/getCatalogue")
    public String getCatalogue(Model model){
        model.addAttribute("articlesList",generatorService.generateArticlesList(productsDao.getProducts()));
        return "catalogue";
    }
}
