package com.bank.cqrs.query.query;

import lombok.Data;

@Data
public class FindAccountByIdQuery {
	private String accountId;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public FindAccountByIdQuery(String accountId) {
		super();
		this.accountId = accountId;
	}
	
	

}
