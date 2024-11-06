package com.paymybuddy.backend.model.dtos;

import com.paymybuddy.backend.model.UserDB;

public class ReceiverTransactionDTO {

	private int transactionId;
	
	private UserDB sender;
	
	private String description;
	
	private double amont;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public UserDB getSender() {
		return sender;
	}

	public void setSender(UserDB sender) {
		this.sender = sender;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmont() {
		return amont;
	}

	public void setAmont(double amont) {
		this.amont = amont;
	}
	
}