package com.michelin.api.service;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.ClientDto;

public interface SvcClient {
    public ApiResponse registerClient(ClientDto client);
}
