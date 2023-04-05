package com.example.mercadona23.controller;

import com.example.mercadona23.daoService.CategoriesDao;
import com.example.mercadona23.daoService.PersonsDao;
import com.example.mercadona23.daoService.ProductsDao;
import com.example.mercadona23.daoService.UsersDao;
import com.example.mercadona23.model.Articles;
import com.example.mercadona23.model.Persons;
import com.example.mercadona23.model.Users;
import com.example.mercadona23.service.GeneratorService;
import com.example.mercadona23.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
public class RouterController {

    @Autowired
    ProductsDao productsDao;
    @Autowired
    CategoriesDao categoriesDao;
    @Autowired
    UsersDao usersDao;

    @Autowired
    PersonsDao personsDao;
    @Autowired
    GeneratorService generatorService;
    @Autowired
    LoginService loginService;

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

    @GetMapping("/adminArea")
    public String getEspaceAdmin (Model model,
                                  @RequestParam("tokenId") String tokenId,
                                  @RequestParam("userName") String userName){
        if (!loginService.isValidToken(tokenId)){
            return "home";
        }
        if (!(loginService.findTokenRoleByTokenId(tokenId).equals("admin"))){
            return "home";
        }
        Users user = usersDao.getUserByUserName(userName);
        Persons personUser = personsDao.getPerson(user.getPersonId());
        model.addAttribute("user", personUser);
        model.addAttribute("tokenId", tokenId);
        return "adminArea";
    }

    @GetMapping("/getAdminMenu")
    public String getAdminMenu (Model model, @RequestParam("tokenId") String tokenId) {
        if (!loginService.isValidToken(tokenId)){
            return "home";
        }
        if (!(loginService.findTokenRoleByTokenId(tokenId).equals("admin"))){
            return "home";
        }
        return "adminMenu";
    }

    @GetMapping("/getAdminProducts")
    public String getAdminProducts (Model model, @RequestParam("tokenId") String tokenId) {
        if (!loginService.isValidToken(tokenId)){
            return "home";
        }
        if (!(loginService.findTokenRoleByTokenId(tokenId).equals("admin"))){
            return "home";
        }
        return "adminProducts";
    }

    @GetMapping("/getAdminPromo")
    public String getAdminPromo (Model model, @RequestParam("tokenId") String tokenId) {
        if (!loginService.isValidToken(tokenId)){
            return "home";
        }
        if (!(loginService.findTokenRoleByTokenId(tokenId).equals("admin"))){
            return "home";
        }
        return "adminPromo";
    }
}
