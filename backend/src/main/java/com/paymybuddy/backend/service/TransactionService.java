package com.paymybuddy.backend.service;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.backend.model.Transaction;
import com.paymybuddy.backend.model.User;
import com.paymybuddy.backend.model.dtos.TransactionDTO;
import com.paymybuddy.backend.repository.TransactionRepository;
import com.paymybuddy.backend.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionService {

	private static Logger log = LogManager.getLogger(TransactionService.class);
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	TransactionRepository transactionRepository;

	/**
	 * @return A List of transactions
	 */
	public Iterable<Transaction> getAllTransactions() {
		
		log.info("Fetching all the transaction int the database");
		
		return transactionRepository.findAll();
		
	}

	/**
	 * @param The id of the transaction
	 * @return A transaction
	 */
	public Optional<Transaction> getOneTransactionById(int id) {
		
		log.info("Fetching one transaction in the database with id : " + id +" .");
		
		if(transactionRepository.existsById(id)) {
			
			return transactionRepository.findById(id);
			
		}
		
		return null;
	}

	/**
	 * @param The new transactionDTO
	 * @return The saved transaction
	 */
	@Transactional
	public Transaction addANewTransaction(TransactionDTO transactionDTO) {
		
		log.info("Saving a transaction in the database");
		
		Transaction transaction = transferTransactionDTOToTransaction(transactionDTO);
		
		return transactionRepository.save(transaction);
		
	}

	/**
	 * @param The modify transactionDTO
	 * @return The updated transaction
	 */
	@Transactional
	public Transaction updateAExistingTransaction(TransactionDTO transactionDTO) {
		
		log.info("Updating a transaction in the database with id :" + transactionDTO.getId() + " .");
		
		Transaction transaction = transferTransactionDTOToTransaction(transactionDTO);
		
		return transactionRepository.save(transaction);
	}

	/**
	 * @param the transactionDTO
	 * @return
	 */
	@Transactional
	public boolean deleteATransactionByTheEntity(TransactionDTO transactionDTO) {

		log.info("Delete a existing transaction in the database with the entity.");
		
		Transaction transaction = transferTransactionDTOToTransaction(transactionDTO);
		
		if(transactionRepository.existsById(transaction.getId())) {
			
			transactionRepository.delete(transaction);
			return true;
			
		}
		
		return false;
	}

	/**
	 * @param The id of the transaction 
	 * @return
	 */
	public boolean deleteATransactionByTheId(int id) {
		
		log.info("Delete a existing transaction in the database with id :" + id + " .");
		
		if(transactionRepository.existsById(id)) {
			
			transactionRepository.deleteById(id);
			return true;
			
		}
		
		return false;
	}
	
	/**
	 * Method for datat transfer transactionDTO to a new entity transaction
	 * 
	 * @param transactionDTO
	 * @return A transaction
	 */
	@Transactional
	public Transaction transferTransactionDTOToTransaction(TransactionDTO transactionDTO) {
		
		log.info("Data transfer transactionDTO to a new entity Transaction");
		
		User sender = userRepository.findById(transactionDTO.getSenderId())
				.orElseThrow(() -> new RuntimeException("Sender not found."));
		User receiver = userRepository.findById(transactionDTO.getReceiverId())
				.orElseThrow(() -> new RuntimeException("Receiver not found."));
		
		Transaction transaction = new Transaction();
		
		if(transactionDTO.getId() != null) {
			transaction.setId(transactionDTO.getId());
		}
		transaction.setSender(sender);
		transaction.setReceiver(receiver);
		transaction.setDescription(transactionDTO.getDescription());
		transaction.setAmont(transactionDTO.getAmont()); 
		
		return transaction;
		
	}
	
}
