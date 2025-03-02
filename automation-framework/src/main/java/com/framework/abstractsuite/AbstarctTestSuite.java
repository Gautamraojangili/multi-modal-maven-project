package com.framework.abstractsuite;

import java.lang.reflect.Method;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import com.framework.main.CoreWebDriver;
import com.framework.main.Obj;
import com.framework.main.TestListener;

import io.cucumber.java.After;
import io.cucumber.java.Before;


@Listeners({ com.framework.main.TestListener.class })
public class AbstarctTestSuite {
	
	public AbstarctTestSuite()
	{
		new ConfigAdaptor();
	}
//	@BeforeSuite
	public void setupSuite() {
		System.out.println("Initializing Test Suite...");
		System.out.println("BaseURL: " + ConfigAdaptor.getBaseUrl());
	}	
	//@BeforeMethod
	public void startTest() {
		Obj.launchBrowser();
		CoreWebDriver.getDriver().get(ConfigAdaptor.getBaseUrl());
	}

	public WebDriver getDriver() {
		return CoreWebDriver.getDriver();
	}

//	@AfterMethod
	public void teardown() {
		CoreWebDriver.quitDriver();
	}

//	@AfterSuite
	public void tearDownSuite() {
		CoreWebDriver.quitDriver();
		System.out.println("Test Suite execution completed.");
	}
	
	@Before
	public void setup()
	{
		startTest();
	}
	@After
	public void teardownCucumber()
	{
		teardown();
	}
}
