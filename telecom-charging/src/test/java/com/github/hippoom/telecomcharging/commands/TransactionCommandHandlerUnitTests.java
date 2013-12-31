package com.github.hippoom.telecomcharging.commands;

import org.axonframework.test.FixtureConfiguration;
import org.axonframework.test.Fixtures;
import org.jmock.Expectations;
import org.jmock.auto.Mock;
import org.jmock.integration.junit4.JUnitRuleMockery;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.github.hippoom.telecomcharging.domain.model.txn.Transaction;
import com.github.hippoom.telecomcharging.domain.shared.IdentifierGenerator;
import com.github.hippoom.telecomcharging.events.EntryBookedEvent;

public class TransactionCommandHandlerUnitTests {
	@Rule
	public final JUnitRuleMockery context = new JUnitRuleMockery();

	private FixtureConfiguration<Transaction> fixture = Fixtures
			.newGivenWhenThenFixture(Transaction.class);

	private TransactionCommandHandler handler = new TransactionCommandHandler();
	@Mock
	private IdentifierGenerator transactionSequenceGenerator;

	@Before
	public void injects() {
		handler.setTransactionRepository(fixture.getRepository());
		handler.setTransactionSequenceGenerator(transactionSequenceGenerator);
		fixture.registerAnnotatedCommandHandler(handler);
	}

	@Test
	public void twoLeggedTxnCreated() throws Throwable {
		final String sequence1 = "1";
		final String network = "John Doe network";
		final String basicTime = "John Doe basicAccount";
		final DateTime whenCharged = DateTimeFormat.forPattern(
				"yyyy-MM-dd HH:mm").parseDateTime("2014-04-01 13:12");

		context.checking(new Expectations() {
			{
				allowing(transactionSequenceGenerator).next();
				will(returnValue(sequence1));
			}
		});

		fixture.given()
				.when(new CreateTwoLeggedTransactionCommand(network, basicTime,
						8, whenCharged))
				.expectEvents(
						new EntryBookedEvent(sequence1, network, -8,
								whenCharged),
						new EntryBookedEvent(sequence1, basicTime, 8,
								whenCharged));

	}

	@Test
	public void twoLeggedTransformedTxnCreated() throws Throwable {
		final String sequence1 = "1";
		final String network = "John Doe network";
		final String basicTime = "John Doe basicAccount";
		final String networkIncome = "John Doe network income";
		final String activeAccount = "John Doe activeAccount";
		final DateTime whenCharged = DateTimeFormat.forPattern(
				"yyyy-MM-dd HH:mm").parseDateTime("2014-04-01 13:12");

		context.checking(new Expectations() {
			{
				allowing(transactionSequenceGenerator).next();
				will(returnValue(sequence1));
			}
		});

		fixture.given()
				.when(new CreateTransformTransactionCommand(basicTime, network,
						8, whenCharged))
				.expectEvents(
						new EntryBookedEvent(sequence1, basicTime, -8,
								whenCharged),
						new EntryBookedEvent(sequence1, network, 8, whenCharged),
						new EntryBookedEvent(sequence1, networkIncome, -0.98,
								whenCharged),
						new EntryBookedEvent(sequence1, activeAccount, 0.98,
								whenCharged));

	}
}
