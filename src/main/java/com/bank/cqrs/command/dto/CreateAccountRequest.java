package com.bank.cqrs.command.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CreateAccountRequest {

	private BigDecimal stratingBalance;

	public BigDecimal getStratingBalance() {
		return stratingBalance;
	}

	public void setStratingBalance(BigDecimal stratingBalance) {
		this.stratingBalance = stratingBalance;
	}
}
