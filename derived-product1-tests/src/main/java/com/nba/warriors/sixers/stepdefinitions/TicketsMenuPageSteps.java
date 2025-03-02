package com.nba.warriors.sixers.stepdefinitions;

import java.io.FileWriter;
import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.framework.main.CoreWebDriver;
import com.nba.warriors.sixers.pages.TicketsMenuPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TicketsMenuPageSteps {
	private WebDriver driver;
	private TicketsMenuPage ticketsPage;
	private FileWriter fileWriter;
	private String jacketData;

	public TicketsMenuPageSteps() {
		driver = CoreWebDriver.getDriver();
		ticketsPage  = new TicketsMenuPage();
	}

	@Given("User navigates to Men's section from CP Home Page")
	public void userNavigatesToMensSection() {
	}

// /div[@data-testid='content-grid']/descendent::div[@data-testid='tile-featured-article']

	@When("User collects jacket details from all pages")
	public void userCollectsJacketDetails() throws IOException {

	}

	@Then("Attach the details file to the test report")
	public void attachFileToReport() {
	}

}
