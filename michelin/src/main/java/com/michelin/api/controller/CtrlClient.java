package com.michelin.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.michelin.api.dto.ApiResponse;

@RestController
@RequestMapping("/michelin")
public class CtrlClient {   

    @PostMapping("/register") 
    public ResponseEntity<ApiResponse> registerCostumer() {
        return null;
    }
}
