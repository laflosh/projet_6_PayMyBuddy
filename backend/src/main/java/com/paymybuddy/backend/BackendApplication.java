package com.paymybuddy.backend;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	private final static Logger log = LogManager.getLogger("BackendApplication");
	
	public static void main(String[] args) {
		log.info("Initalizing API PayMyBuddy");
		SpringApplication.run(BackendApplication.class, args);
	}

}
