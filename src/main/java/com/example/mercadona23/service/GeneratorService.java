package com.example.mercadona23.service;

import com.example.mercadona23.daoService.SalesDao;
import com.example.mercadona23.model.Articles;
import com.example.mercadona23.model.Products;
import com.example.mercadona23.model.Sales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class GeneratorService {
    @Autowired
    SalesDao salesDao;
    @Autowired
    CheckService checkService;

    public ArrayList<Articles> generateArticlesList (List<Products> productsList,boolean modeAdmin) {
        ArrayList<Articles> articlesList = new ArrayList<>();
        if (!modeAdmin) {
            for (Products p : productsList) {
                if (generateArticle(p) != null) {
                    articlesList.add(generateArticle(p));
                }
            }
        }else {
            for (Products p : productsList) {
                if (generateAdminArticle(p) != null) {
                    articlesList.add(generateAdminArticle(p));
                }
            }
        }
        return articlesList;
    }

    public Articles generateArticle(Products p) {
        if (p.getId()==null){
            return null;
        }
        Articles a = new Articles(p);
        if (p.getSalesId() != null) {
            try {
                Sales productSales = salesDao.getOneSales(p.getSalesId());
                if (checkService.checkValiditySales(productSales)) {
                    a.setDiscount(productSales.getDiscount());
                    a.setOnDateDiscount(productSales.getOnDate());
                    a.setOffDateDiscount(productSales.getOffDate());
                } else {
                    a.setDiscount(0);
                }
            }catch (Exception e){
                a.setDiscount(0);
            }
        } else {
            a.setDiscount(0);
        }
        a.setPrice();
        return a;
    }
    public Articles generateAdminArticle(Products p) {
        if (p.getId()==null){
            return null;
        }
        Articles a = new Articles(p);
        if (p.getSalesId() != null) {
            try {
                Sales productSales = salesDao.getOneSales(p.getSalesId());
                a.setDiscount(productSales.getDiscount());
                a.setOnDateDiscount(productSales.getOnDate());
                a.setOffDateDiscount(productSales.getOffDate());
            }catch (Exception e){
                a.setDiscount(0);
            }
        } else {
            a.setDiscount(0);
        }
        a.setPrice();
        return a;
    }
}

