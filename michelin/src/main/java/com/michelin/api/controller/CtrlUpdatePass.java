package com.michelin.api.controller;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
import com.michelin.api.dto.PasswordDto;
import com.michelin.api.service.SvcClient;
import com.michelin.api.entity.Salesman;
import com.michelin.api.repository.RepoSalesman;
import com.michelin.api.service.SvcSalesman;
import com.michelin.exception.ApiException;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.servlet.http.Cookie;

@RestController
@RequestMapping("/michelin")
public class CtrlUpdatePass {

    @Autowired
    SvcClient svc;

    @Autowired
    SvcSalesman svcSalesman;

    @Autowired
    RepoSalesman repo;


    private Integer id;
    private String email;


    @PostMapping("/updatePass")
    public ResponseEntity<ApiResponse> updatePass(@Valid @RequestBody PasswordDto in, BindingResult bindingResult) {
        Salesman salesman = repo.findByEmail(getEmail());

        if (salesman != null) {
            setId(salesman.getAdministratorId());
        } else {
            setId(-1);
        }

        ApiResponse response = svc.updatePassword(in, getEmail());

        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        response = svcSalesman.updatePassword(in, getId());

        if (response != null){
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        throw new ApiException(HttpStatus.NOT_FOUND, "sin sesion");
    }

    @PostMapping("/receiveEmail")
    public void receiveEmail(@RequestBody ObjectNode in) {
        setEmail(in.get("email").asText());
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}