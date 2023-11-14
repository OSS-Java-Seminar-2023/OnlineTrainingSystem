package com.training.OnlineTraining.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @GetMapping("/register")
    public String getRegisterPage(){
        return "register_page";
    }

    @GetMapping("/login")
    public String getLoginPage(){
        return "login_page";
    }

}

