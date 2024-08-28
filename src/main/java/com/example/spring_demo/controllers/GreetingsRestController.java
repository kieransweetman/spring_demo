package com.example.spring_demo.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class GreetingsRestController {

    @GetMapping("/restgreeting")
    public String restGreeting() {
        return new String("Hello, World!");
    }

}
