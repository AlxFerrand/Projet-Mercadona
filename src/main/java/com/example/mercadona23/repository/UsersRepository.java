package com.example.mercadona23.repository;

import com.example.mercadona23.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Long> {
    public Users findUsersByUserName(String userName);
    public List<Users> findAllByUserRole(String userRole);
}
