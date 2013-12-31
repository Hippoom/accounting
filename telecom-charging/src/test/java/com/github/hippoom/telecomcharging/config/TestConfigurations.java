package com.github.hippoom.telecomcharging.config;

import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
public class TestConfigurations {

	private String urlPrefix;

	private Configurations configurations;

	public String url(String string) {
		return urlPrefix + string;
	}

	public String phoneCallMadeQueue() {
		return configurations.phoneCallMadeQueue();
	}
}
