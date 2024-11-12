package com.paymybuddy.backend.security.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paymybuddy.backend.security.model.CustomUserDetails;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{

	@Autowired
	private ObjectMapper objectMapper;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException{
		
		CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
		
		Map<String, Object> userInfo = new HashMap<String, Object>();
		userInfo.put("id", userDetails.getId());
		userInfo.put("email", userDetails.getUsername());
		userInfo.put("roles", userDetails.getAuthorities());
		userInfo.put("message", "Authentication successful");
		
        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType("application/json");
        
        response.getWriter().write(objectMapper.writeValueAsString(userInfo));
        
	}
	
}
