package com.paymybuddy.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.backend.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}