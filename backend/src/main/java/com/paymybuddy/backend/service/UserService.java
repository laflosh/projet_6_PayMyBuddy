package com.paymybuddy.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.backend.model.UserDB;
import com.paymybuddy.backend.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService {

	private Logger log = LogManager.getLogger(UserService.class);
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * @return A List Of users 
	 */
	public Iterable<UserDB> getAllUsers(){
		
		log.info("Fetching all the users in the database");
		return userRepository.findAll();
		
	}

	/**
	 * @param id of the user
	 * @return An user
	 */
	public UserDB getOneUserById(int id) {
			
		log.info("Fetching one user in the database with id : {}", id);
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		
	}

	/**
	 * @param A new user
	 * @return The saved user
	 */
	public UserDB addNewUser(UserDB user) {

		log.info("Saving a new user in the database");
		return userRepository.save(user);
		
	}

	/**
	 * @param A modify user
	 * @return A updated user
	 */
	public UserDB updateAExistingUser(UserDB user) {
		
		log.info("Updating the user in the database with id : {}", user.getId());
		return userRepository.save(user);
		
	}
	
	/**
	 * @param An user
	 */
	public boolean deleteAExistingUserByTheEntity(UserDB user) {
		
		log.info("Delete an user in the database with his entity");
		
		if(userRepository.existsById(user.getId())) {
			
			userRepository.delete(user);
			return true;
			
		}

		return false;
		
	}

	/**
	 * @param id of a user
	 * @return 
	 */
	public boolean deleteAExistingUserByTheId(int id) {
		
		log.info("Delete an user in the database with id : {}", id);
		
		if(userRepository.existsById(id)) {
			
			userRepository.deleteById(id);
			return true;
			
		}

		return false;
		
	}

	/**
	 * @param The user id
	 * @return The connection list of user
	 */
	public List<UserDB> getConnectionsOfAnUser(int id) {
		
		log.info("Fetching all the connections of an user with id : {} .", id);
		
		//Fetching the user
		UserDB user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		List<UserDB> connections = new ArrayList<UserDB>();
		
		//Add user's connections in a List
		user.getConnections().forEach(connection -> {
			connections.add(connection);
		});
		
		return connections;
	}

	/**
	 * @param The user id
	 * @param The user emailUserConnection for search
	 * @return The new List of connection
	 */
	@Transactional
	public List<UserDB> addForAnUserANewConnectionWithEmail(int id, String email) {
		
		log.info("Add a new connection with the email address : {} of an user with id : {} .", email, id);
		
		//Fetching user and user connection
		UserDB user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		UserDB connection = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
		
		if(user.getConnections().contains(connection)) {
			throw new IllegalArgumentException("Connection already exists.");
		}
		
		//Add connection in the user's connections List
		user.addConnections(connection);
		
		//Save the entity
		userRepository.save(user);
		
		return getConnectionsOfAnUser(id);
	}

	/**
	 * @param The user id
	 * @param The user connectionId
	 * @return
	 */
	public List<UserDB> deleteForAnUserAConnectionInHisList(int id, int connectionId) {
		
		log.info("Delete the connection with id : {} of an user with id : {} .", connectionId , id);
		
		//Fetching user 
		UserDB user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		
		UserDB connectionToRemove = null;
		//Removing connection
		for(UserDB connection : user.getConnections()){
			
			if(connection.getId() == connectionId) {
				
				connectionToRemove = connection;
				
			}
			
		};
		user.removeConnection(connectionToRemove);
		
		//Save the entity
		userRepository.save(user);
		
		return getConnectionsOfAnUser(id);
	}
	
}
