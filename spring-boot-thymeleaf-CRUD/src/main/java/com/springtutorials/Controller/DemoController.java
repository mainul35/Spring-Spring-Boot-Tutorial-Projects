package com.springtutorials.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class DemoController {



    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String bar(){
        System.out.println("Admin access only...");
        return "login";
    }

    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String _403(){
        System.out.println("403 : Access Denied!");
        return "403";
    }
}
