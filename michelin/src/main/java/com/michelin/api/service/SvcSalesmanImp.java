package com.michelin.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.PasswordDto;
import com.michelin.api.repository.RepoSalesman;

@Service
public class SvcSalesmanImp implements SvcSalesman {

    @Autowired
    RepoSalesman repo;

    @Override 
    public ApiResponse updatePassword(PasswordDto in, Integer salesman_id) {
        return new ApiResponse("IT ALL WORK WELL");
    }
}
