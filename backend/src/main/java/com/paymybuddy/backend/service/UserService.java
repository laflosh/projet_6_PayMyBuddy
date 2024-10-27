package com.paymybuddy.backend.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.backend.model.User;
import com.paymybuddy.backend.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public Iterable<User> getAllUsers(){
		
		return userRepository.findAll();
		
	}

	public Optional<User> getOneUser(int id) {

		return userRepository.findById(id);
		
	}
	
}
