package com.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.application.entity.User;
import com.application.repository.RoleRepository;
import com.application.service.RoleService;
import com.application.service.UserService;

@Controller
public class userController {
	
	@Autowired
	UserService userService;
	@Autowired
	RoleService roleService;
	
	@GetMapping ("/")
	public String index() {
		return "index";
	}
	
	@GetMapping ("/userForm")
	public String userForm(Model model) {
		model.addAttribute("userForm",  new User());
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles", roleService.getAllRoles());
		model.addAttribute("listTab", "active");
		return "user-form/user-view";
	}

}
