package com.paymybuddy.backend.transaction;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymybuddy.backend.model.TransactionDB;
import com.paymybuddy.backend.model.dtos.TransactionDTO;
import com.paymybuddy.backend.repository.TransactionRepository;
import com.paymybuddy.backend.service.TransactionService;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

	private static final String TEST_TRANSACTION_PREFIX = "testtransaction_";
    private List<TransactionDB> createdTestTransactions = new ArrayList<>();
	
	@Autowired
	TransactionRepository transactionRepository;
	
	@Autowired
	TransactionService transactionService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	MockMvc mockMvc;
	
	@BeforeEach
	public void setUp() {
		
		//Creation entity for test
		TransactionDTO testTransactionDTO = new TransactionDTO();
		testTransactionDTO.setSenderId(1);
		testTransactionDTO.setReceiverId(2);
		testTransactionDTO.setDescription(TEST_TRANSACTION_PREFIX + "1");
		testTransactionDTO.setAmount(100);
		
		//Saving the test entity in the database
		TransactionDB transaction = transactionService.addANewTransaction(testTransactionDTO);
		//Put it in a List to have acces at the entity
		createdTestTransactions.add(transaction);
		
	}
	
	@AfterEach
	public void tearDown() {
		
		//Clearing the database with test entity
		createdTestTransactions.forEach(transaction -> {
			if(transactionRepository.existsById(transaction.getId())) {
				transactionRepository.deleteById(transaction.getId());
			}
		});
		createdTestTransactions.clear();
	}
	
	@Test
	@WithMockUser
	public void getAllTRansactionsAndReturnOk() throws Exception {
		
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders.get("/api/transactions"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.not(Matchers.empty())));
	}
	
	@Test
	@WithMockUser
	public void getOneTransactionAndReturnOk() throws Exception {
		
		//Getting the entity to fetch in the database
		TransactionDB testTransaction = createdTestTransactions.get(0);
		
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders.get("/api/transactions/" + testTransaction.getId()))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.not(Matchers.empty())));
		
		
	}
	
	@Test
	@WithMockUser
	public void addANewTransactionAndReturnCreated() throws Exception {
		//Creation DTO transaction
		TransactionDTO newTransactionDTO = new TransactionDTO();
		newTransactionDTO.setSenderId(1);
		newTransactionDTO.setReceiverId(2);
		newTransactionDTO.setDescription(TEST_TRANSACTION_PREFIX + "2");
		newTransactionDTO.setAmount(100);
		
		String transactionDTOAsString = objectMapper.writeValueAsString(newTransactionDTO);
		
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions")
				.contentType(MediaType.APPLICATION_JSON).content(transactionDTOAsString))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.not(Matchers.empty())));
		
		createdTestTransactions.add(transactionRepository.findByDescription(TEST_TRANSACTION_PREFIX + "2"));
		
		
	}
	
	@Test
	@WithMockUser
	public void sendingWrongEntityForCreateANewTransactionAndReturnBadRequest() throws Exception {
		
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders.post("/api/transactions"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	@WithMockUser
	public void updateAExistingTransactionAndReturnCreated() throws Exception {
		
		//Getting entity to update and setting modification
		TransactionDB testTransaction = createdTestTransactions.get(0);
		TransactionDTO testTransactionDTO = new TransactionDTO();
		testTransactionDTO.setId(testTransaction.getId());
		testTransactionDTO.setSenderId(testTransaction.getSender().getId());
		testTransactionDTO.setReceiverId(testTransaction.getReceiver().getId());
		testTransactionDTO.setAmount(85);
		
		String transactionDTOAsString = objectMapper.writeValueAsString(testTransactionDTO);
		
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders.put("/api/transactions")
				.contentType(MediaType.APPLICATION_JSON).content(transactionDTOAsString))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.not(Matchers.empty())));
	}
	
	@Test
	@WithMockUser
	public void sendingWrongEntityForUpdateATransactionAndReturnBadRequest() throws Exception {
		
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders.put("/api/transactions"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
	}
	
	@Test
	@WithMockUser
	public void deleteAExistingTransactionWithTheEntityAndReturnNoContent() throws Exception {
		
		//Getting the entity and setting the dto
		TransactionDB testTransaction = createdTestTransactions.get(0);
		TransactionDTO testTransactionDTO = new TransactionDTO();
		testTransactionDTO.setId(testTransaction.getId());
		testTransactionDTO.setSenderId(testTransaction.getSender().getId());
		testTransactionDTO.setReceiverId(testTransaction.getReceiver().getId());
		testTransactionDTO.setDescription(testTransaction.getDescription());
		testTransactionDTO.setAmount(testTransaction.getAmount());
		
		String transactionDTOAsString = objectMapper.writeValueAsString(testTransactionDTO);
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/transactions")
				.contentType(MediaType.APPLICATION_JSON).content(transactionDTOAsString))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isNoContent());
		
	}
	
	@Test
	@WithMockUser
	public void sendingWrongEntityForDeleteAndReturnNotFound() throws Exception {
		
		//Transaction entity to be delete
		TransactionDTO newTransactionDTO = new TransactionDTO();
		newTransactionDTO.setId(0);
		newTransactionDTO.setSenderId(1);
		newTransactionDTO.setReceiverId(2);
		
		String transactionAsString =  objectMapper.writeValueAsString(newTransactionDTO);
		
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/transactions" )
				.contentType(MediaType.APPLICATION_JSON).content(transactionAsString))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isNotFound());
		
	}
	
	@Test
	@WithMockUser
	public void deleteAExistingTTransactionWithTheId() throws Exception {
		
		//Getting the entity to delete
		TransactionDB testTransaction = createdTestTransactions.get(0);
		
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/transactions/" + testTransaction.getId()))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isNoContent());
		
		
	}
	
	@Test
	@WithMockUser
	public void sendingWrongIdNumberForDeleteAndReturnNotFound() throws Exception {
		
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/transactions/" + 0))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isNotFound());
		
	}
	
	@Test
	@WithMockUser
	public void getAllSenderTransactionsOfAnUserAndReturnOk() throws Exception {
		
		TransactionDB transaction = createdTestTransactions.get(0);
		
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders.get("/api/transactions/sender/" + transaction.getSender().getId()))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.not(Matchers.empty())));
		
		
	}
	
	@Test
	@WithMockUser
	public void getAllReceiverTransactionsOfAnUserAndReturnOk() throws Exception {
		
		TransactionDB transaction = createdTestTransactions.get(0);
		
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders.get("/api/transactions/receiver/" + transaction.getReceiver().getId()))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.not(Matchers.empty())));
		
		
	}
	
}
