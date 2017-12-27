package com.springtutorials.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.springtutorials.Dao.UserDao;
import com.springtutorials.Model.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserDao genericDao;
	
	
	@RequestMapping(method=RequestMethod.GET, value="/show")
	public String showUsers_GET(@ModelAttribute("user") User user, Model model, HttpServletRequest request){
//		model.addAttribute("users", genericDao.getAll());
//		model.addAttribute("username", user.getUsername());
		model.addAttribute("userName", request.getUserPrincipal().getName());
		System.out.println(request.getUserPrincipal().getName());
		return "show_user_list";
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/create")
	public String addUser_POST(@ModelAttribute("user") User user, Model model){
		genericDao.saveOrUpdate(user);
		model.addAttribute("users", genericDao.getAll());
		return "show_user_list";
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/create")
	public String addUser_GET(@ModelAttribute("user") User user, Model model){
		model.addAttribute("user", user);
		return "create_user";
	}

	@RequestMapping(method=RequestMethod.GET, value="/update/{id}")
	public String updateUser_GET(@PathVariable("id") Integer id, Model model){
		
		model.addAttribute("user", genericDao.get(id));
		return "create_user";
	}

	@RequestMapping(method=RequestMethod.GET, value="/delete")
	public String deleteUser_GET(@RequestParam("id") Integer id, @ModelAttribute("user") User user, Model model){
		genericDao.delete(user.getId());
		return "show_user_list";
	}
	
	
}
