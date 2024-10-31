package com.paymybuddy.backend.controller;

import java.util.List;
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

import com.paymybuddy.backend.model.Transaction;
import com.paymybuddy.backend.model.dtos.ReceiverTransactionDTO;
import com.paymybuddy.backend.model.dtos.SenderTransactionDTO;
import com.paymybuddy.backend.model.dtos.TransactionDTO;
import com.paymybuddy.backend.service.TransactionService;

@RestController
@RequestMapping("/api")
public class TransactionController {

	private Logger log = LogManager.getLogger(TransactionController.class);
	
	@Autowired
	TransactionService transactionService;
	
	/**
	 * Fetching all transactions in the database
	 * 
	 * @return A List of transactions
	 */
	@GetMapping("/transactions")
	public ResponseEntity<Iterable<Transaction>> getAllTransactions(){
		
		log.info("Trying to acces to all transactions in database");
		
		Iterable<Transaction> transactions = transactionService.getAllTransactions();
		
		return ResponseEntity.ok(transactions);
		
	}
	
	/**
	 * Fetching one transaction by the id
	 * 
	 * @param id of the transaction
	 * @return A transaction
	 */
	@GetMapping("/transactions/{id}")
	public ResponseEntity<Optional<Transaction>> getOneTransactionById(@PathVariable int id){
		
		log.info("Trying to acces to one transaction with id : " + id + " .");
		
		Optional<Transaction> transaction = transactionService.getOneTransactionById(id);
		
		return ResponseEntity.ok(transaction);
		
	}
	
	/**
	 * Saving a new transaction in the database
	 * 
	 * @param A new transactionDTO
	 * @return The saved transaction
	 */
	@PostMapping("/transactions")
	public ResponseEntity<Transaction> addANewTransaction(@RequestBody TransactionDTO transactionDTO){
		
		try {
			log.info("Trying to save a new transaction in the database.");
			
			Transaction savedTransaction = transactionService.addANewTransaction(transactionDTO);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(savedTransaction);
		} catch(RuntimeException e) {
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	/**
	 * Updating a transaction existing in the database.
	 * 
	 * @param The modify transactionDTO
	 * @return The updated transaction
	 */
	@PutMapping("/transactions")
	public ResponseEntity<Transaction> updateAExistingTransaction(@RequestBody TransactionDTO transactionDTO){
		
		try {
			log.info("Trying to update a transaction in the databse with id :" + transactionDTO.getId() + " ."); 
			
			Transaction updatedTransaction = transactionService.updateAExistingTransaction(transactionDTO);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(updatedTransaction);
		} catch(RuntimeException e) {
			e.printStackTrace();
			
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		
	}
	
	/**
	 * Delete an existing transaction in the database by her entity 
	 * 
	 * @param A transactionDTO
	 * @return
	 */
	@DeleteMapping("/transactions")
	public ResponseEntity<Void> deleteATransactionByTheEntity(@RequestBody TransactionDTO transactionDTO){
		
		log.info("Trying to delete a transaction in the database with her entity");
		
		boolean isDeleted = transactionService.deleteATransactionByTheEntity(transactionDTO);
		
		if(isDeleted == true) {
			
			return ResponseEntity.noContent().build();
			
		} else {
			
			return ResponseEntity.notFound().build();
			
		}
		
	}
	
	/**
	 * Delete an existing transaction in the database by the id
	 * 
	 * @param The transaction id
	 * @return
	 */
	@DeleteMapping("/transactions/{id}")
	public ResponseEntity<Void> deleteATransactionByTheId(@PathVariable int id){
		
		log.info("Trying to delete a transaction in the database with id : " + id + " .");
		
		boolean isDeleted = transactionService.deleteATransactionByTheId(id);
		
		if(isDeleted == true) {
			
			return ResponseEntity.noContent().build();
			
		} else {
			
			return  ResponseEntity.notFound().build();
			
		}
		
	}
	
	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/transactions/sender/{id}")
	public ResponseEntity<List<SenderTransactionDTO>> getSenderTransactionsForAnUser(@PathVariable int id){
		
		List<SenderTransactionDTO> senderTransactions = transactionService.getSenderTransactionsForAnUser(id);
		
		return ResponseEntity.ok().body(senderTransactions);
		
	}
	
	/**
	 * @param id
	 * @return
	 */
	@GetMapping("/transactions/receiver/{id}")
	public ResponseEntity<List<ReceiverTransactionDTO>> getReceiverTransactionsOfAnUser(@PathVariable int id){
		
		List<ReceiverTransactionDTO> receiverTransactions = transactionService.getReceiverTransactionsOfAnUser(id);
		
		return ResponseEntity.ok().body(receiverTransactions);
		
	}
	
}
