package com.github.hippoom.telecomcharging;

import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(features = { "classpath:" }, format = { "html:target/acceptance-cucumber-html" })
public class AcceptanceTests {

}
