package com.example.mercadona23.controller;

import com.example.mercadona23.daoService.*;
import com.example.mercadona23.model.*;
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
    SalesDao salesDao;
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
                articlesList = generatorService.generateArticlesList(productsDao.getProductsByCategoryName(categoryName),false);
        }else {
            articlesList = generatorService.generateArticlesList(productsDao.getProducts(),false);
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

    @GetMapping("/getAdminProducts/{category}")
    public String getAdminProducts (Model model,
                                    @RequestParam("tokenId") String tokenId,
                                    @PathVariable("category") String categoryName)
    {
        if (!loginService.isValidToken(tokenId)){
            return "home";
        }
        if (!(loginService.findTokenRoleByTokenId(tokenId).equals("admin"))){
            return "home";
        }
        List<Products> productsList;
        if (!categoryName.equals("all")) {
            productsList = productsDao.getProductsByCategoryName(categoryName);
        }else {
            productsList = productsDao.getProducts();
        }
        model.addAttribute("productList",productsList);
        model.addAttribute("categoriesList",categoriesDao.getCategories());
        return "adminProducts";
    }

    @GetMapping("/getAdminPromo/{category}")
    public String getAdminPromo (Model model,
                                 @RequestParam("tokenId") String tokenId,
                                 @PathVariable("category") String categoryName)
    {
        if (!loginService.isValidToken(tokenId)){
            return "home";
        }
        if (!(loginService.findTokenRoleByTokenId(tokenId).equals("admin"))){
            return "home";
        }
        List<Articles> articlesList = new ArrayList<>();
        if (!categoryName.equals("all")) {
            articlesList = generatorService.generateArticlesList(productsDao.getProductsByCategoryName(categoryName),true);
        }else {
            articlesList = generatorService.generateArticlesList(productsDao.getProducts(),true);
        }
        model.addAttribute("articlesList",articlesList);
        model.addAttribute("categoriesList",categoriesDao.getCategories());
        return "adminPromo";
    }
    @GetMapping("/getAddProductModale")
    public String getAddModal (Model model, @RequestParam("tokenId") String tokenId){
        if (!loginService.isValidToken(tokenId)){
            return "home";
        }
        if (!(loginService.findTokenRoleByTokenId(tokenId).equals("admin"))){
            return "home";
        }
        model.addAttribute("categoriesList",categoriesDao.getCategories());
        return "modalAddProduct";
    }
    @GetMapping("/getUpdateProductModale/{productId}")
    public String getUpdateModal (Model model,
                                  @RequestParam("tokenId") String tokenId,
                                  @PathVariable("productId") String productId){
        if (!loginService.isValidToken(tokenId)){
            return "home";
        }
        if (!(loginService.findTokenRoleByTokenId(tokenId).equals("admin"))){
            return "home";
        }
        Products productToUpdate =null;
        try{
            productToUpdate  = productsDao.getProduct(Long.valueOf(productId));
        }catch (Exception e){
            return "adminMenu";
        }
        model.addAttribute("productToUpdate",productToUpdate);
        model.addAttribute("categoriesList",categoriesDao.getCategories());
        return "modalUpdateProduct";
    }

    @GetMapping("/getDeleteProductModale/{productId}")
    public String getDeleteModal (Model model,
                                  @RequestParam("tokenId") String tokenId,
                                  @PathVariable("productId") String productId){
        if (!loginService.isValidToken(tokenId)){
            return "home";
        }
        if (!(loginService.findTokenRoleByTokenId(tokenId).equals("admin"))){
            return "home";
        }
        Products productToDelete = null;
        try{
            productToDelete  =  productsDao.getProduct(Long.valueOf(productId));
        }catch (Exception e){
            return "adminMenu";
        }
        model.addAttribute("productToDelete",productToDelete);
        return "modalDeleteProduct";
    }
    @GetMapping("/getAddSalesToProductModale/{productId}")
    public String getAddToProductModal (Model model,
                               @RequestParam("tokenId") String tokenId,
                               @PathVariable("productId") String productId)
    {
        if (!loginService.isValidToken(tokenId)){
            return "home";
        }
        if (!(loginService.findTokenRoleByTokenId(tokenId).equals("admin"))){
            return "home";
        }
        model.addAttribute("productId",productId);
        model.addAttribute("categoriesList",categoriesDao.getCategories());
        return "modalAddSalesToProduct";
    }
    @GetMapping("/getAddSalesToCatModale")
    public String getAddToCatModal (Model model,
                               @RequestParam("tokenId") String tokenId)
    {
        if (!loginService.isValidToken(tokenId)){
            return "home";
        }
        if (!(loginService.findTokenRoleByTokenId(tokenId).equals("admin"))){
            return "home";
        }
        model.addAttribute("categoriesList",categoriesDao.getCategories());
        return "modalAddSalesToCat";
    }
}
