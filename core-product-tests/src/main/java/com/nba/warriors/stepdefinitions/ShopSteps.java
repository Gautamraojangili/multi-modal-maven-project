package com.nba.warriors.stepdefinitions;

import com.framework.abstractsuite.AbstarctTestSuite;
import com.framework.main.CoreWebDriver;
import com.nba.wariors.pages.ShopPage;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import java.io.FileWriter;
import java.io.IOException;

public class ShopSteps {
	private WebDriver driver;
	private ShopPage shopPage;
	private FileWriter fileWriter;
	private String jacketData;

	public ShopSteps() {
		driver = CoreWebDriver.getDriver();
		shopPage = new ShopPage();
	}

	@Given("User navigates to Men's section from CP Home Page")
	public void userNavigatesToMensSection() {
		for (int i = 0; i < 3; i++) {
			shopPage.handlePrerequisities();
		}
		for (int i = 0; i < 3; i++) {
			shopPage.closeRecaptcha();
		}

		shopPage.navigateToMensSection();
	}

// /div[@data-testid='content-grid']/descendent::div[@data-testid='tile-featured-article']

	@When("User collects jacket details from all pages")
	public void userCollectsJacketDetails() throws IOException {

		jacketData = shopPage.collectedDataForAllPages();
	}

	@Then("Attach the details file to the test report")
	public void attachFileToReport() {
		shopPage.saveToFile(jacketData, "jacket_details.csv");
	}

	@Given("User navigates to News and Features Page from CP Home Page")
	public void userNavigatesToNewsFeaturesSection() {
		for (int i = 0; i < 3; i++) {
			shopPage.handlePrerequisities();
		}
		for (int i = 0; i < 3; i++) {
			shopPage.closeRecaptcha();
		}

		shopPage.navigateToNewAndFeaturesSection();
	}

	@When("Count total video feeds")
	public void countTotalVideoFeeds() throws IOException {

		System.out.println("Total Video Feeds are " + shopPage.countAllVideoFeeds());
	}

	@Then("Count total video feeds older than three days")
	public void countVideoFeedsOlderThanThreeDays() throws IOException {

		System.out.println("Total Video Feeds are " + shopPage.countVideoFeedsOlderThanThreeDays());
	}
}
