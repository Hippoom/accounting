package com.github.hippoom.telecomcharging.rest;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Controller
public class BillingRestEndpoint {

	@RequestMapping(value = "/customer/{customerId}/bill", method = RequestMethod.GET)
	@ResponseBody
	public String viewComments(@PathVariable("customerId") String customerId)
			throws JsonParseException, JsonMappingException, IOException {
		return "0.98";
	}

}
