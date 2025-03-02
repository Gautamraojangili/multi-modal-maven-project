package com.nba.wariors.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.framework.pages.BasePage;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class ShopPage extends BasePage {

	StringBuilder jacketData = new StringBuilder();

	@FindBy(xpath = "//a[text()='Men']")
	private WebElement shopMenMenu;

	@FindBy(xpath = "//*[text()='Shop']/parent::a")
	private WebElement shopMenu;

	@FindBy(xpath = "//span[text()='Jackets']/parent::a")
	private WebElement jacketsRadioButton;

	@FindBy(css = "div.product-card")
	private List<WebElement> jackets;

	@FindBy(css = ".product-card-title")
	private List<WebElement> jacketTitles;

	@FindBy(css = ".sr-only")
	private List<WebElement> jacketPrices;

	@FindBy(xpath = "//a[contains(@href,'video') and contains(@data-testid,'tile')]")
	private List<WebElement> totalVideoFeeds;

	@FindBy(xpath = "//li[@class='menu-item']//span[contains(.,'...')]")
	private WebElement additionalMenu;

	@FindBy(xpath = "//a[contains(@title,'News & Features')]")
	private WebElement newsAndFeatures;

	@FindBy(css = ".top-seller-vibrancy-message")
	private List<WebElement> topSellerMessages;

	@FindBy(xpath = "//li[@class='next-page']/a")
	private WebElement nextPageButton;

	@FindBy(xpath = "//button[contains(text(),'I Accept')]")
	private WebElement acceptCookiesButton;

	@FindBy(xpath = "//iframe[contains(@title,'reCAPTCHA')]")
	private WebElement reCaptchaFrame;

	@FindBy(xpath = "//div[contains(text(),'x') and contains(@class,'cursor')]")
	private WebElement closeCaptchaFrame;

	@FindBy(tagName = "body')]")
	private WebElement pageBody;

	@FindBy(xpath = "//a[contains(@href,'video') and contains(@data-testid,'tile')]/following-sibling::div//time")
	private List<WebElement> videoTimeStamp;

	public void handlePrerequisities() {

		try {

			if (acceptCookiesButton.isDisplayed()) {
				acceptCookiesButton.click();
				System.out.println("Cookies popup handled.");
			}
		} catch (NoSuchElementException e) {
			System.out.println("No cookies popup found.");
		}
	}

	public void closeRecaptcha() {
		try {
			// driver.switchTo().frame(reCaptchaFrame);
			System.out.println("Switched to reCAPTCHA iframe");
			Thread.sleep(10000);
			closeCaptchaFrame.click();
			System.out.println("Clicked outside reCAPTCHA to close it");
		} catch (Exception e) {
			System.out.println("reCAPTCHA not handled");
		}

	}

	public void navigateToMensSection() {
		String originalWindow = driver.getWindowHandle(); // Store the original tab

		driver.switchTo().defaultContent();
		clickElement(shopMenu);
		Set<String> windowHandles = driver.getWindowHandles();
		for (String window : windowHandles) {
			if (!window.equals(originalWindow)) {
				driver.switchTo().window(window);
				System.out.println("Switched to new tab");
				break;
			}
		}
		waitForElement(shopMenMenu);
		clickElement(shopMenMenu);
		clickElement(jacketsRadioButton);
	}

	public String collectedDataForAllPages() {

		try {
			while (true) {
				// Wait for product cards to be visible
				wait.until(ExpectedConditions.visibilityOfAllElements(jackets));

				for (int i = 0; i < jackets.size(); i++) {
					String title = (i < jacketTitles.size()) ? jacketTitles.get(i).getText() : "No Title";
					String price = (i < jacketPrices.size()) ? jacketPrices.get(i).getText() : "No Price";
					String topSeller = (i < topSellerMessages.size()) ? topSellerMessages.get(i).getText()
							: "Not a Top Seller";

					jacketData.append("Title: ").append(title).append(", Price: ").append(price)
							.append(", Top Seller: ").append(topSeller).append("\n");
				}

				// Check if "Next Page" button exists and is displayed
				if (isElementPresent(nextPageButton)) {
					nextPageButton.click();
					Thread.sleep(2000); // Small delay for page load
				} else {
					break; // No more pages
				}
			}

			// Save data to file
			// saveToFile(jacketData.toString(), "jacket_details.txt");
			return jacketData.toString();

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	private boolean isElementPresent(WebElement element) {
		try {
			return element.isDisplayed();
		} catch (NoSuchElementException | StaleElementReferenceException e) {
			return false;
		}
	}

	public void saveToFile(String data, String fileName) {
		try (FileWriter writer = new FileWriter(fileName)) {
			writer.write(data);
			System.out.println("Jacket details saved successfully!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void navigateToNewAndFeaturesSection() {
		Actions actions = new Actions(driver);

		// Find 'Shop' menu and hover over it
		wait.until(ExpectedConditions.visibilityOf(additionalMenu));
		actions.moveToElement(additionalMenu).perform();
		System.out.println("Hovered on Additional Menu");

		// Click on the 'Men' link
		actions.moveToElement(newsAndFeatures).click().perform();
		System.out.println("Clicked on News & Features Section");
	}

	public int countAllVideoFeeds() {
		try {
			System.out.println("Total Video Feeds ");
			wait.until(ExpectedConditions.visibilityOfAllElements(totalVideoFeeds));
			return totalVideoFeeds.size();
		} catch (Exception e) {

			return 0;
		}
	}

	public int countVideoFeedsOlderThanThreeDays() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy", Locale.ENGLISH);
		int oldVideoCount = 0;
		try {
			System.out.println("Total Video Feeds Older than 3 days ");
			wait.until(ExpectedConditions.visibilityOfAllElements(videoTimeStamp));

			for (WebElement element : videoTimeStamp) {
				String dateText = element.getAttribute("datetime").trim();
				if (!dateText.isEmpty()) {
					LocalDate videoDate = LocalDate.parse(dateText, formatter);
					if (videoDate.isBefore(LocalDate.now().minusDays(3))) {
						oldVideoCount++;
					}
				}
			}
			return oldVideoCount;

		} catch (Exception e) {

			return 0;
		}
	}

	public void selectJacketsRadioButton() {
		clickElement(jacketsRadioButton);
	}

	public boolean isNextPageAvailable() {
		return nextPageButton.isDisplayed();
	}

	public void clickNextPage() {
		clickElement(nextPageButton);
	}

	public List<WebElement> getJackets() {
		return jackets;
	}

	public List<WebElement> getJacketjacketTitles() {
		return jacketTitles;
	}

	public List<WebElement> getJacketPrices() {
		return jacketPrices;
	}

	public List<WebElement> getTopSellerMessages() {
		return topSellerMessages;
	}
}
