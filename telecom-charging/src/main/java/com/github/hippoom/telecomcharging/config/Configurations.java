package com.github.hippoom.telecomcharging.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Configurations {

	private String jmsBrokerUrl;
	private String jmsEnvironment = "local.";

	public String phoneCallMadeQueue() {
		return jmsEnvrironmentSpecified("PhoneCallMadeQueue");
	}

	private String jmsEnvrironmentSpecified(String destinationName) {
		return jmsEnvironment + "." + destinationName;
	}

}
