package com.paymybuddy.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.paymybuddy.backend.model.TransactionDB;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionDB, Integer>{

	TransactionDB findByDescription(String string);

}
