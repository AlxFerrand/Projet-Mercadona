package com.example.mercadona23.controller;

import com.example.mercadona23.daoService.CategoriesDao;
import com.example.mercadona23.daoService.ProductsDao;
import com.example.mercadona23.daoService.SalesDao;
import com.example.mercadona23.model.Categories;
import com.example.mercadona23.model.ConnectUser;
import com.example.mercadona23.model.Products;
import com.example.mercadona23.model.Sales;
import com.example.mercadona23.service.CheckService;
import com.example.mercadona23.service.LoginService;
import com.example.mercadona23.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    LoginService loginService;
    @Autowired
    ProductsDao productsDao;
    @Autowired
    CheckService checkService;
    @Autowired
    UploadService uploadService;
    @Autowired
    SalesDao salesDao;
    @Autowired
    CategoriesDao categoriesDao;

    @GetMapping(value = "/uploadsFiles/{image}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImages(@PathVariable("image") String imageName) throws IOException {
        String path = System.getProperty("user.dir") + "/uploadsFiles/" + imageName;
        Path filePath = Paths.get(path);
        if (Files.exists(filePath) && !Files.isDirectory(filePath)) {
            InputStream in = Files.newInputStream(filePath, StandardOpenOption.READ);
            return in.readAllBytes();
        } else {
            return null;
        }
    }

    @PostMapping("/postConnect")
    public HashMap<String,String> postConnect(@RequestBody ConnectUser user) {
        HashMap<String,String> userConnect = new HashMap<>();
        String tokenId = loginService.getToken(user.getId(),user.getPassword());
        if (tokenId != null){
            userConnect.put("tokenId",tokenId);
            userConnect.put("role", loginService.findTokenRoleByTokenId(tokenId));
        }else {
            userConnect.put("tokenId","null");
            userConnect.put("role", "null");
        }
        return userConnect;
    }

    @PostMapping("/postAddProduct")
    public String postAddProduct(@RequestParam("label") String label,
                                 @RequestParam("description") String description,
                                 @RequestParam("category") String category,
                                 @RequestParam("price") double price,
                                 @RequestParam("picture") MultipartFile picture,
                                 @RequestParam("tokenId") String tokenId
    )
    {
        if (!loginService.isValidToken(tokenId)){
            return "Erreur : Temps de connexion dépassé";
        }
        if (!(loginService.findTokenRoleByTokenId(tokenId).equals("admin"))){
            return "Erreur : Vous n'etes pas autorisé à faire cette action";
        }
        if (!checkService.checkProductData(label,description,category,price)){
            return "Erreur : données saisis incorect";
        }
        if (!checkService.checkFile(picture)){
            return "Erreur : fichier";
        }
        String tempFileName = uploadService.uploadFileToTemp(picture, label);
        if (tempFileName.equals("")){
            return "Erreur : lors de l'upload d'image";
        }
        if (uploadService.getTempFilesByFileName(tempFileName)==null) {
            return "Erreur : lors de l'upload d'image";
        }
        Products productToAdd = new Products(label,description,price,tempFileName, null,category);
        Products productAdd = productsDao.addProduct(productToAdd);
        String definitifFileName = uploadService.copyFilesToUploads(uploadService.getTempFilesByFileName(tempFileName), productAdd.getId());
        if (definitifFileName.equals("")) {
            return "Erreur : lors de la copy d'image, l'image n'a pas pue s'enregistrer sur le serveur";
        }
        productAdd.setPicture(definitifFileName);
        productsDao.updateProducts(productAdd);
        System.out.println(productsDao.getProduct(productAdd.getId()));
        return "Produit ajouté avec succes Id :"+productToAdd.getId();
    }

    @PostMapping("/postUpdateProduct")
    public String postUpdateProduct(@RequestParam("id") int id,
                                    @RequestParam("label") String label,
                                    @RequestParam("description") String description,
                                    @RequestParam("category") String category,
                                    @RequestParam("price") double price,
                                    @RequestParam("picture") MultipartFile picture,
                                    @RequestParam("salesId") String salesId,
                                    @RequestParam("tokenId") String tokenId
    )
    {
        if (!loginService.isValidToken(tokenId)){
            return "Erreur : Temps de connexion dépassé";
        }
        if (!(loginService.findTokenRoleByTokenId(tokenId).equals("admin"))){
            return "Erreur : Vous n'etes pas autorisé à faire cette action";
        }
        int intSalesId;
        if (salesId.equals("")){
            intSalesId = 0;
        }else {
            intSalesId = Integer.parseInt(salesId);
        }
        if (!checkService.checkProductData(id,label,description,category,price,intSalesId)){
            return "Erreur : données saisis incorect";
        }
        Products productToUpdate = new Products((long) id,label,description,price,"tempName", (long) intSalesId,category);
        if(productToUpdate.getSalesId() == 0){productToUpdate.setSalesId(null);}
        if (!picture.getOriginalFilename().equals("")) {
            if (!checkService.checkFile(picture)) {
                return "Erreur : fichier";
            }
            System.out.println(picture.getOriginalFilename());
            String tempFileName = uploadService.uploadFileToTemp(picture, label);
            if (tempFileName.equals("")) {
                return "Erreur : lors de l'upload d'image";
            }
            String definitifFileName = uploadService.copyFilesToUploads(uploadService.getTempFilesByFileName(tempFileName), (long) id);
            if (definitifFileName.equals("")) {
                return "Erreur : lors de la copy d'image";
            }
            productToUpdate.setPicture(definitifFileName);
        }else{
            productToUpdate.setPicture(productsDao.getProduct((long) id).getPicture());
        }
        productsDao.updateProducts(productToUpdate);
        System.out.println(productToUpdate);
        return "Produit mit à jour avec succes";
    }

    @PostMapping("/postDeleteProduct/{id}")
    public String postDeleteProduct (@PathVariable("id")String idProductToDelete, @RequestParam("tokenId") String tokenId){
        if (!loginService.isValidToken(tokenId)){
            return "Erreur : Temps de connexion dépassé";
        }
        if (!(loginService.findTokenRoleByTokenId(tokenId).equals("admin"))){
            return "Erreur : Vous n'etes pas autorisé à faire cette action";
        }
        try{
            Products productToDelete = productsDao.getProduct(Long.valueOf(idProductToDelete));
            System.out.println(productToDelete);
        }catch (Exception e){
            return "Erreur : Le produit n'existe pas";
        }
        File fileProduct = uploadService.getUploadsFilesByFileName(
                productsDao.getProduct(Long.valueOf(idProductToDelete)).getPicture()
        );
        if (fileProduct!=null) {
            uploadService.delteFiles(fileProduct);
        }
        productsDao.deleteProducts(Long.valueOf(idProductToDelete));
        return "Produit supprimé avec succes";
    }

    @PostMapping("/postAddSalesToProduct")
    public String postAddSalesToProduct(@RequestParam("productId") String productId,
                                    @RequestParam("onDate") String onDate,
                                    @RequestParam("offDate") String offDate,
                                    @RequestParam("discount") int discount,
                                    @RequestParam("tokenId") String tokenId
    )
    {
        if (!loginService.isValidToken(tokenId)){
            return "Erreur : Temps de connexion dépassé";
        }
        if (!(loginService.findTokenRoleByTokenId(tokenId).equals("admin"))){
            return "Erreur : Vous n'etes pas autorisé à faire cette action";
        }
        if (discount > 100){
            discount = 100;
        }
        if (discount < 0){
            discount = 0;
        }
        Products productToUpdate;
        try{
            productToUpdate = productsDao.getProduct(Long.valueOf(productId));
        }catch (Exception e){
            return "Erreur : Le produit n'existe pas";
        }
        Sales salesToAdd = new Sales(onDate,offDate,discount,productId);
        Sales salesAdd = salesDao.addSales(salesToAdd);
        System.out.println(salesAdd);
        productToUpdate.setSalesId(salesAdd.getId());
        productsDao.updateProducts(productToUpdate);
        System.out.println(productToUpdate);
        return "Promotion ajoutée avec succes";
    }

    @PostMapping("/postAddSalesToCat")
    public String postAddSalesToCat(@RequestParam("catSales") String catSales,
                               @RequestParam("onDate") String onDate,
                               @RequestParam("offDate") String offDate,
                               @RequestParam("discount") int discount,
                               @RequestParam("tokenId") String tokenId
    )
    {
        if (!loginService.isValidToken(tokenId)){
            return "Erreur : Temps de connexion dépassé";
        }
        if (!(loginService.findTokenRoleByTokenId(tokenId).equals("admin"))){
            return "Erreur : Vous n'etes pas autorisé à faire cette action";
        }
        if (discount > 100){
            discount = 100;
        }
        if (discount < 0){
            discount = 0;
        }
        if (catSales.equals("all")) {
            return "Erreur : Veuillez selectionner une catégorie";
        }
        try{
           Categories categoryChoos = categoriesDao.getCategory(catSales);
        }catch (Exception e){
            return "Erreur : La catégorie n'existe pas";
        }

        List<Products> productsToSalesList = productsDao.getProductsByCategoryName(catSales);
        if (productsToSalesList.isEmpty()){
            return "Erreur : Pas de produit disponible dans cette catégorie";
        }
        String productsId = "";
        for (Products p : productsToSalesList){
            if (productsId.equals("")){
                productsId = p.getId().toString();
            }else {
                productsId = productsId+","+p.getId().toString();
            }
        }
        Sales salesToAdd = new Sales(onDate,offDate,discount,productsId);
        Sales salesAdd = salesDao.addSales(salesToAdd);
        for (Products p : productsToSalesList){
            p.setSalesId(salesAdd.getId());
            productsDao.updateProducts(p);
        }
        return "Promotion ajoutée avec succes";
    }
}