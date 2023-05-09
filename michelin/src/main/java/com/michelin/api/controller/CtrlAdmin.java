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
import com.michelin.api.dto.SalesmanDto;
import com.michelin.api.service.SvcAdmin;
import com.michelin.exception.ApiException;

@RestController
@RequestMapping("/michelin/admin")
public class CtrlAdmin {

    @Autowired
    SvcAdmin svc;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> registerSalesman(@Valid @RequestBody SalesmanDto in, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ApiException(HttpStatus.BAD_REQUEST, bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        return new ResponseEntity<>(svc.registerSalesman(in), HttpStatus.OK);
    }
    
}
