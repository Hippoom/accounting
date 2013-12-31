package com.github.hippoom.telecomcharging.feature;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;

import com.github.hippoom.telecomcharging.config.TestConfigurations;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@ContextConfiguration("classpath:acceptance.xml")
public class PhoneChargingSteps {
	@Autowired
	private JmsTemplate jmsTemplate;
	@Autowired
	private TestConfigurations configurations;

	@Given("^all calls are divided into day and evening calls$")
	public void all_calls_are_divided_into_day_and_evening_calls()
			throws Throwable {
		// Express the Regexp above with the code you wish you had
	}

	@Given("^daytime runs from (\\d+):(\\d+) am to (\\d+):(\\d+) pm$")
	public void daytime_runs_from_am_to_pm(int arg1, int arg2, int arg3,
			int arg4) throws Throwable {
		// Express the Regexp above with the code you wish you had
	}

	@Given("^the classification is based on the time the call begins$")
	public void the_classification_is_based_on_the_time_the_call_begins()
			throws Throwable {
		// Express the Regexp above with the code you wish you had
	}

	@Given("^day calls cost (\\d+) cents for the first minute and (\\d+) cents for subsequent minutes$")
	public void day_calls_cost_cents_for_the_first_minute_and_cents_for_subsequent_minutes(
			int arg1, int arg2) throws Throwable {
		// Express the Regexp above with the code you wish you had
	}

	@When("^John Doe make a call at daytime and ends within one minute$")
	public void John_Doe_make_a_call_at_daytime_and_ends_within_one_minute()
			throws Throwable {
		jmsTemplate
				.convertAndSend("phoneCallMadeQueue",
						"{\"customer\":\"JohnDoe\", \"date\":\"2014-04-01\", \"time\":\"07:01\"}");
	}

	@Then("^John Doe owes total telecommunications (\\d+) cents$")
	public void John_Doe_owes_total_telecommunications_cents(int arg1)
			throws Throwable {
		Content cotent = Request
				.Get(configurations.url("/customer/JohnDoe/bill")).execute()
				.returnContent();
		assertThat(cotent.asString(), equalTo("0.98"));
	}
}
