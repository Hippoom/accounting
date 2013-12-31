package com.github.hippoom.telecomcharging.commands;

import lombok.Getter;
import lombok.ToString;

import org.joda.time.DateTime;

@ToString
@Getter
public class CreateTwoLeggedTransactionCommand {
	private final String fromAccount;
	private final String toAccount;
	private final double quantity;
	private final DateTime whenCharged;

	public CreateTwoLeggedTransactionCommand(String fromAccount, String toAccount,
			double quantity, DateTime whenCharged) {
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.quantity = quantity;
		this.whenCharged = whenCharged;
	}
}
