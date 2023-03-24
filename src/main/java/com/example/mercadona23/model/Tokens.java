package com.example.mercadona23.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Objects;

@Entity
public class Tokens {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userNameHash;
    private String validity;
    private String userRole;

    private void generateValidity(){
        this.validity = LocalDateTime.now(ZoneId.of("Europe/Paris")).plusMinutes(5).toString();
    }
    public Tokens() {
    }

    public Tokens(String userNameHash, String userRole) {
        this.userNameHash = userNameHash;
        this.userRole = userRole;
        generateValidity();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserNameHash() {
        return userNameHash;
    }

    public void setUserNameHash(String userNameHash) {
        this.userNameHash = userNameHash;
    }

    public String getValidity() {
        return validity;
    }

    public void setValidity(String validity) {
        this.validity = validity;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "Tokens{" +
                "id=" + id +
                ", userNameHash='" + userNameHash + '\'' +
                ", validity='" + validity + '\'' +
                ", userRole='" + userRole + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tokens tokens)) return false;
        return getId().equals(tokens.getId())
                && getUserNameHash().equals(tokens.getUserNameHash())
                && getValidity().equals(tokens.getValidity())
                && getUserRole().equals(tokens.getUserRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserNameHash(), getValidity(), getUserRole());
    }
}
