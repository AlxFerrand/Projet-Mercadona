package com.example.mercadona23.service;

import com.example.mercadona23.daoService.CategoriesDao;
import com.example.mercadona23.daoService.ProductsDao;
import com.example.mercadona23.daoService.SalesDao;
import com.example.mercadona23.model.Sales;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class CheckService {

    @Autowired
    ProductsDao productsDao;

    @Autowired
    CategoriesDao categoriesDao;
    @Autowired
    SalesDao salesDao;

    public boolean checkValiditySales (Sales s){
        return s.getDiscount()<=100 && s.getDiscount()>0 && checkValidityDateSales(s.getOnDate(),s.getOffDate());
    }
    public boolean checkValidityDateSales (String onDate, String offDate){
        LocalDate onDateLDT = LocalDate.parse(onDate);
        LocalDate offDateLDT = LocalDate.parse(offDate);
        return (LocalDate.now(ZoneId.of("Europe/Paris")).isAfter(onDateLDT)
                && LocalDate.now(ZoneId.of("Europe/Paris")).isBefore(offDateLDT));
    }

    public boolean checkFile (MultipartFile file){
        try{
            String typeMime = new Tika().detect(file.getBytes());
            if ((!typeMime.equals("image/png")) && (!typeMime.equals("image/jpg")) && (!typeMime.equals("image/jpeg"))) {
                return false;
            }
        }catch (IOException ioe){
            return false;
        }
        if (file.getOriginalFilename()==""){
            return false;
        }
        return true;
    }

    public boolean checkProductData(int id, String label, String description, String category, double price,int salesId){
        try{
            productsDao.getProduct((long) id);
        }catch (Exception e){
            return false;
        }
        try{
            categoriesDao.getCategory(category);
        }catch (Exception e){
            return false;
        }
        if (salesId != 0) {
            try {
                salesDao.getOneSales((long) salesId);
            } catch (Exception e) {
                return false;
            }
        }
        if(label=="" | label.length()>50){
            return false;
        }
        if (description=="" | description.length()>500){
            return false;
        }
        if (price<0 | price>1000000){
            return false;
        }
        return true;
    }
    public boolean checkProductData( String label, String description, String category, double price){
        try{
            categoriesDao.getCategory(category);
        }catch (Exception e){
            return false;
        }
        if(label.length()>50){
            return false;
        }
        if (description.length()>500){
            return false;
        }
        if (price<0 | price>1000000){
            return false;
        }
        return true;
    }

}
