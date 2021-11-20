package com.bank.cqrs.controller;

import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.cqrs.command.dto.CreateAccountRequest;
import com.bank.cqrs.command.dto.DepositeRequest;
import com.bank.cqrs.command.dto.WithdrawalRequest;
import com.bank.cqrs.command.service.AccountCommandService;

@RestController
@RequestMapping(value = "/bank-account")
public class BankAccountController {

	
	private final AccountCommandService accountCommandService;
	
	
	public BankAccountController(AccountCommandService accountCommandService) {
		super();
		this.accountCommandService = accountCommandService;
	}

	@PostMapping(value="/create")
	public ResponseEntity<String> createAccount(@RequestBody CreateAccountRequest request){
		
		try {
				CompletableFuture<String> response = 
							accountCommandService.createAccount(request);
				
				return new ResponseEntity<>(response.get() , HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>("an Error Occure " , HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@PutMapping(value="/depoite")
	public ResponseEntity<String> depoite(@RequestBody DepositeRequest request){
		try {
			
			CompletableFuture<String> response = 
					accountCommandService.depositeAccount(request);
		
		return new ResponseEntity<>("Account credited" , HttpStatus.OK);
			
		}catch(Exception e) {
			return new ResponseEntity<>("an Error Occure " , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping(value="/withdraw")
	public ResponseEntity<String> withdraw(@RequestBody WithdrawalRequest request){
		try {
			CompletableFuture<String> response = 
					accountCommandService.withdrawAccount(request);
		
		return new ResponseEntity<>("Account Depited" , HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>("an Error Occure " , HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
