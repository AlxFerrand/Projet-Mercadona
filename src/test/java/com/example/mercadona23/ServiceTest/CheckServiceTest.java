package com.example.mercadona23.ServiceTest;

import com.example.mercadona23.model.Sales;
import com.example.mercadona23.service.CheckService;
import com.example.mercadona23.service.HashService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;

@SpringBootTest
@AutoConfigureMockMvc
public class CheckServiceTest {
    @Autowired
    private CheckService checkServiceTest = new CheckService();
    @Autowired
    private HashService hashService=new HashService();

    private static String DATE_ON_OK;
    private static String DATE_OFF_OK;
    private static String DATE_ON_NOK;
    private static String DATE_OFF_NOK;
    private static Sales FAKE_SALES;
    private static MultipartFile MULTI_PNG;
    private static MultipartFile MULTI_TXT;
    private static MultipartFile MULTI_NULL;
    private static String RESOURCE_DIRECTORY;
    private static PRODUCT_DATA PRODUCT_DATA;
    private class PRODUCT_DATA {
        public int id;
        public String label;
        public String description;
        public String category;
        public double price;
        public int salesId;

        public PRODUCT_DATA(int id, String label, String description, String category, double price, int salesId) {
            this.id = id;
            this.label = label;
            this.description = description;
            this.category = category;
            this.price = price;
            this.salesId = salesId;
        }
    }
    @BeforeAll
    public static void beforeAll() throws IOException {
        RESOURCE_DIRECTORY = System.getProperty("user.dir") + "/src/test/java/com/example/mercadona23/testResources";
        DATE_ON_OK = LocalDate.now(ZoneId.of("Europe/Paris")).minusDays(1).toString();
        DATE_OFF_OK = LocalDate.now(ZoneId.of("Europe/Paris")).plusDays(1).toString();
        DATE_ON_NOK = "4000-01-01";
        DATE_OFF_NOK = "2023-01-01";
        FAKE_SALES = new Sales(DATE_ON_OK, DATE_OFF_OK, 0 );
        MULTI_PNG = new MockMultipartFile("test1.png","test1.png","IMAGE/png", new FileInputStream(new File(RESOURCE_DIRECTORY+"/testPNG1.PNG")));
        MULTI_TXT = new MockMultipartFile("test3.png","test1.png","IMAGE/png", new FileInputStream(new File(RESOURCE_DIRECTORY+"/testFautFichierPNG.png")));
        MULTI_NULL = new MockMultipartFile("test3.png","","IMAGE/png", new FileInputStream(new File(RESOURCE_DIRECTORY+"/testFautFichierPNG.png")));
    }
    @BeforeEach
    public void beforeEach(){
        PRODUCT_DATA = new PRODUCT_DATA(1,"Test","Test product","Test",100.0,1);
    }

    /**************************** Test Validity date ****************************/
    @Test
    public void checkValidityDateSalesTest_With_DateON_Ok_And_DateOff_Ok(){
        Assertions.assertTrue(checkServiceTest.checkValidityDateSales(DATE_ON_OK,DATE_OFF_OK));
    }
    @Test
    public void checkValidityDateSalesTest_With_DateON_Ok_And_DateOff_Nok(){
        Assertions.assertFalse(checkServiceTest.checkValidityDateSales(DATE_ON_OK,DATE_OFF_NOK));
    }
    @Test
    public void checkValidityDateSalesTest_With_DateON_Nok_And_DateOff_Nok(){
        Assertions.assertFalse(checkServiceTest.checkValidityDateSales(DATE_ON_NOK,DATE_OFF_NOK));
    }
    @Test
    public void checkValidityDateSalesTest_With_DateON_Nok_And_DateOff_Ok(){
        Assertions.assertFalse(checkServiceTest.checkValidityDateSales(DATE_ON_NOK,DATE_OFF_OK));
    }

    /**************************** Test Validity Sales ****************************/
    @Test
    public void checkValiditySalesTest_With_Sales_Ok(){
        FAKE_SALES.setDiscount(50);
        Assertions.assertTrue(checkServiceTest.checkValiditySales(FAKE_SALES));
    }
    @Test
    public void checkValiditySalesTest_With_Sales_SupTo_100(){
        FAKE_SALES.setDiscount(120);
        Assertions.assertFalse(checkServiceTest.checkValiditySales(FAKE_SALES));
    }
    @Test
    public void checkValiditySalesTest_With_Sales_InfTo_0(){
        FAKE_SALES.setDiscount(-10);
        Assertions.assertFalse(checkServiceTest.checkValiditySales(FAKE_SALES));
    }

