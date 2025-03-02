package com.nba.bulls.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/features/FooterLinks.feature", glue = { "com.nba.bulls.stepdefinitions",
"com.framework.abstractsuite.AbstarctTestSuite" }, plugin = { "pretty", "html:target/cucumber-reports.html" })
public class HomePageFooterRunner  extends AbstractTestNGCucumberTests{

}
