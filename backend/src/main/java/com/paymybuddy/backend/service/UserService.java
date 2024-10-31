package com.paymybuddy.backend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.backend.model.User;
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
	public Iterable<User> getAllUsers(){
		
		log.info("Fetching all the users in the database");
		return userRepository.findAll();
		
	}

	/**
	 * @param id of the user
	 * @return An user
	 */
	public Optional<User> getOneUserById(int id) {
		
		if(userRepository.existsById(id)) {
			
			log.info("Fetching one user in the database with id :" + id);
			return userRepository.findById(id);
			
		}
		return null;
		
		
	}

	/**
	 * @param A new user
	 * @return The saved user
	 */
	public User addNewUser(User user) {

		log.info("Saving a new user in the database");
		return userRepository.save(user);
		
	}

	/**
	 * @param A modify user
	 * @return A updated user
	 */
	public User updateAExistingUser(User user) {
		
		log.info("Updating the user in the database with id :" + user.getId());
		return userRepository.save(user);
		
	}
	
	/**
	 * @param An user
	 */
	public boolean deleteAExistingUserByTheEntity(User user) {
		
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
		
		log.info("Delete an user in the database with id :" + id);
		
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
	public List<User> getConnectionsOfAnUser(int id) {
		
		log.info("Fetching all the connections of an user with id : " + id + " .");
		
		//Fetching the user
		User user = userRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("User not found"));
		
		List<User> connections = new ArrayList<User>();
		
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
	public List<User> addForAnUserANewConnectionWithEmail(int id, String email) {
		
		log.info("Add a new connection with the email address : " + email + " of an user with id :" + id +" .");
		
		//Fetching user and user connection
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		User connection = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
		
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
	public List<User> deleteForAnUserAConnectionInHisList(int id, int connectionId) {
		
		log.info("Delete the connection with id : " + connectionId + " of an user with id : " + id + " .");
		
		//Fetching user 
		User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
		
		User connectionToRemove = null;
		//Removing connection
		for(User connection : user.getConnections()){
			
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
