package com.paymybuddy.backend.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.backend.model.Transaction;
import com.paymybuddy.backend.repository.TransactionRepository;

@Service
public class TransactionService {

	private static Logger log = LogManager.getLogger(TransactionService.class);
	
	@Autowired
	TransactionRepository transactionRepository;

	public Iterable<Transaction> getAllTransactions() {
		
		return transactionRepository.findAll();
		
	}
	
}
