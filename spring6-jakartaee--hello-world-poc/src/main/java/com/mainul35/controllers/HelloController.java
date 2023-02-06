package com.mainul35.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/")
public class HelloController {

    @GetMapping
    public String sayHello(Model model) {
        model.addAttribute("message", "Basic Web app developed with JakartaEE and Spring 6");
        return "hello";
    }
}
