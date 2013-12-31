package com.github.hippoom.telecomcharging.commands;

import lombok.Getter;
import lombok.ToString;

import org.joda.time.DateTime;

@ToString
@Getter
public class CreateTransformTransactionCommand extends CreateTwoLeggedTransactionCommand {

	public CreateTransformTransactionCommand(String fromAccount,
			String toAccount, double quantity, DateTime whenCharged) {
		super(fromAccount, toAccount, quantity, whenCharged);
	}
}
