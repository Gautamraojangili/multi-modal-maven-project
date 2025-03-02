package com.framework.pages;


import com.framework.abstractsuite.AbstarctTestSuite;
import com.framework.main.CoreWebDriver;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage{
    protected WebDriver driver;
    protected WebDriverWait wait;


    public BasePage() {
    	new AbstarctTestSuite().startTest();
        this.driver = new AbstarctTestSuite().getDriver();
        PageFactory.initElements(driver, this);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }
    
    protected void clickElement(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected String getElementText(WebElement element) {
        waitForElement(element);
        return element.getText();
    }
}
