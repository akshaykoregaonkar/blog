package com.akshay.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }
}
