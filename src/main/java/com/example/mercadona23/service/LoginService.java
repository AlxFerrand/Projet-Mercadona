package com.example.mercadona23.service;

import com.example.mercadona23.daoService.TokensDao;
import com.example.mercadona23.daoService.UsersDao;
import com.example.mercadona23.model.Tokens;
import com.example.mercadona23.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.mercadona23.service.HashService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class LoginService {

    @Autowired
    UsersDao usersDao;

    @Autowired
    HashService hashService;
    @Autowired
    TokensDao tokensDao;

    public String getToken(String userName, String password) {
        Users clientLogins = usersDao.getUserByUserName(userName);
        if (clientLogins == null) {
            return null;
        }
        if (!hashService.isHashEqual(clientLogins.getPasswordHash(), password, clientLogins.getSalt())) {
            return null;
        }
        String tokenId = hashService.getSalt(64);
        String tokenRole = clientLogins.getUserRole();
        Tokens t = new Tokens(hashService.getHash(tokenId), tokenRole);
        tokensDao.addToken(t);
        return tokenId;
    }

    public boolean isValidToken(String tokenId) {
        String hashId = hashService.getHash(tokenId);
        Tokens t = tokensDao.getTokenByUserNameHash(hashId);
        if (t == null) {
            return false;
        }
        return LocalDateTime.now(ZoneId.of("Europe/Paris")).isBefore(LocalDateTime.parse(t.getValidity()));
    }

    public String findTokenRoleByTokenId(String tokenId) {
        String hashId = hashService.getHash(tokenId);
        String tokenRole = tokensDao.getTokenByUserNameHash(hashId).getUserRole();
        return tokenRole;
    }
}
