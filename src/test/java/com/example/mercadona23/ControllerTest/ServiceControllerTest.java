package com.example.mercadona23.ControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;

import com.example.mercadona23.daoService.ProductsDao;
import com.example.mercadona23.daoService.SalesDao;
import com.example.mercadona23.model.Products;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;


@SpringBootTest
@AutoConfigureMockMvc
public class ServiceControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ProductsDao productsDaoTest = new ProductsDao();
    @Autowired
    private SalesDao salesDaoTest = new SalesDao();
    private static String RESOURCE_DIRECTORY;
    private static MockMultipartFile MULTI_PNG;
    private static MockMultipartFile MULTI_TXT;
    private static Products PRODUIT_TEST;

    @BeforeAll
    public static void beforeAll() throws IOException {
        RESOURCE_DIRECTORY = System.getProperty("user.dir") + "/src/test/java/com/example/mercadona23/testResources";
        MULTI_PNG = new MockMultipartFile("picture","test1.png","IMAGE/png", new FileInputStream(new File(RESOURCE_DIRECTORY+"/testPNG1.PNG")));
        MULTI_TXT = new MockMultipartFile("picture","test2.png","text/plain", new FileInputStream(new File(RESOURCE_DIRECTORY+"/testFautFichierPNG.png")));
        PRODUIT_TEST = new Products(29L,"Test_Delete","Produit pour test delte",100.0,"picture",null,"Test");

    }

    /**************************** Test getImage ****************************/
    @Test
    public void getImagesTest_With_ExistingImage() throws Exception {
        mockMvc.perform(get("/uploadsFiles/TestImage.jpg"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.IMAGE_JPEG_VALUE));
    }
    @Test
    public void getImagesTest_With_NotExistingImage() throws Exception {
        mockMvc.perform(get("/uploadsFiles/noimage.jpg"))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    /**************************** Test postConnect ****************************/
    @Test
    public void postConnect_With_GoodLogins() throws Exception {
        JSONObject requestBody = new JSONObject();
        requestBody.put("password","passJohnHash");
        requestBody.put("id","johndupont@email.fr");
        String requestJson= requestBody.toString();
        String response = mockMvc.perform(post("/postConnect")
                        .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONObject jsonRespon = new JSONObject(response);
        Assertions.assertTrue(jsonRespon.get("tokenId").toString().length()==64);
        Assertions.assertTrue(jsonRespon.get("role").toString().equals("admin"));
    }
    @Test
    public void postConnect_With_WrongLogins() throws Exception {
        JSONObject requestBody = new JSONObject();
        requestBody.put("password","Fake");
        requestBody.put("id","johndupont@email.fr");
        String requestJson= requestBody.toString();
        String response = mockMvc.perform(post("/postConnect")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        JSONObject jsonRespon = new JSONObject(response);
        Assertions.assertTrue(jsonRespon.get("tokenId").toString().equals("null"));
        Assertions.assertTrue(jsonRespon.get("role").toString().equals("null"));
    }

    /**************************** Test addProduct ****************************/
    @Test
    public void postAddProductTest_With_Wrong_Token() throws Exception {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("label","Test_controller");
        params.add("description","Test du controller");
        params.add("category","Test");
        params.add("price","100.0");
        params.add("tokenId","123");/*Wrong Token*/
        String response = mockMvc.perform(MockMvcRequestBuilders.multipart("/postAddProduct")
                        .file(MULTI_PNG)
                        .params(params))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
        Assertions.assertEquals("Erreur : Temps de connexion dépassé",response);
    }
    @Test
    public void postAddProductTest_With_NoAdminToken() throws Exception {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("label","Test_controller");
        params.add("description","Test du controller");
        params.add("category","Test");
        params.add("price","100.0");
        params.add("tokenId","NoAdmin");/*No Admin Token*/
        String response = mockMvc.perform(MockMvcRequestBuilders.multipart("/postAddProduct")
                        .file(MULTI_PNG)
                        .params(params))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
        Assertions.assertEquals("Erreur : Vous n'etes pas autorisé à faire cette action",response);
    }

    @Test
    public void postAddProductTest_With_Wrong_Data() throws Exception {
      MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("label","Test_controller");
        params.add("description","Test du controller");
        params.add("category","NoExist");/*Wrong data here*/
        params.add("price","100.0");
        params.add("tokenId","tokenId");
        String response = mockMvc.perform(MockMvcRequestBuilders.multipart("/postAddProduct")
                        .file(MULTI_PNG)
                        .params(params))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
        Assertions.assertEquals("Erreur : données saisis incorect",response);
    }
    @Test
    public void postAddProductTest_With_Wrong_File() throws Exception {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("label","Test_controller");
        params.add("description","Test du controller");
        params.add("category","Test");
        params.add("price","100.0");
        params.add("tokenId","tokenId");
        String response = mockMvc.perform(MockMvcRequestBuilders.multipart("/postAddProduct")
                        .file(MULTI_TXT)
                        .params(params))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
        Assertions.assertEquals("Erreur : fichier",response);
    }

    @Test
    public void postAddProductTest_With_GoodData() throws Exception {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("label","Test_controller");
        params.add("description","Test du controller");
        params.add("category","Test");
        params.add("price","100.0");
        params.add("tokenId","tokenId");
        String response = mockMvc.perform(MockMvcRequestBuilders.multipart("/postAddProduct")
                        .file(MULTI_PNG)
                        .params(params))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
        Assertions.assertTrue(response.contains("Produit ajouté avec succes Id :"));
    }

    /**************************** Test updateProduct ****************************/
    @Test
    public void postUpdateProductTest_With_Wrong_Data() throws Exception {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("id","28");
        params.add("label","Test_controller");
        params.add("description","Test du controller");
        params.add("category","NoExist");/*Wrong data*/
        params.add("price","100.0");
        params.add("salesId","1");
        params.add("tokenId","tokenId");
        String response = mockMvc.perform(MockMvcRequestBuilders.multipart("/postUpdateProduct")
                        .file(MULTI_PNG)
                        .params(params))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
        Assertions.assertEquals("Erreur : données saisis incorect",response);
    }
    @Test
    public void postUpdateProductTest_With_Wrong_File() throws Exception {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("id","28");
        params.add("label","Test_controller");
        params.add("description","Test du controller");
        params.add("category","Test");
        params.add("price","100.0");
        params.add("salesId","1");
        params.add("tokenId","tokenId");
        String response = mockMvc.perform(MockMvcRequestBuilders.multipart("/postUpdateProduct")
                        .file(MULTI_TXT)
                        .params(params))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
        Assertions.assertEquals("Erreur : fichier",response);
    }

    @Test
    public void postUpdateProductTest_With_GoodData() throws Exception {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("id","28");
        params.add("label","Test_controller");
        params.add("description","Test du controller");
        params.add("category","Test");
        params.add("price","100.0");
        params.add("salesId","1");
        params.add("tokenId","tokenId");
        String response = mockMvc.perform(MockMvcRequestBuilders.multipart("/postUpdateProduct")
                        .file(MULTI_PNG)
                        .params(params))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
        Assertions.assertTrue(response.contains("Produit mit à jour avec succes"));
    }

    /**************************** Test deleteProduct ****************************/
    @Test
    public void postDeleteProductTest_With_WrongId() throws Exception {
        String id = String.valueOf(-1);
        String response = mockMvc.perform(post("/postDeleteProduct/"+id)
                        .param("tokenId","tokenId"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
        Assertions.assertTrue(response.contains("Le produit n'existe pas"));
    }
    @Test
    public void postDeleteProductTest_With_GoodData() throws Exception {
        Products newProduct = productsDaoTest.addProduct(PRODUIT_TEST);
        String response = mockMvc.perform(post("/postDeleteProduct/"+newProduct.getId())
                .param("tokenId","tokenId"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
        Assertions.assertTrue(response.contains("Produit supprimé avec succes"));
    }

    /**************************** Test addSales ****************************/
    @Test
    public void postAddSales_With_WrongProductId() throws Exception {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("productId","-1");
        params.add("onDate", LocalDate.now(ZoneId.of("Europe/Paris")).toString());
        params.add("offDate",LocalDate.now(ZoneId.of("Europe/Paris")).plusDays(1).toString());
        params.add("discount","50");
        params.add("tokenId","tokenId");
        String response = mockMvc.perform(MockMvcRequestBuilders.multipart("/postAddSales")
                        .params(params))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
        Assertions.assertTrue(response.contains("Le produit n'existe pas"));
    }
    @Test
    public void postAddSales_With_discount_Sup100() throws Exception {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("productId","1");
        params.add("onDate", LocalDate.now(ZoneId.of("Europe/Paris")).toString());
        params.add("offDate",LocalDate.now(ZoneId.of("Europe/Paris")).plusDays(1).toString());
        params.add("discount","150");
        params.add("tokenId","tokenId");
        String response = mockMvc.perform(MockMvcRequestBuilders.multipart("/postAddSales")
                        .params(params))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
        Assertions.assertTrue(response.contains("Promotion ajouté avec succes"));
        Assertions.assertEquals(
                100,
                salesDaoTest.getOneSales(productsDaoTest.getProduct(1L).getSalesId()).getDiscount());
    }
    @Test
    public void postAddSales_With_GoodData() throws Exception {
        MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
        params.add("productId","1");
        params.add("onDate", LocalDate.now(ZoneId.of("Europe/Paris")).toString());
        params.add("offDate",LocalDate.now(ZoneId.of("Europe/Paris")).plusDays(1).toString());
        params.add("discount","50");
        params.add("tokenId","tokenId");
        String response = mockMvc.perform(MockMvcRequestBuilders.multipart("/postAddSales")
                        .params(params))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(response);
        Assertions.assertTrue(response.contains("Promotion ajouté avec succes"));
    }
}
