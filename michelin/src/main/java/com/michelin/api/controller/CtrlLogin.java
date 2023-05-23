package com.michelin.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/michelin")
public class CtrlLogin {

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }

    @GetMapping("/")
    public String whateverPage(){
        return "index";
    }

}
