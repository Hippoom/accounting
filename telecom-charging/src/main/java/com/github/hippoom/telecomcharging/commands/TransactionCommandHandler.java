package com.github.hippoom.telecomcharging.commands;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import lombok.Setter;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;

import com.github.hippoom.telecomcharging.domain.model.txn.Entry;
import com.github.hippoom.telecomcharging.domain.model.txn.Transaction;
import com.github.hippoom.telecomcharging.domain.shared.IdentifierGenerator;

public class TransactionCommandHandler {
	@Setter
	private Repository<Transaction> transactionRepository;
	@Setter
	private IdentifierGenerator transactionSequenceGenerator;

	@CommandHandler
	public void handle(CreateTwoLeggedTransactionCommand command) {
		transactionRepository.add(new Transaction(transactionSequenceGenerator
				.next(), command.getFromAccount(), command.getToAccount(),
				command.getQuantity(), command.getWhenCharged()));
	}

	@CommandHandler
	public void handle(CreateTransformTransactionCommand command) {
		final Set<Entry> entries = new LinkedHashSet<Entry>();
		entries.add(new Entry(command.getFromAccount(),
				quantityForFromAccount(command.getQuantity()), command
						.getWhenCharged()));
		entries.add(new Entry(command.getToAccount(), command.getQuantity(),
				command.getWhenCharged()));
		entries.add(new Entry("John Doe network income", -0.98, command
				.getWhenCharged()));
		entries.add(new Entry("John Doe activeAccount", 0.98, command
				.getWhenCharged()));

		Transaction txn = new Transaction(transactionSequenceGenerator.next(),
				entries);
		transactionRepository.add(txn);
	}

	private double quantityForFromAccount(double quantity) {
		return new BigDecimal(quantity).negate().doubleValue();
	}
}
