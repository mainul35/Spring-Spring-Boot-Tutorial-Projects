package com.mainul35.controller;

import com.mainul35.model.User;
import com.mainul35.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    UserService userService;
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

    @RequestMapping(value = {"/"})
    public String index(){
        return "index";
    }

    @RequestMapping("/admin/user-form")
    public String form(Model model){

        init();
        //Controller Logic
        model.addAttribute("actionType", "create");
        model.addAttribute("id", System.currentTimeMillis());
        model.addAttribute("sexTypes", sexTypes);
        model.addAttribute("countryList", countryList);
        model.addAttribute("user", new User());
        return "form";
    }

    @RequestMapping(method = RequestMethod.POST, value = {"/admin/form-submit"})
    public String formSubmit(Model model, @ModelAttribute("user") User user, @RequestParam("actionType") String actionType){
        if(actionType.equalsIgnoreCase("create")){
            userService.save(user);
            model.addAttribute("user", user);
            return "formData";
        }else if (actionType.equalsIgnoreCase("update")){
            userService.updateUser(user);
            return "redirect:/user/all-users";
        }
        return "redirect:/";
    }

    @RequestMapping("/user/all-users")
    public String getAllUsers(Model model){
        model.addAttribute("users", userService.getAllUsers());
        return "allUsers";
    }

    @RequestMapping("/admin/update")
    public String getAllUsers(@RequestParam("email") String email, Model model){
        init();
        model.addAttribute("actionType", "update");

        model.addAttribute("sexTypes", sexTypes);
        model.addAttribute("countryList", countryList);
        model.addAttribute("user", userService.getUser(email));
        return "form";
    }
}
