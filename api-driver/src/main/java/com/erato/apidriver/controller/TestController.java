package com.erato.apidriver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/noauthTest")
    public String noauthTest(){
        return "hello";
    }

    @GetMapping("/authTest")
    public String authTest() {
        return "hello";
    }
}
