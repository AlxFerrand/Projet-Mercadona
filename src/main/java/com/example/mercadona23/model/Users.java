package com.example.mercadona23.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String passwordHash;
    private String salt;
    private String userRole;
    private Long personId;

    public Users() {
    }

    public Users(String userName, String passwordHash, String salt, String userRole, Long personId) {
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.salt = salt;
        this.userRole = userRole;
        this.personId = personId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", salt='" + salt + '\'' +
                ", userRole='" + userRole + '\'' +
                ", personId=" + personId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Users users)) return false;
        return getId().equals(users.getId())
                && getUserName().equals(users.getUserName())
                && getPasswordHash().equals(users.getPasswordHash())
                && getSalt().equals(users.getSalt())
                && getUserRole().equals(users.getUserRole())
                && getPersonId().equals(users.getPersonId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUserName(), getPasswordHash(), getSalt(), getUserRole(), getPersonId());
    }
}
