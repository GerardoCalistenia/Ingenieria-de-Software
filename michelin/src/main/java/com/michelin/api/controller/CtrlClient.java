package com.michelin.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.ClientDto;
import com.michelin.api.dto.PasswordDto;
import com.michelin.api.service.SvcClient;
import com.michelin.exception.ApiException;

@RestController
@RequestMapping("/michelin")
public class CtrlClient {   

    @Autowired
    SvcClient svc;

    @PostMapping("/register") 
    public ResponseEntity<ApiResponse> registerClient(@Valid @RequestBody ClientDto client, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        return new ResponseEntity<>(svc.registerClient(client), HttpStatus.OK);
    }

    @PutMapping("/update/password")
    public ResponseEntity<ApiResponse> updatePassword(@Valid @RequestBody PasswordDto in, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
        }

        return new ResponseEntity<>(svc.updatePassword(in), HttpStatus.OK);
    }
}
