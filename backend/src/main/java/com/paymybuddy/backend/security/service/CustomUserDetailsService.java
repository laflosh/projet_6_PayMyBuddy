package com.paymybuddy.backend.security.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paymybuddy.backend.model.UserDB;
import com.paymybuddy.backend.repository.UserRepository;
import com.paymybuddy.backend.security.model.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private Logger log = LogManager.getLogger(CustomUserDetailsService.class); 
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		UserDB user = userRepository.findByEmail(email);
		
		if(user == null) {
			log.error("User not found with email : {} ", email);
			throw new UsernameNotFoundException("User not found");
		}
			
		log.info("User charged with email : {} ", email);
		return new CustomUserDetails(
				user.getId(),
				user.getEmail(), 
				user.getPassword(), 
				getGrantedAuthority("USER"));
	}
	
	private List<GrantedAuthority> getGrantedAuthority(String role){
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		
		return authorities;
		
	}

}
