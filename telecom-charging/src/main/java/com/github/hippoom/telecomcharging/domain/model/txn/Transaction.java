package com.github.hippoom.telecomcharging.domain.model.txn;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.joda.time.DateTime;

import com.github.hippoom.telecomcharging.events.EntryBookedEvent;

public class Transaction extends AbstractAnnotatedAggregateRoot<String> {

	private static final long serialVersionUID = 1L;
	@AggregateIdentifier
	private String sequence;

	public Transaction(String sequence, String fromAccount, String toAccount,
			double quantity, DateTime whenCharged) {
		final Set<Entry> entries = new LinkedHashSet<Entry>();
		entries.add(new Entry(fromAccount, quantityForFromAccount(quantity),
				whenCharged));
		entries.add(new Entry(toAccount, quantity, whenCharged));
		for (Entry entry : entries) {
			apply(new EntryBookedEvent(sequence, entry.account(),
					entry.quantity(), entry.whenCharged()));
		}
	}

	public Transaction(String sequence, Set<Entry> entries) {
		for (Entry entry : entries) {
			apply(new EntryBookedEvent(sequence, entry.account(),
					entry.quantity(), entry.whenCharged()));
		}
	}

	private double quantityForFromAccount(double quantity) {
		return new BigDecimal(quantity).negate().doubleValue();
	}

	@EventHandler
	private void on(EntryBookedEvent event) {
		this.sequence = event.getSequence();
	}

	/**
	 * for frameworks only
	 */
	@SuppressWarnings("unused")
	private Transaction() {
	}
}
