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
	 * @return
	 */
	public Iterable<Transaction> getAllTransactions() {
		
		return transactionRepository.findAll();
		
	}

	/**
	 * @param id
	 * @return
	 */
	public Optional<Transaction> getOneTransactionById(int id) {
		
		if(transactionRepository.existsById(id)) {
			
			return transactionRepository.findById(id);
			
		}
		
		return null;
	}

	/**
	 * @param transactionDTO
	 * @return
	 */
	@Transactional
	public Transaction addANewTransaction(TransactionDTO transactionDTO) {
		
		User sender = userRepository.findById(transactionDTO.getSenderId())
				.orElseThrow(() -> new RuntimeException("Sender not found."));
		User receiver = userRepository.findById(transactionDTO.getReceiverId())
				.orElseThrow(() -> new RuntimeException("Receiver not found."));
		
		Transaction transaction = new Transaction();
		transaction.setSender(sender);
		transaction.setReceiver(receiver);
		transaction.setDescription(transactionDTO.getDescription());
		transaction.setAmont(transactionDTO.getAmont()); 
		
		return transactionRepository.save(transaction);
		
	}

	@Transactional
	public Transaction updateAExistingTransaction(TransactionDTO transactionDTO) {
		
		User sender = userRepository.findById(transactionDTO.getSenderId())
				.orElseThrow(() -> new RuntimeException("Sender not found."));
		User receiver = userRepository.findById(transactionDTO.getReceiverId())
				.orElseThrow(() -> new RuntimeException("Receiver not found."));
		
		Transaction transaction = new Transaction();
		transaction.setId(transactionDTO.getId());
		transaction.setSender(sender);
		transaction.setReceiver(receiver);
		transaction.setDescription(transactionDTO.getDescription());
		transaction.setAmont(transactionDTO.getAmont()); 
		
		return transactionRepository.save(transaction);
	}
	
}
