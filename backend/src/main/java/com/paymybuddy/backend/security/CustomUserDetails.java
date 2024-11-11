package com.paymybuddy.backend.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@SuppressWarnings("serial")
public class CustomUserDetails extends User {

	private int id;
	
	public CustomUserDetails(int id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.id = id;
	
	}
	
	public int getId() {
		return id;
	}

}
