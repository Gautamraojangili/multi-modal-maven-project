package com.framework.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.support.ui.ExpectedConditions;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Sample {
    public static void main(String[] args) {
        // Set up WebDriver
    	WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();

    	options.addArguments("--disable-gpu"); // Helps with GUI issues
        options.addArguments("--remote-allow-origins=*"); // Allow WebSocket connections
        options.addArguments("--disable-blink-features=AutomationControlled"); // Prevent bot detection
        options.addArguments("--disable-dev-shm-usage"); // Helps with Docker/Linux issues
        options.addArguments("--no-sandbox"); // Required for some environments
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://shop.warriors.com/golden-state-warriors-men-jackets/t-25690618+ga-01+d-3438843071+z-9-4167543?pageSize=96");

        StringBuilder jacketData = new StringBuilder();
        
        try {
            while (true) {
                // Wait for elements to load
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.product-card")));

                // Get all jackets
                List<WebElement> jackets = driver.findElements(By.cssSelector("div.product-card"));
                List<WebElement> titles = driver.findElements(By.cssSelector(".product-card-title"));
                List<WebElement> prices = driver.findElements(By.cssSelector(".sr-only[aria-hidden='true']"));
                List<WebElement> topSellerTags = driver.findElements(By.cssSelector(".product-card-bestseller"));

                for (int i = 0; i < jackets.size(); i++) {
                    String title = (i < titles.size()) ? titles.get(i).getText() : "No Title";
                    String price = (i < prices.size()) ? prices.get(i).getText() : "No Price";
                    String topSeller = (i < topSellerTags.size()) ? topSellerTags.get(i).getText() : "Not a Top Seller";

                    jacketData.append("Title: ").append(title)
                            .append(", Price: ").append(price)
                            .append(", Top Seller: ").append(topSeller).append("\n");
                }

                // Check if "Next Page" button exists
                List<WebElement> nextButton = driver.findElements(By.xpath("//li[@class='next-page']/a"));
                if (nextButton.size() > 0 && nextButton.get(0).isDisplayed()) {
                    nextButton.get(0).click();
                    Thread.sleep(2000); // Small delay for page load
                } else {
                    break; // No more pages, exit loop
                }
            }

            // Save data to file
            try (FileWriter writer = new FileWriter("jacket_details.txt")) {
                writer.write(jacketData.toString());
                System.out.println("Jacket details saved successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}
