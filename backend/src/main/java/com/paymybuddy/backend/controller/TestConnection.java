package com.paymybuddy.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestConnection {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void TestController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("/test-connexion")
    public String testConnexion() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return "Connexion à la base de données réussie !";
        } catch (Exception e) {
            return "Erreur de connexion : " + e.getMessage();
        }
    }
	
}
