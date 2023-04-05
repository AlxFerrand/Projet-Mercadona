package com.example.mercadona23.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.Random;
@Service
public class HashService {
    public static final Random r = new Random();
    public static final String alphabet = "0123456789azertyuiopqsdfghjklmwxcvbnAZERTYUIOPQSDFGHJKLMWXCVBN";

    public String getSalt(int taille){
        String s = "";
        for (int i = 0; i < taille; i++){
            s += alphabet.charAt(r.nextInt(alphabet.length()));
        }
        return s;
    }

    public String getHash(String toHash){
        return DigestUtils.sha256Hex(toHash);
    }

    public String getHashSalt(String toHashSalt, String salt){
        return getHash(toHashSalt + salt);
    }
    public boolean isHashEqual(String hash, String toCompare, String salt ){
        return getHashSalt(toCompare , salt).equals(hash);
    }

}
