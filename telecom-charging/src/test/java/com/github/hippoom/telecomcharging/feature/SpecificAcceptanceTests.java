package com.github.hippoom.telecomcharging.feature;
import org.junit.runner.RunWith;

import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@Cucumber.Options(features = { "classpath:" }, format = { "html:target/acceptance-cucumber-html" }, name = "First minute charge for daytime call ")
public class SpecificAcceptanceTests {

}
