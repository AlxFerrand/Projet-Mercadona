package com.example.mercadona23.ServiceTest;

import com.example.mercadona23.model.Articles;
import com.example.mercadona23.model.Products;
import com.example.mercadona23.service.GeneratorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class GeneratorServiceTest {
    @Autowired
    private GeneratorService generatorServiceTest = new GeneratorService();

    private static Products PRODUCT;
    private static Products PRODUCT_With_SalesId_Null;
    private static Products PRODUCT_With_Fake_SalesId;
    private static Products PRODUCT_With_Dates_NOK;

    @BeforeAll
    public static void beforeAll(){
    }

    @BeforeEach
    public void beforeEach(){
        PRODUCT = new Products(
                "Product test generator OK",
                "description",
                200.00,"picture",
                5L,
                "Test");
        PRODUCT.setId(1L);
    }

    /**************************** Test Generate single Article ****************************/
    @Test
    public void generateArticleTest_With_Product_Null(){
        Assertions.assertTrue(generatorServiceTest.generateArticle(new Products())==null);
    }
    @Test
    public void generateArticleTest_With_ProductSalesId_Null(){
        PRODUCT.setSalesId(null);
        Assertions.assertFalse(generatorServiceTest.generateArticle(PRODUCT)==null);
        Articles a = generatorServiceTest.generateArticle(PRODUCT);
        Assertions.assertTrue(a.getDiscount()==0);
        Assertions.assertTrue(a.getBasePrice()==a.getPrice());
    }
    @Test
    public void generateArticleTest_With_ProductSalesId_Fake(){
        PRODUCT.setSalesId(28456978L);
        Assertions.assertFalse(generatorServiceTest.generateArticle(PRODUCT)==null);
        Articles a = generatorServiceTest.generateArticle(PRODUCT);
        Assertions.assertTrue(a.getDiscount()==0);
        Assertions.assertTrue(a.getBasePrice()==a.getPrice());
    }
    @Test
    public void generateArticleTest_With_ProductSales_Validity_NOK(){
        PRODUCT.setSalesId(1L);
        Assertions.assertFalse(generatorServiceTest.generateArticle(PRODUCT)==null);
        Articles a = generatorServiceTest.generateArticle(PRODUCT);
        Assertions.assertTrue(a.getDiscount()==0);
        Assertions.assertTrue(a.getBasePrice()==a.getPrice());
    }

    @Test
    public void generateArticleTest_With_Product_Ok(){
        Assertions.assertFalse(generatorServiceTest.generateArticle(PRODUCT)==null);
        Articles a = generatorServiceTest.generateArticle(PRODUCT);
        Assertions.assertTrue(a.getDiscount()!=0);
        Assertions.assertTrue(a.getBasePrice()!=a.getPrice());
    }

    /**************************** Test Generate Articles List ****************************/
    @Test
    public void generateArticlesListTest(){
        List<Products> productsList = new ArrayList<>();
        ArrayList<Articles> articlesList = new ArrayList<>();
        productsList.add(new Products());
        productsList.add(PRODUCT);
        articlesList = generatorServiceTest.generateArticlesList(productsList,false);
        Assertions.assertTrue(articlesList.size() == 1);
        Assertions.assertTrue(articlesList.get(0).getName() == "Product test generator OK");
    }
}
