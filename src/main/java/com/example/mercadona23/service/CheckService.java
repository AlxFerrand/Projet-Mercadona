package com.example.mercadona23.service;

import com.example.mercadona23.model.Sales;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Service
public class CheckService {

    public boolean checkValiditySales (Sales s){
        return s.getDiscount()<=100 && s.getDiscount()>0 && checkValidityDateSales(s.getOnDate(),s.getOffDate());
    }
    public boolean checkValidityDateSales (String onDate, String offDate){
        LocalDate onDateLDT = LocalDate.parse(onDate);
        LocalDate offDateLDT = LocalDate.parse(offDate);
        return (LocalDate.now(ZoneId.of("Europe/Paris")).isAfter(onDateLDT)
                && LocalDate.now(ZoneId.of("Europe/Paris")).isBefore(offDateLDT));
    }

}
