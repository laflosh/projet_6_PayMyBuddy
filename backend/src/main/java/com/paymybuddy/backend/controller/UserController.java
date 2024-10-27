package com.paymybuddy.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.backend.model.User;
import com.paymybuddy.backend.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping("/users")
	public Iterable<User> getAllUsers(){
		
		return userService.getAllUsers();
		
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getOneUser(@PathVariable int id) {
		
		return userService.getOneUser(id);
		
	}
	
	
}
