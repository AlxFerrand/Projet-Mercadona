package com.example.mercadona23.ControllerTest;

import com.example.mercadona23.model.Articles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RouterControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void getCatalogue_WithOut_Filter() throws Exception {
        mockMvc.perform(get("/getCatalogue/all"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("articlesList", not(hasSize(0))))
                .andExpect(model().attribute("categoriesList",not(hasSize(0))));
    }
    @Test
    public void getCatalogue_With_Filter_OK() throws Exception {
        mockMvc.perform(get("/getCatalogue/Test"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("articlesList", not(hasSize(0))))
                .andExpect(model().attribute("categoriesList",not(hasSize(0))));
    }
    @Test
    public void getCatalogue_With_Filter_NOK() throws Exception {
        mockMvc.perform(get("/getCatalogue/FAKE"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("articlesList", hasSize(0)))
                .andExpect(model().attribute("categoriesList",not(hasSize(0))));
    }
}
