package com.michelin.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.SalesmanDto;
import com.michelin.api.repository.RepoSalesman;

@Service
public class SvcAdminImp implements SvcAdmin {

    @Autowired
    RepoSalesman repo;

    @Override
    public ApiResponse registerSalesman(SalesmanDto in) {
        return new ApiResponse("it all work well");
    }   
}
