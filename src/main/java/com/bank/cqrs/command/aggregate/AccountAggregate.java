package com.bank.cqrs.command.aggregate;

import java.math.BigDecimal;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import com.bank.cqrs.command.CreateAccountCommand;
import com.bank.cqrs.command.DepositMoneyCommand;
import com.bank.cqrs.command.WithdrawMoneyCommand;
import com.bank.cqrs.common.event.AccountActivatedEvent;
import com.bank.cqrs.common.event.AccountCreatedEvent;
import com.bank.cqrs.common.event.AccountCreditedEvent;
import com.bank.cqrs.common.event.AccountDebitedEvent;

import lombok.extern.slf4j.Slf4j;

@Aggregate
@Slf4j
public class AccountAggregate {
	
	@AggregateIdentifier
	private String accountId ;
	private BigDecimal amount ;
	private String status ;
	
	public AccountAggregate() {
	}
	
	
	@CommandHandler
	public AccountAggregate(CreateAccountCommand createAccountCommand) {
        System.out.println("CreateAccountCommand received.");
        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.getId(), createAccountCommand.getBalance()));
	
	}
	
	@EventSourcingHandler
	public void on(AccountCreatedEvent accountCreatedEvent) {
        System.out.println("AccountCreatedEvent received.");
        this.accountId= accountCreatedEvent.getId();
        this.amount = accountCreatedEvent.getBalance();
        this.status ="CREATED";
        
        AggregateLifecycle.apply(new AccountActivatedEvent(accountCreatedEvent.getId() ,"ACTIVATED"));

	}
	
	 @EventSourcingHandler
	    public void on(AccountActivatedEvent accountActivatedEvent) {
		 System.out.println("An AccountActivatedEvent occurred.");
	     this.status = accountActivatedEvent.getStatus();
	    }
	
	@CommandHandler
	public AccountAggregate(DepositMoneyCommand depositeMoneyCommand) {
        System.out.println("DepositMoneyCommand received.");
        AggregateLifecycle.apply(new AccountCreditedEvent(depositeMoneyCommand.getId(), depositeMoneyCommand.getBalance()));
	}

	@EventSourcingHandler
	public void on(AccountCreditedEvent accountCreditedEvent) {
        System.out.println("AccountCreditedEvent occured.");
        this.accountId= accountCreditedEvent.getId();
        //this.amount = accountCreditedEvent.getAmount();
        this.amount = this.amount.add(accountCreditedEvent.getAmount());
	}

	
	
	@CommandHandler
	public AccountAggregate(WithdrawMoneyCommand withdrawMoneyCommand) {
        System.out.println("WithdrawMoneyCommand received.");
        AggregateLifecycle.apply(new AccountDebitedEvent(withdrawMoneyCommand.getId(), withdrawMoneyCommand.getBalance()));
	
	}
	
	@EventSourcingHandler
	public void on(AccountDebitedEvent accountDebitedEvent) {
        System.out.println("AccountDebitedEvent occured.");
        this.accountId =accountDebitedEvent.getId();
        this.amount = this.amount.subtract(accountDebitedEvent.getAmount());

		
	}

}
