package com.bank.cqrs.command.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DepositeRequest {
	private String accountId;
	public String getAccountId() {
		return accountId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	private BigDecimal amount;
	

}
