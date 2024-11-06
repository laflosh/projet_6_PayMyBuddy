package com.paymybuddy.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymybuddy.backend.model.dtos.ReceiverTransactionDTO;
import com.paymybuddy.backend.model.dtos.SenderTransactionDTO;
import com.paymybuddy.backend.model.TransactionDB;
import com.paymybuddy.backend.model.UserDB;
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
	public Iterable<TransactionDB> getAllTransactions() {
		
		log.info("Fetching all the transaction int the database");
		
		return transactionRepository.findAll();
		
	}

	/**
	 * @param The id of the transaction
	 * @return A transaction
	 */
	public TransactionDB getOneTransactionById(int id) {
		
		log.info("Fetching one transaction in the database with id : {} .", id);
			
		return transactionRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Transaction not found"));
			
	}

	/**
	 * @param The new transactionDTO
	 * @return The saved transaction
	 */
	@Transactional
	public TransactionDB addANewTransaction(TransactionDTO transactionDTO) {
		
		log.info("Saving a transaction in the database");
		
		TransactionDB transaction = transferTransactionDTOToTransaction(transactionDTO);
		
		return transactionRepository.save(transaction);
		
	}

	/**
	 * @param The modify transactionDTO
	 * @return The updated transaction
	 */
	@Transactional
	public TransactionDB updateAExistingTransaction(TransactionDTO transactionDTO) {
		
		log.info("Updating a transaction in the database with id : {} .", transactionDTO.getId());
		
		TransactionDB transaction = transferTransactionDTOToTransaction(transactionDTO);
		
		return transactionRepository.save(transaction);
	}

	/**
	 * @param the transactionDTO
	 * @return
	 */
	@Transactional
	public boolean deleteATransactionByTheEntity(TransactionDTO transactionDTO) {

		log.info("Delete a existing transaction in the database with the entity.");
		
		TransactionDB transaction = transferTransactionDTOToTransaction(transactionDTO);
		
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
		
		log.info("Delete a existing transaction in the database with id : {} .", id);
		
		if(transactionRepository.existsById(id)) {
			
			transactionRepository.deleteById(id);
			return true;
			
		}
		
		return false;
	}
	
	/**
	 * @param The user id
	 * @return A List of SenderTransaction
	 */
	@Transactional
	public List<SenderTransactionDTO> getSenderTransactionsForAnUser(int id) {
		
		log.info("Fetching all transaction in the database where the user with id : {} is the sender.", id);
		
		UserDB user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Sender not found."));
		
		List<SenderTransactionDTO> senderTransactionsList = new ArrayList<SenderTransactionDTO>();
		
		user.getTransactionSender().forEach(transaction ->{
			SenderTransactionDTO senderTransactionDTO = new SenderTransactionDTO();
			senderTransactionDTO.setTransactionId(transaction.getId());
			senderTransactionDTO.setReceiver(transaction.getReceiver());
			senderTransactionDTO.setDescription(transaction.getDescription());
			senderTransactionDTO.setAmont(transaction.getAmont());
			
			senderTransactionsList.add(senderTransactionDTO);
		});
		
		return senderTransactionsList;
	}
	
	/**
	 * @param The user id
	 * @return A List of ReceiverTransaction
	 */
	public List<ReceiverTransactionDTO> getReceiverTransactionsOfAnUser(int id) {
		
		log.info("Fetching all transaction in the database where the user with id : {} is the receiver.", id);
		
		UserDB user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Sender not found."));
		
		List<ReceiverTransactionDTO> receiverTransactionsList = new ArrayList<ReceiverTransactionDTO>();
		
		user.getTransactionReceiver().forEach(transaction -> {
			ReceiverTransactionDTO receiverTransactionDTO = new ReceiverTransactionDTO();
			receiverTransactionDTO.setTransactionId(transaction.getId());
			receiverTransactionDTO.setSender(transaction.getSender());
			receiverTransactionDTO.setDescription(transaction.getDescription());
			receiverTransactionDTO.setAmont(transaction.getAmont());
			
			receiverTransactionsList.add(receiverTransactionDTO);
		});
		
		return receiverTransactionsList;
	}
	
	/**
	 * Method for data transfer transactionDTO to a new entity transaction
	 * 
	 * @param transactionDTO
	 * @return A transaction
	 */
	public TransactionDB transferTransactionDTOToTransaction(TransactionDTO transactionDTO) {
		
		log.info("Data transfer transactionDTO to a new entity Transaction");
		
		UserDB sender = userRepository.findById(transactionDTO.getSenderId())
				.orElseThrow(() -> new RuntimeException("Sender not found."));
		UserDB receiver = userRepository.findById(transactionDTO.getReceiverId())
				.orElseThrow(() -> new RuntimeException("Receiver not found."));
		
		TransactionDB transaction = new TransactionDB();
		
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
