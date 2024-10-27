package com.paymybuddy.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.backend.model.User;
import com.paymybuddy.backend.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public Iterable<User> getAllUsers(){
		
		return userService.getAllUsers();
		
	}
	
}
