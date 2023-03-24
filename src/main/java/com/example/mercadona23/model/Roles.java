package com.example.mercadona23.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String roleName;

    public Roles() {
    }

    public Roles(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String rolename) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Roles{" +
                "roleName='" + roleName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Roles roles)) return false;
        return getRoleName().equals(roles.getRoleName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoleName());
    }
}
