package com.paymybuddy.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.backend.model.UserDB;

@Repository
public interface UserRepository extends CrudRepository<UserDB, Integer> {

	public UserDB findByUsername(String string);

	public UserDB findByEmail(String emailUserConnection);

}
