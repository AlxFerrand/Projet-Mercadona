package com.example.mercadona23.daoServiceTest;

import com.example.mercadona23.daoService.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureMockMvc
public class daoServiceTest {

    @Autowired
    private CategoriesDao categoriesDao = new CategoriesDao();
    @Autowired
    private PersonsDao personsDao = new PersonsDao();
    @Autowired
    private ProductsDao productsDao = new ProductsDao();
    @Autowired
    private RolesDao rolesDao = new RolesDao();
    @Autowired
    private SalesDao salesDao = new SalesDao();
    @Autowired
    private TokensDao tokensDao = new TokensDao();
    @Autowired
    private UsersDao usersDao = new UsersDao();

    @Test
    public void getCategories(){Assertions.assertFalse(categoriesDao.getCategories().isEmpty());}
    @Test
    public void getPersons(){Assertions.assertFalse(personsDao.getPersons().isEmpty());}
    @Test
    public void getProducts(){Assertions.assertFalse(productsDao.getProducts().isEmpty());}
    @Test
    public void getRoles(){Assertions.assertFalse(rolesDao.getRoles().isEmpty());}
    @Test
    public void getSales(){Assertions.assertFalse(salesDao.getSales().isEmpty());}
    @Test
    public void getTokens(){Assertions.assertFalse(tokensDao.getTokens().isEmpty());}
    @Test
    public void getUsers(){Assertions.assertFalse(usersDao.getUsers().isEmpty());}

}
