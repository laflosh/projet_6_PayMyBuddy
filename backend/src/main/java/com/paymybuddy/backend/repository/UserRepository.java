package com.paymybuddy.backend.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.backend.model.UserDB;

@Repository
public interface UserRepository extends CrudRepository<UserDB, Integer> {

	public UserDB findByUsername(String string);

	public Optional<UserDB> findByEmail(String emailUserConnection);
	
}
