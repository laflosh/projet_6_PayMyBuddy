package com.paymybuddy.backend.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
@DynamicUpdate
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@ManyToMany(
			fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
				}
			)
	@JoinTable(
			name = "user_connections",
			joinColumns = @JoinColumn(name = "id_user"),
			inverseJoinColumns = @JoinColumn(name = "id_user_connection")
			)
	@JsonIgnoreProperties({"connections", "transactionSender", "transactionReceiver"})
	private List<User> connections = new ArrayList<User>();

	@OneToMany(
			mappedBy = "sender",
			cascade = CascadeType.ALL
			)
	@JsonIgnoreProperties({"sender"})
	private List<Transaction> transactionSender = new ArrayList<Transaction>();
	
	@OneToMany(
			mappedBy = "receiver",
			cascade = CascadeType.ALL
			)
	@JsonIgnoreProperties({"receiver"})
	private List<Transaction> transactionReceiver = new ArrayList<Transaction>();
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<User> getConnections() {
		return connections;
	}

	public void setConnections(List<User> connections) {
		this.connections = connections;
	}
	
	public List<Transaction> getTransactionSender() {
		return transactionSender;
	}

	public void setTransactionSender(List<Transaction> transactionSender) {
		this.transactionSender = transactionSender;
	}

	public List<Transaction> getTransactionReceiver() {
		return transactionReceiver;
	}

	public void setTransactionReceiver(List<Transaction> transactionReceiver) {
		this.transactionReceiver = transactionReceiver;
	}

	//Util method
	public void addConnections(User user) {
		
		connections.add(user);
		
	}
	
	public void removeConnection(User user ) {
		
		connections.remove(user);
		
	}
	
	
}
