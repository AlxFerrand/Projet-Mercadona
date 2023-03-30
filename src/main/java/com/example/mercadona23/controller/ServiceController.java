package com.example.mercadona23.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

@RestController
public class ServiceController {
    @GetMapping(value = "/uploadsFiles/{image}",produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImages(@PathVariable("image") String imageName) throws IOException {
        String path = System.getProperty("user.dir") + "/uploadsFiles/" + imageName;
        Path filePath = Paths.get(path);
        if (Files.exists(filePath) && !Files.isDirectory(filePath)) {
            InputStream in = Files.newInputStream(filePath, StandardOpenOption.READ);
            return in.readAllBytes();
        } else {
            return null;
        }
    }
}