package com.example.mercadona23.daoService;

import com.example.mercadona23.model.Tokens;
import com.example.mercadona23.repository.TokensRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokensDao {
    @Autowired
    TokensRepository tokensRepository;

    public List<Tokens> getTokens(){return tokensRepository.findAll();}
    public Tokens getToken(Long id){return tokensRepository.findById(id).get();}
    public Tokens getTokenByUserNameHash(String userNameHash) {
        return tokensRepository.findTokensByUserNameHash(userNameHash);
    }
    public void addToken(Tokens newEntry){tokensRepository.save(newEntry);}
    public void updateTokens(Tokens update,Long id){}
    public void deleteTokens(Long id){}
}
