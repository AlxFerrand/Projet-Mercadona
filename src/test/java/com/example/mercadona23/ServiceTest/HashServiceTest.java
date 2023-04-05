package com.example.mercadona23.ServiceTest;

import com.example.mercadona23.service.HashService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class HashServiceTest {

    @Autowired
    private HashService hashServiceTest = new HashService();

    @Test
    public void isHashEqualTest(){
        String salt = hashServiceTest.getSalt(64);
        String hash = hashServiceTest.getHashSalt("MonMotDePass",salt);
        Assertions.assertTrue(hashServiceTest.isHashEqual(hash,"MonMotDePass",salt));
    }
}
