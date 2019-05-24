package com.springtutorials.Controller;

import com.springtutorials.Service.UserService;
import com.springtutorials.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    private String username;
    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/login", "/"})
    public String login(Model model){
        if(!SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString().equalsIgnoreCase("anonymousUser")){
            model.addAttribute("msg", "Welcome, "+username);
            return "index";
        }
        return "login";
    }

    @RequestMapping(value = "/dashboard")
    public String loginSuccess(Model model, HttpSession session){
        username = session.getAttribute("username").toString();
        model.addAttribute("msg", "Welcome, "+username);
        return "index";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signup_GET(Model model){
        System.out.println("Sign up page...");
        User user = new User();
        model.addAttribute("user", user);
        return "signup";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signup_POST(@ModelAttribute("user")User user, Model model){
        System.out.println("Sign up page...");
        userService.createUser(user);
        return "signup";
    }
}
