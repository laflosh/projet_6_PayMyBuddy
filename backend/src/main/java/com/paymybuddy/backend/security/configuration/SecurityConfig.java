package com.paymybuddy.backend.security.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.paymybuddy.backend.security.service.CustomUserDetailsService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;

    // Configuration des rôles et accès par URL
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
        http.cors(Customizer.withDefaults())
        	.csrf(csrf -> csrf.disable()) //à retirer
        	.authorizeHttpRequests(auth -> auth
        		.requestMatchers("/login","/api/**").permitAll()
        		.requestMatchers("/user").hasRole("USER")
                .anyRequest().authenticated()// Authentification pour toutes les autres requêtes
            )
        	.formLogin((formLogin) -> formLogin
                    .successHandler(new AuthenticationSuccessHandler() {
						@Override
						public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
								Authentication authentication) throws IOException, ServletException {

					        response.setStatus(HttpServletResponse.SC_OK);
					        response.setContentType("application/json");
					        
					        response.getWriter().write("{\"message\": \"Authentication successful\"}");
						}
                    }))
        	.logout((logout) -> logout
        			.permitAll());

        return http.build();
        
    }

    // Gestionnaire d'authentification en mémoire avec BCryptPasswordEncoder pour le cryptage de mots de passe
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
    	
        return new BCryptPasswordEncoder();
        
    }

    // Bean d'AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception {
    	
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(customUserDetailsService).passwordEncoder(bCryptPasswordEncoder);
        
        return authenticationManagerBuilder.build();
        
    }
}