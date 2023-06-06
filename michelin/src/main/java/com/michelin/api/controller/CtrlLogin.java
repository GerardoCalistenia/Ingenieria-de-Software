package com.michelin.api.controller;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Cookie;
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

@RestController
@RequestMapping("/michelin")
public class CtrlLogin {
    
    @Autowired
    SvcClient svc;

    @Autowired
    SvcSalesman svcSalesman;

    @Autowired
    SvcAdmin svcAdmin;

    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody LoginDto in, BindingResult bindingResult) { 

        if (in.getEmail() == ""){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Ingresa un email");
        }

        if (!isValidEmail(in.getEmail())) {
            throw new ApiException(HttpStatus.BAD_REQUEST, "Formato de correo electrónico incorrecto");
        }

        ApiResponse response = svc.login(in);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response = svcSalesman.loginSalesman(in);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response = svcAdmin.loginAdmin(in);

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        System.out.println(response);

        throw new ApiException(HttpStatus.NOT_FOUND, "El email es inválido");
    }
    
    @GetMapping("/logout")
    public ApiResponse logout(){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        // Sesión en cookie
        Cookie cookie = new Cookie("nombreCookie", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return new ApiResponse("cierre de sesion exitoso");
    }

}
