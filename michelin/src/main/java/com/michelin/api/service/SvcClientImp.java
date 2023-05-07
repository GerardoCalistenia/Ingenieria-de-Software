package com.michelin.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.ClientDto;

@Service
public class SvcClientImp implements SvcClient {
    
    

    @Override
    public ApiResponse registerClient(ClientDto client) {
        return null;
    }
}
