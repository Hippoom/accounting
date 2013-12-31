package com.github.hippoom.telecomcharging.domain.model.txn;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.joda.time.DateTime;

@EqualsAndHashCode
@ToString
public class Entry {
	private String account;
	private double quantity;
	private DateTime whenCharged;

	public Entry(String account, double quantity, DateTime whenCharged) {
		this.account = account;
		this.quantity = quantity;
		this.whenCharged = whenCharged;
	}

	public String account() {
		return account;
	}

	public double quantity() {
		return quantity;
	}

	public DateTime whenCharged() {
		return whenCharged;
	}
}
