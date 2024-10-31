package com.paymybuddy.backend.model.dtos;

public class TransactionDTO {

	private Integer id;
	
	private int senderId;
	
	private int receiverId;
	
	private String description;
	
	private double amont;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
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
