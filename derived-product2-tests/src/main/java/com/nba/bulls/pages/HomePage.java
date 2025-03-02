package com.nba.bulls.pages;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.framework.pages.BasePage;

public class HomePage extends BasePage {
	Actions actions;
	@FindBy(xpath = "")
	private List<WebElement> footerlinks;

	@FindBy(xpath = "//footer//nav")
	private WebElement pageFooter;

	@FindBy(xpath = "//ul[@data-testid='footer-list']//a")
	private List<WebElement> footerLinks;

	public void scrollToTheFooter() {
		actions = new Actions(driver);
		waitForElement(pageFooter);
		actions.moveToElement(pageFooter).perform();
	}

	public void getAllFooterLinks() {
		for (WebElement element : footerLinks) {
			System.out.println("Link is : " + element.getAttribute("href"));
		}
	}

	public void writeLinksToCSV() {
		List<String> allLinks = new ArrayList<>();
		List<String> duplicateLinks = new ArrayList<>();
		Set<String> uniqueLinks = new HashSet<>();

		actions = new Actions(driver);
		waitForElement(pageFooter);
		actions.moveToElement(pageFooter).perform();

		try {
			FileWriter csvWriter = new FileWriter("footer_links.csv");
			csvWriter.append("Link Text,URL\n");

			for (WebElement element : footerLinks) {
				String linkText = element.getText().trim();
				String url = element.getAttribute("href");

				if (url != null && !url.isEmpty()) {
					allLinks.add(url);

					csvWriter.append(linkText).append(",").append(url).append("\n");

				}
				if (!uniqueLinks.add(url)) {
					duplicateLinks.add(url);
				}
			}
			csvWriter.flush();
			csvWriter.close();
			if (!duplicateLinks.isEmpty()) {
				System.out.println("Duplicate links found:");
				for (String duplicate : duplicateLinks) {
					System.out.println(duplicate);
				}
			} else {
				System.out.println("No duplicate links found.");
			}
		} catch (Exception e) {
			e.addSuppressed(e);
		}

	}
}
