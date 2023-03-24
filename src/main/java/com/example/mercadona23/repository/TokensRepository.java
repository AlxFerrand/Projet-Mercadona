package com.example.mercadona23.repository;

import com.example.mercadona23.model.Tokens;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokensRepository extends JpaRepository<Tokens,Long> {
    public Tokens findTokensByUserNameHash(String userNameHash);
}
