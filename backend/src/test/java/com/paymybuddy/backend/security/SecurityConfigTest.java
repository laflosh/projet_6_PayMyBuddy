package com.paymybuddy.backend.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityConfigTest {

	@Autowired
	MockMvc mockMvc;
	
	@Test
	public void shouldReturnDefaultMessageForConnectionAndReturnIsOk() throws Exception {
		
		mockMvc.perform(MockMvcRequestBuilders.get("/login"))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(MockMvcResultMatchers.status().isOk());
		
	}
	
	@Test
	public void testingConnectionForAnUserInDatabaseAndReturnAuthenticated() throws Exception {
		
		mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin("/login")
				.user("sophie@hotmail.fr").password("123456"))
			.andExpect(SecurityMockMvcResultMatchers.authenticated());
		
	}
	
	@Test
	public void testingUserLoginFailedAndReturnUnauthenticated() throws Exception {
		
		mockMvc.perform(SecurityMockMvcRequestBuilders.formLogin("/login")
				.user("sophie@hotmail.fr").password("wrongpassword"))
			.andExpect(SecurityMockMvcResultMatchers.unauthenticated());
		
	}
	
}
