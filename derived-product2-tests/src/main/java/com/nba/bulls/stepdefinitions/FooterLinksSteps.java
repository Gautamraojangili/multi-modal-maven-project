package com.nba.bulls.stepdefinitions;

import java.io.FileWriter;

import org.openqa.selenium.WebDriver;

import com.framework.main.CoreWebDriver;
import com.nba.bulls.pages.HomePage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FooterLinksSteps {
	
	private WebDriver driver;
	private HomePage homePage;
	private FileWriter fileWriter;
	private String jacketData;

	public FooterLinksSteps() {
		driver = CoreWebDriver.getDriver();
		homePage = new HomePage();
	}
	
	@Given("User navigates to Application Home Page")
	public void goToHomePageAndScrollToBottom()
	{
		homePage.scrollToTheFooter();
	}
	@When("Scroll to the Footer")
	public void getAllLinksForFooterCategory()
	{
		homePage.getAllFooterLinks();
	}
	@Then("Write All Links to CSV and Print Duplicate Links")
	public void writeAllLinkToCSVPrintDuplicateLink()
	{
		homePage.writeLinksToCSV();
	}

}
