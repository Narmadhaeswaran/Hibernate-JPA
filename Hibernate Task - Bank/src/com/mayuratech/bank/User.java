package com.mayuratech.bank;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;


@Entity
public class User {
	
	

	@Id
	
	private int id;
	
	
	private String name;
	
	
	private String accountNumber;
	

	private String password;
	

	private float balance;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String name, String accountNumber, String password, float balance) {
		
		this.name = name;
		this.accountNumber = accountNumber;
		this.password = password;
		this.balance = balance;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	
}
