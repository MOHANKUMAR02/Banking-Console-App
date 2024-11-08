package com.project.bank_app.Entity;

import java.time.LocalDate;

public class Transaction {

	private LocalDate transactionDate;
	private String transferToUserId;
	private Double transationAmount;
	private String transationType;
	private Double initialBalance;
	private Double finalBalance;
	private String transactionPerformedBy;

	
	public Transaction(LocalDate transactionDate, String transferToUserId, Double transationAmount,
			String transationType, Double initialBalance, Double finalBalance, String transactionPerformedBy) {
		super();
		this.transactionDate = transactionDate;
		this.transferToUserId = transferToUserId;
		this.transationAmount = transationAmount;
		this.transationType = transationType;
		this.initialBalance = initialBalance;
		this.finalBalance = finalBalance;
		this.transactionPerformedBy = transactionPerformedBy;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransferToUserId() {
		return transferToUserId;
	}
	public void setTransferToUserId(String transferToUserId) {
		this.transferToUserId = transferToUserId;
	}
	public Double getTransationAmount() {
		return transationAmount;
	}
	public void setTransationAmount(Double transationAmount) {
		this.transationAmount = transationAmount;
	}
	public String getTransationType() {
		return transationType;
	}
	public void setTransationType(String transationType) {
		this.transationType = transationType;
	}
	public Double getInitialBalance() {
		return initialBalance;
	}
	public void setInitialBalance(Double initialBalance) {
		this.initialBalance = initialBalance;
	}
	public Double getFinalBalance() {
		return finalBalance;
	}
	public void setFinalBalance(Double finalBalance) {
		this.finalBalance = finalBalance;
	}
	public String getTransactionPerformedBy() {
		return transactionPerformedBy;
	}
	public void setTransactionPerformedBy(String transactionPerformedBy) {
		this.transactionPerformedBy = transactionPerformedBy;
	}
	
	
	@Override
	public String toString() {
		return "Transaction [transactionDate=" + transactionDate + ", transactionUserId=" + transferToUserId
				+ ", transationAmount=" + transationAmount + ", transationType=" + transationType + ", initialBalance="
				+ initialBalance + ", finalBalance=" + finalBalance + ", transactionPerformedBy="
				+ transactionPerformedBy + "]";
	}

}
