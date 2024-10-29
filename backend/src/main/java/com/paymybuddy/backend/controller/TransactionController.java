package com.paymybuddy.backend.controller;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymybuddy.backend.model.Transaction;
import com.paymybuddy.backend.service.TransactionService;

@RestController
@RequestMapping("/api")
public class TransactionController {

	private Logger log = LogManager.getLogger(TransactionController.class);
	
	@Autowired
	TransactionService transactionService;
	
	/**
	 * @return
	 */
	@GetMapping("/transactions")
	public ResponseEntity<Iterable<Transaction>> getAllTransactions(){
		
		Iterable<Transaction> transactions = transactionService.getAllTransactions();
		
		return ResponseEntity.ok(transactions);
		
	}
	
	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/transactions/{id}")
	public ResponseEntity<Optional<Transaction>> getOneTransactionById(@PathVariable int id){
		
		Optional<Transaction> transaction = transactionService.getOneTransactionById(id);
		
		return ResponseEntity.ok(transaction);
		
	}
}
