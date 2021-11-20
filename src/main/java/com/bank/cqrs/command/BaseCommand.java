package com.bank.cqrs.command;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public class BaseCommand<T> {
	
	@TargetAggregateIdentifier
	private final T id;

	public BaseCommand(T id) {
		super();
		this.id = id;
	}

	public T getId() {
		return id;
	}
	

}
