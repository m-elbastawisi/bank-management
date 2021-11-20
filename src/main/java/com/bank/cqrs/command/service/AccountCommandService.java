package com.bank.cqrs.command.service;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import com.bank.cqrs.command.CreateAccountCommand;
import com.bank.cqrs.command.DepositMoneyCommand;
import com.bank.cqrs.command.WithdrawMoneyCommand;
import com.bank.cqrs.command.dto.CreateAccountRequest;
import com.bank.cqrs.command.dto.DepositeRequest;
import com.bank.cqrs.command.dto.WithdrawalRequest;

@Service
public class AccountCommandService {

	private final CommandGateway commandGateway;

	public AccountCommandService(CommandGateway commandGateway) {
		super();
		this.commandGateway = commandGateway;
	}
	
	public CompletableFuture<String> createAccount(CreateAccountRequest createAccountRequest){
		CreateAccountCommand createAccountCommand = new CreateAccountCommand(UUID.randomUUID().toString(), createAccountRequest.getStratingBalance());
		return commandGateway.send(createAccountCommand);
	}
	
	public CompletableFuture<String> depositeAccount(DepositeRequest depositeRequest){
		DepositMoneyCommand depositeMoneyCommand = new DepositMoneyCommand(depositeRequest.getAccountId(), depositeRequest.getAmount());
		return commandGateway.send(depositeMoneyCommand);
	}
	
	public CompletableFuture<String> withdrawAccount(WithdrawalRequest withdrawalRequest){
		WithdrawMoneyCommand withdrawMoneyCommand = new WithdrawMoneyCommand(withdrawalRequest.getAccountId(), withdrawalRequest.getAmount());
		return commandGateway.send(withdrawMoneyCommand);
	}
	
}
