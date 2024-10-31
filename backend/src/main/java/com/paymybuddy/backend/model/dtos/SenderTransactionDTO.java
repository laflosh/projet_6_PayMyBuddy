package com.paymybuddy.backend.model.dtos;

import com.paymybuddy.backend.model.User;

public class SenderTransactionDTO {

	private int transactionId;
	
	private User receiver;
	
	private String description;
	
	private double amont;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
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
