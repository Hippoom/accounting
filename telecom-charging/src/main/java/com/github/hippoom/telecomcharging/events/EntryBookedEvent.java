package com.github.hippoom.telecomcharging.events;

import lombok.Getter;
import lombok.ToString;

import org.joda.time.DateTime;

@Getter
@ToString
public class EntryBookedEvent {
	private final String sequence;
	private final String account;
	private final double quantity;
	private final DateTime whenCharged;

	public EntryBookedEvent(String sequence, String account,
			double quantity, DateTime whenCharged) {
		this.sequence = sequence;
		this.account = account;
		this.quantity = quantity;
		this.whenCharged = whenCharged;
	}
}
