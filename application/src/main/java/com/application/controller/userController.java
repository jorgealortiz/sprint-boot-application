package com.application.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.application.entity.User;
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
		model.addAttribute("listTab", "active");
		model.addAttribute("userForm",  new User());
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles", roleService.getAllRoles());
		return "user-form/user-view";
	}

	@PostMapping ("/userForm")
	public String createUser (@Valid @ModelAttribute("userForm") User user, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("formTab", "active");
			model.addAttribute("userForm", user);
		}else {
			try {
				userService.createUser(user);
				model.addAttribute("listTab", "active");
				model.addAttribute("userForm", new User());
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",  e.getMessage());
				model.addAttribute("formTab", "active");
			}
		}
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles", roleService.getAllRoles());		
		return "user-form/user-view";
	}
	
	@GetMapping("/editUser/{id}")
	public String getEditUserForm(Model model,  @PathVariable(name = "id") Long id) throws Exception {
		User user = userService.findById(id);
		model.addAttribute("formTab", "active");
		model.addAttribute("userForm",  user);
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles", roleService.getAllRoles());
		model.addAttribute("editMode", "true");
		return "user-form/user-view";
	}
	
	@PostMapping("/editUser")
	public String postEditUserForm(@Valid @ModelAttribute("userForm") User user, BindingResult result, ModelMap model) {
		if(result.hasErrors()) {
			model.addAttribute("userForm", user);
			model.addAttribute("formTab","active");
			model.addAttribute("editMode","true");
		}else {
			try {
				userService.updateUser(user);
				model.addAttribute("listTab","active");
				model.addAttribute("userForm", new User());
			} catch (Exception e) {
				model.addAttribute("formErrorMessage",e.getMessage());
				model.addAttribute("userForm", user);
				model.addAttribute("formTab","active");
			}
			model.addAttribute("editMode","true");
		}
		
		model.addAttribute("userList", userService.getAllUsers());
		model.addAttribute("roles", roleService.getAllRoles());
		return "user-form/user-view";	
	}
	
	@GetMapping("/userForm/cancel")
	public String cancelEditUser(ModelMap model) {
		return "redirect:/userForm";
	}
	
}