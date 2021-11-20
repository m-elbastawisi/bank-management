package com.bank.cqrs.command;

import java.math.BigDecimal;

public class WithdrawMoneyCommand extends BaseCommand<String> {

	private final BigDecimal balance ;

	public WithdrawMoneyCommand(String id, BigDecimal balance) {
		super(id);
		this.balance = balance;
	}
	
	public BigDecimal getBalance() {
		return balance;
	}
	
}
