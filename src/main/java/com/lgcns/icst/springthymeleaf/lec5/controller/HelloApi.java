package com.lgcns.icst.springthymeleaf.lec5.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-api")
public class HelloApi {

    @GetMapping("/rest-controller")
    public String hello() {
        return "hello";
    }
}

