package com.example.mercadona23.ControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.hamcrest.Matchers.*;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


@SpringBootTest
@AutoConfigureMockMvc
public class ServiceControllerTest {
    @Autowired
    private MockMvc mockMvc;

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

}
