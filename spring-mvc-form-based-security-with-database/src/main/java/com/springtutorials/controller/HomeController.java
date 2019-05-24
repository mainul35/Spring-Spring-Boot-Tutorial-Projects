package com.springtutorials.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.springtutorials.Dao.UserDao;

@Controller
public class HomeController {

	Logger Logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	UserDao genericDao;
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
	
	@RequestMapping("/login")
	public String login_GET(Model model, HttpSession session){
		if(session.getAttribute("username")==null){
			model.addAttribute("userName", "Anonymous");
		}
		return "login";
	}
	
//	@RequestMapping("/login-process")
//	public String login_POST(Model model, HttpSession session){
//		if(session.getAttribute("username")==null){
//			model.addAttribute("userName", "Anonymous");
//		}
//		return "login";
//	}
	
}
