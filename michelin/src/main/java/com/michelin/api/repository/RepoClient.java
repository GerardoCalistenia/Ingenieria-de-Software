package com.michelin.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.michelin.api.dto.ClientDto;
import com.michelin.api.entity.Client;


public interface RepoClient extends JpaRepository<Client,Integer>{
    
}
