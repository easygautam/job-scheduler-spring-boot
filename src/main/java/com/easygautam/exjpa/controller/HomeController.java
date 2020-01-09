package com.easygautam.exjpa.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/a")
public class HomeController {

    @GetMapping("/hello")
    public String getHelloMessage() {
        return "Hello World.";
    }

}
