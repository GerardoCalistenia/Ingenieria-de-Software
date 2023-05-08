package com.michelin.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.ClientDto;
import com.michelin.api.repository.RepoClient;

@Service
public class SvcClientImp implements SvcClient {
    
    @Autowired
    RepoClient repo;

    @Override
    public ApiResponse registerClient(ClientDto client) {
        return null;
    }
}
