package com.michelin.api.controller;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.michelin.api.dto.ApiResponse;
import com.michelin.api.dto.LoginDto;
import com.michelin.api.service.SvcAdmin;
import com.michelin.api.service.SvcClient;
import com.michelin.api.service.SvcSalesman;
import com.michelin.exception.ApiException;

import javax.servlet.http.Cookie;

@RestController
@RequestMapping("/michelin")
public class CtrlUpdatePass {

    @Autowired
    SvcClient svc;

    @Autowired
    SvcSalesman svcSalesman;





    @PostMapping("/updatePass")
    public ResponseEntity<ApiResponse> updatePass(@Valid @RequestBody PasswordDto in, BindingResult bindingResult) {
        return;
    }

    @PostMapping("/receiveMail")
    public ResponseEntity<ApiResponse> receiveMail(@Valid @RequestBody PasswordDto in, BindingResult bindingResult) {
        return;
    }
}