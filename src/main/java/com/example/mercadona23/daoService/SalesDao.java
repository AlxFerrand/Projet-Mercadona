package com.example.mercadona23.daoService;

import com.example.mercadona23.model.Sales;
import com.example.mercadona23.repository.SalesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SalesDao {

    @Autowired
    SalesRepository salesRepository;

    public List<Sales> getSales(){return salesRepository.findAll();}
    public List<Sales> getSalesByOnDate(String onDate){return salesRepository.findAllByOnDate(onDate);}
    public  List<Sales> getSalesByOnDateIsAfter(LocalDate minOnDate){
        List<Sales> salesOnAfter = null;
        for (Sales s : getSales()){
            if (minOnDate.isBefore(LocalDate.parse(s.getOnDate()))){
                salesOnAfter.add(s);
            }
        }
        return salesOnAfter;
    }
    public  List<Sales> getSalesByOffDateIsAfter(LocalDate minOffDate){
        List<Sales> salesOffAfter = null;
        for (Sales s : getSales()){
            if (minOffDate.isBefore(LocalDate.parse(s.getOffDate()))){
                salesOffAfter.add(s);
            }
        }
        return salesOffAfter;
    }
    public  List<Sales> getSalesBetween(LocalDate minOnDate, LocalDate maxOffDate){
        List<Sales> salesBetween = null;
        for (Sales s : getSales()){
            if (minOnDate.isBefore(LocalDate.parse(s.getOnDate()))){
                if (maxOffDate.isAfter(LocalDate.parse(s.getOffDate()))) {
                    salesBetween.add(s);
                }
            }
        }
        return salesBetween;
    }
    public List<Sales> getSalesByOffDate(String offDate){return salesRepository.findAllByOffDate(offDate);}
    public List<Sales> getSalesByDiscountIsLessThan(int maxDiscount){
        return salesRepository.findAllByDiscountIsLessThan(maxDiscount);
    }
    public List<Sales> getSalesByDiscountIsGreaterThan(int minDiscount){
        return salesRepository.findAllByDiscountIsGreaterThan(minDiscount);
    }
    public List<Sales> getSalesByDiscountIsBetween(int minDiscount, int maxDiscount){
        return salesRepository.findAllByDiscountIsBetween(minDiscount,maxDiscount);
    }
    public Sales getOneSales(Long id){return salesRepository.findById(id).get();}
    public Sales addSales(Sales newEntry){return salesRepository.save(newEntry);}
    public void updateSales(Sales update,Long id){salesRepository.save(update);}
    public void deleteSales(Long id){salesRepository.deleteById(id);}
}
