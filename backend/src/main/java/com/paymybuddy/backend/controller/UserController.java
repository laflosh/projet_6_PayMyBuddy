package com.paymybuddy.backend.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	private Logger log = LogManager.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	/**
	 * Fetching all the users in the database
	 * 
	 * @return A List of users
	 */
	@GetMapping("/users")
	public ResponseEntity<Iterable<User>> getAllUsers(){
		
		log.info("Trying to acces to all users in database.");
		
		Iterable<User> users = userService.getAllUsers();
		
		return ResponseEntity.ok(users);
		
	}
	
	/**
	 * Fetching one user by his id
	 * 
	 * @param id of the user
	 * @return A User
	 */
	@GetMapping("/users/{id}")
	public ResponseEntity<Optional<User>> getOneUserById(@PathVariable int id) {
		
		log.info("Trying to acces to the user in the database with id : " + id + " .");
		
		Optional<User> user = userService.getOneUserById(id);
		
		return ResponseEntity.ok(user);
		
	}
	
	/**
	 * Saving a new user in db with entity sent in the body of the request
	 * 
	 * @param A new user
	 * @return A saved user
	 */
	@PostMapping("/users")
	public ResponseEntity<User> addNewUser(@RequestBody User user) {
		
		log.info("Trying to saving a new user in the database.");
		
		User savedUser = userService.addNewUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
		
	}
	
	/**
	 * Updating an user in the db depending of the id in the entity
	 * 
	 * @param A modify user
	 * @return The updated user
	 */
	@PutMapping("/users")
	public ResponseEntity<User> updateAExistingUser(@RequestBody User user) {
		
		log.info("Trying to updating an existing user in database with id : "+ user.getId() + " .");
		
		User updatedUser = userService.addNewUser(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(updatedUser);
		
	}
	
	/**
	 * Delete an user in the db with the entity
	 * 
	 * @param A user
	 * @return
	 */
	@DeleteMapping("/users")
	public ResponseEntity<Void> deleteAExistingUser(@RequestBody User user) {
		
		log.info("Trying to delete an user with entity in database.");
		boolean isDeleted = userService.deleteAExistingUser(user);
		
		if(isDeleted == true) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	/**
	 * Delete An user in the db depending of the id
	 * 
	 * @param id of the user
	 * @return 
	 */
	@DeleteMapping("/users/{id}")
	public ResponseEntity<Void> deleteAExistingUserById(@PathVariable int id) {
		
		log.info("Trying to delete an user in the database with id : " + id + ".");
		
		boolean isDeleted = userService.deleteAExistingUserById(id);
		
		if(isDeleted == true) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
		
	}
	
}
