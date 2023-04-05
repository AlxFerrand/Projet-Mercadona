package com.example.mercadona23.controller;

import com.example.mercadona23.daoService.TokensDao;
import com.example.mercadona23.model.ConnectUser;
import com.example.mercadona23.service.LoginService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class ServiceController {

    @Autowired
    LoginService loginService;

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

    @PostMapping("/postConnect")
    public HashMap<String,String> postConnect(@RequestBody ConnectUser user) {
        HashMap<String,String> userConnect = new HashMap<>();
        String tokenId = loginService.getToken(user.getId(),user.getPassword());
        if (tokenId != null){
            userConnect.put("tokenId",tokenId);
            userConnect.put("role", loginService.findTokenRoleByTokenId(tokenId));
        }else {
            userConnect.put("tokenId","null");
            userConnect.put("role", "null");
        }
        return userConnect;
    }
}