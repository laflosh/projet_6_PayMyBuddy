package com.paymybuddy.backend.user;

import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymybuddy.backend.model.UserDB;
import com.paymybuddy.backend.repository.UserRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	private static final String TEST_USER_PREFIX = "testuser_";
    private List<UserDB> createdTestUsers = new ArrayList<>();
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	public MockMvc mockMvc;
	
	@BeforeEach
	private void setUp(){
		//Creating an user test
		UserDB testUser = new UserDB();
		testUser.setUsername(TEST_USER_PREFIX + "1");
		testUser.setEmail("test@test.fr");
		testUser.setPassword("password");
		
		//Save it in database and add it in a list to acces
		userRepository.save(testUser);
		createdTestUsers.add(testUser);
	}
	
	@AfterEach
	public void tearDown() {
		//Delete all the test entity in database
		createdTestUsers.forEach(user -> {
			if(userRepository.existsById(user.getId())) {
				userRepository.deleteById(user.getId());
			}
		});
		createdTestUsers.clear();
	}
	
	@Test
	@WithMockUser
	public void getAllUsersInDataBaseAndReturnOk() throws Exception {
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders.get("/api/users"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.not(Matchers.empty())));
	}
	
	@Test
	@WithMockUser
	public void getOneUserInDataBaseAndReturnOk() throws Exception {
		//Getting the user to fetch in database
		UserDB testUser = createdTestUsers.get(0);
		
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders.get("/api/users/" + testUser.getId()))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.not(Matchers.empty())));
	}
	
	@Test
	@WithMockUser
	public void addANewUserInTheDatabaseAndReturnCreated() throws Exception {
		//Create a new user test entity
		UserDB newUser = new UserDB();
		newUser.setUsername(TEST_USER_PREFIX + "2");
		newUser.setEmail("essaie@essaie.fr");
		newUser.setPassword("password");
		
		String userAsString =  objectMapper.writeValueAsString(newUser);
		
		//Testing the request
		mockMvc.perform(MockMvcRequestBuilders.post("/api/users")
				.contentType(MediaType.APPLICATION_JSON).content(userAsString))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isCreated())
		.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.not(Matchers.empty())));
		
		createdTestUsers.add(userRepository.findByUsername(TEST_USER_PREFIX + "2"));
	}
	
	@Test
	@WithMockUser
	public void updateAExistingUserAndReturnIsCreated() throws Exception {
		//Fetching the user to be modifiy
		UserDB testUser = createdTestUsers.get(0);
		
		testUser.setEmail("try@try.fr");
		testUser.setPassword("pass");
		
		String userAsString =  objectMapper.writeValueAsString(testUser);
		
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders.put("/api/users")
				.contentType(MediaType.APPLICATION_JSON).content(userAsString))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.not(Matchers.empty())));
	}
	
	@Test
	@WithMockUser
	public void deleteAExistingUserInDatabaseAndReturnNoContent() throws Exception {
		//Fetching user entity to be delete
		UserDB testUser = createdTestUsers.get(0);
		
		String userAsString =  objectMapper.writeValueAsString(testUser);
		
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/users")
				.contentType(MediaType.APPLICATION_JSON).content(userAsString))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	@Test
	@WithMockUser
	public void deleteAExistingUserWithTheIdInDatabaseAndReturnNoContent() throws Exception {
		UserDB testUser = createdTestUsers.get(0);
		
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders.delete("/api/users/" + testUser.getId()))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isNoContent());
	}
	
	@Test
	@WithMockUser
	public void getForAnUserTheConnectionsListAndReturnOk() throws Exception {
		
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders.get("/api/users/"+ 1 +"/connections"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.not(Matchers.empty())));
		
	}
	
	@Test
	@WithMockUser
	public void addAConnectionInTheUserConnectionListAndReturnCreated() throws Exception{
		
		//Create a second test user in database
		addANewUserInTheDatabaseAndReturnCreated();
		
		//testing request
		mockMvc.perform(MockMvcRequestBuilders
				.post("/api/users/"+ createdTestUsers.get(0).getId() +"/connections")
				.contentType(MediaType.TEXT_PLAIN).content("essaie@essaie.fr"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.not(Matchers.empty())));
		
	}
	
	@Test
	@WithMockUser
	public void deleteAConnectionInTheUserConnectionListAndReturnNoContent() throws Exception {
		
		addAConnectionInTheUserConnectionListAndReturnCreated();
		
		//Testing request
		mockMvc.perform(MockMvcRequestBuilders
				.delete("/api/users/" + createdTestUsers.get(0).getId() + "/connections/" + createdTestUsers.get(1).getId()))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isNoContent());
		
	}

	
}
