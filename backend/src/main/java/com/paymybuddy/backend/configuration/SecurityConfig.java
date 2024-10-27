package com.paymybuddy.backend.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // Configuration des utilisateurs en mémoire avec rôles TEST
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails test = User.builder()
                .username("testuser")
                .password(passwordEncoder().encode("testpassword"))
                .roles("TEST")
                .build();

        return new InMemoryUserDetailsManager(test);
    }

    // Configuration des rôles et accès par URL
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) //à retirer
        	.authorizeHttpRequests(auth -> auth
                .anyRequest().authenticated()// Authentification pour toutes les autres requêtes
            )
            .httpBasic(Customizer.withDefaults());// Authentification HTTP Basic pour les tests rapides

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
        authenticationManagerBuilder.userDetailsService(userDetailsService()).passwordEncoder(bCryptPasswordEncoder);
        return authenticationManagerBuilder.build();
    }
}
