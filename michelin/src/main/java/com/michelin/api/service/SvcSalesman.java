package com.michelin.api.service;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.PasswordDto;
import com.michelin.api.dto.SalesmanLoginDto;

public interface SvcSalesman {
    public ApiResponse updatePassword(PasswordDto password, Integer salesman_id);

    public ApiResponse loginSalesman(SalesmanLoginDto in);  
}
