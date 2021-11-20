package com.bank.cqrs.command;

import java.math.BigDecimal;

public class DepositMoneyCommand extends BaseCommand<String> {

	private final BigDecimal balance ;

	public DepositMoneyCommand(String id, BigDecimal balance) {
		super(id);
		this.balance = balance;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
}
