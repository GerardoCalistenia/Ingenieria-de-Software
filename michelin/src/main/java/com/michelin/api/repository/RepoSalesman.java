package com.michelin.api.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.michelin.api.entity.Salesman;

public interface RepoSalesman extends JpaRepository<Salesman, Integer> {
    
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO salesman (name, email, password, date_of_birth) VALUES (:name, :email, :password, :date_of_birth)", nativeQuery = true)
    void createClient(
    @Param("name") String name,
    @Param("email") String email,
    @Param("password") String password,
    @Param("date_of_birth") Date date_of_birth);
}
