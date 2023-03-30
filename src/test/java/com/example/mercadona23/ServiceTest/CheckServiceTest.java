package com.example.mercadona23.ServiceTest;

import com.example.mercadona23.model.Sales;
import com.example.mercadona23.service.CheckService;
import net.bytebuddy.asm.Advice;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZoneId;

@SpringBootTest
@AutoConfigureMockMvc
public class CheckServiceTest {
    @Autowired
    private CheckService checkServiceTest = new CheckService();

    private static String DATE_ON_OK;
    private static String DATE_OFF_OK;
    private static String DATE_ON_NOK;
    private static String DATE_OFF_NOK;
    private static Sales FAKE_SALES;

    @BeforeAll
    public static void beforeAll(){
        DATE_ON_OK = LocalDate.now(ZoneId.of("Europe/Paris")).minusDays(1).toString();
        DATE_OFF_OK = LocalDate.now(ZoneId.of("Europe/Paris")).plusDays(1).toString();
        DATE_ON_NOK = "4000-01-01";
        DATE_OFF_NOK = "2023-01-01";
        FAKE_SALES = new Sales(DATE_ON_OK, DATE_OFF_OK, 0 );
    }
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
}
