package com.hcl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/*import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;*/
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account")
public class Account {
	@Id
	@SequenceGenerator(name = "account_id", sequenceName = "account_sequence", initialValue = 200, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id")
	@Column(name = "id")
	private long accountId;
	
	private long accountNumber;
	private String ifsccode;
	private Double openingBalance;
	private Double currentBalance;
	private String accountType;
	private String branchAddress;

	@OneToOne
	@JoinColumn(name = "customerId")
	private Customer customerDetails;

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getIfsccode() {
		return ifsccode;
	}

	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}

	public Double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(Double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public Double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public Customer getCustomerDetails() {
		return customerDetails;
	}

	public void setCustomerDetails(Customer customerDetails) {
		this.customerDetails = customerDetails;
	}

	public Account(long accountId, long accountNumber, String ifsccode,
			Double openingBalance, Double currentBalance, String accountType,
			String branchAddress, Customer customerDetails) {
		super();
		this.accountId = accountId;
		this.accountNumber = accountNumber;
		this.ifsccode = ifsccode;
		this.openingBalance = openingBalance;
		this.currentBalance = currentBalance;
		this.accountType = accountType;
		this.branchAddress = branchAddress;
		this.customerDetails = customerDetails;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	
}
