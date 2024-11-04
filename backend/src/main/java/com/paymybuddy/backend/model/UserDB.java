package com.paymybuddy.backend.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class UserDB {

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
	//@JsonIgnoreProperties({"password","connections", "transactionSender", "transactionReceiver"})
	@JsonIgnore
	private List<UserDB> connections = new ArrayList<UserDB>();

	@OneToMany(
			mappedBy = "sender",
			cascade = CascadeType.ALL
			)
	//@JsonIgnoreProperties({"sender"})
	@JsonIgnore
	private List<TransactionDB> transactionSender = new ArrayList<TransactionDB>();
	
	@OneToMany(
			mappedBy = "receiver",
			cascade = CascadeType.ALL
			)
	//@JsonIgnoreProperties({"receiver"})
	@JsonIgnore
	private List<TransactionDB> transactionReceiver = new ArrayList<TransactionDB>();
	
	
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

	public List<UserDB> getConnections() {
		return connections;
	}

	public void setConnections(List<UserDB> connections) {
		this.connections = connections;
	}
	
	public List<TransactionDB> getTransactionSender() {
		return transactionSender;
	}

	public void setTransactionSender(List<TransactionDB> transactionSender) {
		this.transactionSender = transactionSender;
	}

	public List<TransactionDB> getTransactionReceiver() {
		return transactionReceiver;
	}

	public void setTransactionReceiver(List<TransactionDB> transactionReceiver) {
		this.transactionReceiver = transactionReceiver;
	}

	//Util method
	public void addConnections(UserDB user) {
		
		connections.add(user);
		
	}
	
	public void removeConnection(UserDB user ) {
		
		connections.remove(user);
		
	}
	
	
}
