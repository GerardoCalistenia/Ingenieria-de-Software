package com.michelin.api.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.ClientDto;
import com.michelin.api.repository.RepoClient;
import com.michelin.exception.ApiException;

@Service
public class SvcClientImp implements SvcClient {
    
    @Autowired
    RepoClient repo;

    @Override
    public ApiResponse registerClient(ClientDto in) {
        String password = generateNewPassword();
        try {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(in.getDateOfBirth());
        repo.createClient(in.getName(), in.getEmail(), password, date);
        } catch (ParseException pe) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Formato de fecha incorrecto");
        }
        return new ApiResponse("registro exitoso");
    }

    private String generateNewPassword() {
        return "this is a password";
    }
}