    /**************************** Test Check File ****************************/
    @Test
    public void checkFileTest_withFileAccept() throws IOException {
        Assertions.assertTrue(checkServiceTest.checkFile(MULTI_PNG));
    }
    @Test
    public void checkFilesTest_withFilesReject() throws IOException {
        Assertions.assertFalse(checkServiceTest.checkFile(MULTI_TXT));
    }
    @Test
    public void checkFilesTest_withFilesNameNull() throws IOException {
        Assertions.assertFalse(checkServiceTest.checkFile(MULTI_NULL));
    }

    /**************************** Test Check Product Data ****************************/
    @Test
    public void checkProductDataTest_With_GoodData(){
        Assertions.assertTrue(checkServiceTest.checkProductData(
                PRODUCT_DATA.id,
                PRODUCT_DATA.label,
                PRODUCT_DATA.description,
                PRODUCT_DATA.category,
                PRODUCT_DATA.price,
                PRODUCT_DATA.salesId));
    }
    @Test
    public void checkProductDataTest_With_WrongId(){
        PRODUCT_DATA.id=-1;
        Assertions.assertFalse(checkServiceTest.checkProductData(
                PRODUCT_DATA.id,
                PRODUCT_DATA.label,
                PRODUCT_DATA.description,
                PRODUCT_DATA.category,
                PRODUCT_DATA.price,
                PRODUCT_DATA.salesId));
    }
    @Test
    public void checkProductDataTest_With_WrongLabel(){
        PRODUCT_DATA.label = "";
        Assertions.assertFalse(checkServiceTest.checkProductData(
                PRODUCT_DATA.id,
                PRODUCT_DATA.label,
                PRODUCT_DATA.description,
                PRODUCT_DATA.category,
                PRODUCT_DATA.price,
                PRODUCT_DATA.salesId));

        PRODUCT_DATA.label = hashService.getSalt(51);
        Assertions.assertFalse(checkServiceTest.checkProductData(
                PRODUCT_DATA.id,
                PRODUCT_DATA.label,
                PRODUCT_DATA.description,
                PRODUCT_DATA.category,
                PRODUCT_DATA.price,
                PRODUCT_DATA.salesId));
    }
    @Test
    public void checkProductDataTest_With_WrongDescription(){
        PRODUCT_DATA.description = "";
        Assertions.assertFalse(checkServiceTest.checkProductData(
                PRODUCT_DATA.id,
                PRODUCT_DATA.label,
                PRODUCT_DATA.description,
                PRODUCT_DATA.category,
                PRODUCT_DATA.price,
                PRODUCT_DATA.salesId));

        PRODUCT_DATA.description = hashService.getSalt(501);
        Assertions.assertFalse(checkServiceTest.checkProductData(
                PRODUCT_DATA.id,
                PRODUCT_DATA.label,
                PRODUCT_DATA.description,
                PRODUCT_DATA.category,
                PRODUCT_DATA.price,
                PRODUCT_DATA.salesId));
    }
    @Test
    public void checkProductDataTest_With_WrongCategory(){
        PRODUCT_DATA.category = "NoExist";
        Assertions.assertFalse(checkServiceTest.checkProductData(
                PRODUCT_DATA.id,
                PRODUCT_DATA.label,
                PRODUCT_DATA.description,
                PRODUCT_DATA.category,
                PRODUCT_DATA.price,
                PRODUCT_DATA.salesId));
    }
    @Test
    public void checkProductDataTest_With_WrongPrice(){
        PRODUCT_DATA.price = -1;
        Assertions.assertFalse(checkServiceTest.checkProductData(
                PRODUCT_DATA.id,
                PRODUCT_DATA.label,
                PRODUCT_DATA.description,
                PRODUCT_DATA.category,
                PRODUCT_DATA.price,
                PRODUCT_DATA.salesId));

        PRODUCT_DATA.price = 1000000001;
        Assertions.assertFalse(checkServiceTest.checkProductData(
                PRODUCT_DATA.id,
                PRODUCT_DATA.label,
                PRODUCT_DATA.description,
                PRODUCT_DATA.category,
                PRODUCT_DATA.price,
                PRODUCT_DATA.salesId));
    }
    @Test
    public void checkProductDataTest_With_WrongSalesId(){
        PRODUCT_DATA.salesId = -1;
        Assertions.assertFalse(checkServiceTest.checkProductData(
                PRODUCT_DATA.id,
                PRODUCT_DATA.label,
                PRODUCT_DATA.description,
                PRODUCT_DATA.category,
                PRODUCT_DATA.price,
                PRODUCT_DATA.salesId));
    }
}
