package com.nba.warriors.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/CountVideoFeeds.feature", glue = { "com.nba.warriors.stepdefinitions",
		"com.framework.abstractsuite.AbstarctTestSuite" }, plugin = { "pretty", "html:target/cucumber-reports.html" })
public class CountVideoFeedsRunner extends AbstractTestNGCucumberTests {

}