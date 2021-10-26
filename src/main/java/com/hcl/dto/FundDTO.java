package com.hcl.dto;


import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class FundDTO {

	@NotNull(message="please enter fromaccount")
	@Positive(message="please enter postive value")
	private long fromAccount;
	
	@NotNull(message="please enter toaccount")
	@Positive(message="please enter postive value")
	private long toAccount;
	
	@NotNull(message="please enter amount")
	@Positive(message="please enter postive value")
	@Min(100)
	@Max(9000)
	private Double amount;
	
	private String remarks;

	private String transferMoney;
	
	public long getFromAccount() {
		return fromAccount;
	}

	public void setFromAccount(long fromAccount) {
		this.fromAccount = fromAccount;
	}

	public long getToAccount() {
		return toAccount;
	}

	public void setToAccount(long toAccount) {
		this.toAccount = toAccount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public FundDTO(
			@NotNull(message = "please enter fromaccount") @Positive(message = "please enter postive value") long fromAccount,
			@NotNull(message = "please enter toaccount") @Positive(message = "please enter postive value") long toAccount,
			@NotNull(message = "please enter amount") @Positive(message = "please enter postive value") @Min(100) @Max(9000) Double amount,
			String remarks,String transferMoney) {
		super();
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.amount = amount;
		this.remarks = remarks;
	}

	public FundDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTransferMoney() {
		return transferMoney;
	}

	public void setTransferMoney(String transferMoney) {
		this.transferMoney = transferMoney;
	}

	
}
