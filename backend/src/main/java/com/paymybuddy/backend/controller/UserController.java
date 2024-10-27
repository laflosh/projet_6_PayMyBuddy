package com.paymybuddy.backend.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/users")
	public User addNewUser(@RequestBody User user) {
		
		return userService.addNewUser(user);
		
	}
	
	@PutMapping("/users")
	public User updateAExistingUser(@RequestBody User user) {
		
		return userService.updateAExistingUser(user);
		
	}
	
	@DeleteMapping("/users")
	public void deleteAExistingUser(@RequestBody User user) {
		
		userService.deleteAExistingUser(user);
		
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteAExistingUserById(@PathVariable int id) {
		
		userService.deleteAExistingUserById(id);
		
	}
	
}
