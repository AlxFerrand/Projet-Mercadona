package com.example.mercadona23.ControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
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
}
