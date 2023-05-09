package com.michelin.api.service;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.SalesmanDto;

public interface SvcAdmin {
    public ApiResponse registerSalesman(SalesmanDto in);   
}
