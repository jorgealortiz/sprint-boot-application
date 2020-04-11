package com.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class userController {
	
	@GetMapping ("/")
	public String index() {
		return "index";
	}
	
	@GetMapping ("/userForm")
	public String userForm() {
		return "user-form/user-view";
	}

}
