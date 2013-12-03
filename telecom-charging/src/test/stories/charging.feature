Feature: Phone call charging

Scenario: First minute charge for daytime call 

Given all calls are divided into day and evening calls
And daytime runs from 7:00 am to 7:00 pm
And the classification is based on the time the call begins
And day calls cost 98 cents for the first minute and 30 cents for subsequent minutes
When John Doe make a call at daytime and ends within one minute
Then John Doe owes total telecommunications 98 cents