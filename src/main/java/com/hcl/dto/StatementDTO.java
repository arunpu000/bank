package com.hcl.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


public class StatementDTO {
	private long transactionId;

	private Date transactionDate;

	private long accountNo;

	private Double amount;

	private Double currentBal;

	private String credit_debit;

	private String message;

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public long getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getCurrentBal() {
		return currentBal;
	}

	public void setCurrentBal(Double currentBal) {
		this.currentBal = currentBal;
	}

	public String getCredit_debit() {
		return credit_debit;
	}

	public void setCredit_debit(String credit_debit) {
		this.credit_debit = credit_debit;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StatementDTO(long transactionId,
			Date transactionDate, long accountNo, Double amount,
			Double currentBal, String credit_debit, String message) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.accountNo = accountNo;
		this.amount = amount;
		this.currentBal = currentBal;
		this.credit_debit = credit_debit;
		this.message = message;
	}

	public StatementDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
