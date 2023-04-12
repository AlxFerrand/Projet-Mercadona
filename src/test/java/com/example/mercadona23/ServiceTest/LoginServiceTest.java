package com.example.mercadona23.ServiceTest;

import com.example.mercadona23.service.LoginService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginServiceTest {

    @Autowired
    LoginService loginServiceTest = new LoginService();

    private static String JOHN_IDENTIFIANT;
    private static String JOHN_PASSWORD;
    private static String FAKE_IDENTIFIANT;
    private static String FAKE_PASSWORD;
    private static String FAKE_TOKEN;
    @BeforeAll
    public static void beforeAll(){
        JOHN_IDENTIFIANT = "johndupont@email.fr";
        JOHN_PASSWORD = "passJohnHash";
        FAKE_IDENTIFIANT = "FAKE_IDENTIFIANT";
        FAKE_PASSWORD = "FAKE_PASSWORD";
        FAKE_TOKEN = "3ed7910775090e4567ce53fd6e03da6e92d68282ea993a619852e7b2e4de2870";
    }

    /**************************** Test getToken ****************************/
    @Test
    public void getTokenTest_With_GoodLogins(){
        Assertions.assertEquals(64,loginServiceTest.getToken(JOHN_IDENTIFIANT,JOHN_PASSWORD).length());
    }
    @Test
    public void getTokenTest_With_WrongPassword(){
        Assertions.assertEquals(null,loginServiceTest.getToken(JOHN_IDENTIFIANT,FAKE_PASSWORD));
    }
    @Test
    public void getTokenTest_With_WrongUserName(){
        Assertions.assertEquals(null,loginServiceTest.getToken(FAKE_IDENTIFIANT,JOHN_PASSWORD));
    }

    /**************************** Test Validity Token ****************************/
    @Test
    public void isValidTokenTest_With_ValideToken(){
        String tokenId = loginServiceTest.getToken(JOHN_IDENTIFIANT,JOHN_PASSWORD);
        Assertions.assertTrue(loginServiceTest.isValidToken(tokenId));
    }

    @Test
    public void isValidTokenTest_With_WrongToken(){
        Assertions.assertFalse(loginServiceTest.isValidToken(FAKE_TOKEN));
    }
}
