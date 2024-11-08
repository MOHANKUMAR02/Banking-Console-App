package com.project.bank_app.Entity;

import java.util.Objects;

public class User {

	private String userId;
	private String name;
	private long accountNumber;
	private String password;
	private long contact;
	private String role;
	private double accountBalance;
	private String address;


	public User(String userId, String name, long accountNumber, String password, long contact, String role,
			double accountBalance, String address) {
		super();
		this.userId = userId;
		this.name = name;
		this.accountNumber = accountNumber;
		this.password = password;
		this.contact = contact;
		this.role = role;
		this.accountBalance = accountBalance;
		this.address = address;
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getContact() {
		return contact;
	}
	public void setContact(long contact) {
		this.contact = contact;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public double getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", Name=" + name + ", accountNumber=" + accountNumber + ", password="
				+ password + ", contact=" + contact + ", role=" + role + ", accountBalance=" + accountBalance
				+ ", address=" + address + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(contact, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return contact == other.contact && Objects.equals(userId, other.userId);
	}

}
