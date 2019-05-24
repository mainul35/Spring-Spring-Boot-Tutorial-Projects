package com.mainul35.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("message", "Spring MVC Java Configuration is working properly.");
        return "index";
    }
}
