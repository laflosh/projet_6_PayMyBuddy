package com.paymybuddy.backend.model.dtos;

import com.paymybuddy.backend.model.UserDB;

public class SenderTransactionDTO {

	private int transactionId;

	private UserDB receiver;

	private String description;

	private double amount;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public UserDB getReceiver() {
		return receiver;
	}

	public void setReceiver(UserDB receiver) {
		this.receiver = receiver;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
