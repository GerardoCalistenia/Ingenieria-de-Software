package com.michelin.api.service;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.ClientDto;
import com.michelin.api.dto.PasswordDto;

public interface SvcClient {
    public ApiResponse registerClient(ClientDto client);

    public ApiResponse updatePassword(PasswordDto password, Integer client_id);
}
