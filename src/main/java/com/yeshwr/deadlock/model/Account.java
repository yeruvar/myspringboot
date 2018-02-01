package com.yeshwr.deadlock.model;

/**
 * Bank Account class
 * 
 * @author yeshwr
 *
 */
public class Account {

	private String id;
	private double balance;

	public Account(String id, double balance) {
		this.id = id;
		this.balance = balance;
	}

	public void withdraw(double amount) {
		balance -= amount;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
}
