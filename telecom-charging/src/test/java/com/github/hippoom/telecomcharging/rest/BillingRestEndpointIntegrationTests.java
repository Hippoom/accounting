package com.github.hippoom.telecomcharging.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/billing-servlet.xml" })
@WebAppConfiguration
public class BillingRestEndpointIntegrationTests {
	@Autowired
	private WebApplicationContext wac;

	private MockMvc mockMvc;

	@Before
	public void injects() {
		mockMvc = webAppContextSetup(this.wac).build();
	}

	@After
	public void restMocks() {

	}

	@Test
	public void approvesComment() throws Throwable {

		mockMvc.perform(get("/customer/JohnDoe/bill")).andDo(print())
				.andExpect(status().isOk()).andExpect(content().string("0.98"));

	}
}
