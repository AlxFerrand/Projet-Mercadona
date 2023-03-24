package com.example.mercadona23.repository;

import com.example.mercadona23.model.Persons;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonsRepository extends JpaRepository<Persons,Long> {
}
