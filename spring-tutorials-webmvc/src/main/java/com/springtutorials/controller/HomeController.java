package com.springtutorials.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	//One way to do it is using the Model object
	@RequestMapping("/")
	public String index(Model model){
		model.addAttribute("message", "A Hello World Spring MVC message Using Model Object");
		return "index";
	}
	
	//Another way to do is using the ModelAndView
	
	@RequestMapping("/hello")  
    public ModelAndView helloWorld() {  
        String message = "Another hello world Using ModelAndView Object";  
        return new ModelAndView("index", "message", message);  
    }
	
}
