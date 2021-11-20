package com.bank.cqrs.query.service;

import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import com.bank.cqrs.common.event.AccountActivatedEvent;
import com.bank.cqrs.common.event.AccountCreatedEvent;
import com.bank.cqrs.common.event.AccountCreditedEvent;
import com.bank.cqrs.common.event.AccountDebitedEvent;
import com.bank.cqrs.query.entity.Account;
import com.bank.cqrs.query.query.FindAccountByIdQuery;
import com.bank.cqrs.query.repository.AccountRepository;

@Service
public class ManageAccountService {

	private final AccountRepository accountRepository ;

	public ManageAccountService(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}
	
	
	@EventHandler
	public void on(AccountCreatedEvent accountCreatedEvent) {
			Account account = new Account();
			account.setAccountId(accountCreatedEvent.getId());
			account.setAmount(accountCreatedEvent.getBalance());
			account.setStatus("CREATED");
			accountRepository.save(account);
		}
	
	@EventHandler
	public void on(AccountActivatedEvent accountActivatedEvent) {
	   Account account =accountRepository.findById(accountActivatedEvent.getId()).orElse(null);
	   if(account != null) {
		   account.setStatus(accountActivatedEvent.getStatus());
		   accountRepository.save(account);
	   }
	 }
	

	@EventHandler
	public void on(AccountCreditedEvent accountCreditedEvent) {
	   Account account =accountRepository.findById(accountCreditedEvent.getId()).orElse(null);
	   if(account != null) {
		   account.setAmount(account.getAmount().add(accountCreditedEvent.getAmount()));
		   accountRepository.save(account);
	   }
	 }
	
	@EventHandler
	public void on(AccountDebitedEvent accountDebitedEvent) {
	   Account account =accountRepository.findById(accountDebitedEvent.getId()).orElse(null);
	   if(account != null) {
		   account.setAmount(account.getAmount().subtract(accountDebitedEvent.getAmount()));
		   accountRepository.save(account);
	   }
	 }
	
	@QueryHandler
	public Account handle(FindAccountByIdQuery findAccountByID) {
		 Account account = accountRepository.findById(findAccountByID.getAccountId()).orElse(null);
		 return account;
	}
	
	
	
	}

