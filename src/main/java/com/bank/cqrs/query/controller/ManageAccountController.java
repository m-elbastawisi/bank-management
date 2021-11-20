package com.bank.cqrs.query.controller;

import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.cqrs.query.entity.Account;
import com.bank.cqrs.query.query.FindAccountByIdQuery;

@RestController
public class ManageAccountController {

	private final QueryGateway queryGateway ;
	public ManageAccountController(QueryGateway queryGateway) {
		super();
		this.queryGateway = queryGateway;
	}
	@GetMapping(value ="get-account")
	public ResponseEntity<Account> getAccount(@RequestParam String id){
		
		Account account = queryGateway.query(new FindAccountByIdQuery(id), Account.class).join();
		if(account == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(account , HttpStatus.OK);
	}
}
