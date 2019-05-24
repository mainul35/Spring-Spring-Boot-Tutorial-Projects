package com.mainul35.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mainul35.model.User;

@Controller
public class HomeController {
	
    List<String> sexTypes = new ArrayList<String>();
    List<String> countryList = new ArrayList<String>();
    public void init(){
        //Service business logic
        sexTypes.clear();
        countryList.clear();
        sexTypes.add("Male");
        sexTypes.add("Female");
        sexTypes.add("Other");

        countryList.add("Bangladesh");
        countryList.add("India");
        countryList.add("Pakistan");
        countryList.add("USA");
        countryList.add("UK");
        countryList.add("South Africa");
    }

    @RequestMapping("/")
    public String index(){
        return "index";
    }

    @RequestMapping("/user-form")
    public String form(Model model){
        init();
        //Controller Logic
        model.addAttribute("id", System.currentTimeMillis());
        model.addAttribute("sexTypes", sexTypes);
        model.addAttribute("countryList", countryList);
        model.addAttribute("user", new User());
        return "form";
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/form-submit"})
    public String formSubmit(Model model, @ModelAttribute("user") User user){
            model.addAttribute("user", user);
            return "formData";
    }
}
