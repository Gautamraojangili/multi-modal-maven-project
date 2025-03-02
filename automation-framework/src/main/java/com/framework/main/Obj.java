package com.framework.main;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.framework.abstractsuite.ConfigAdaptor;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Obj {

	public static void launchBrowser() {
		RemoteWebDriver driver;
		String browser = ConfigAdaptor.getBrowser().toLowerCase();

		switch (browser) {
		case "chrome":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-gpu"); // Helps with GUI issues
	        options.addArguments("--remote-allow-origins=*"); // Allow WebSocket connections
	        options.addArguments("--disable-blink-features=AutomationControlled"); // Prevent bot detection
	        options.addArguments("--disable-dev-shm-usage"); // Helps with Docker/Linux issues
	        options.addArguments("--no-sandbox"); // Required for some environments
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver(options);
			driver.manage().window().maximize();
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browser);
		}

		new CoreWebDriver(driver);
	}

	public static boolean isOsLinux() {
		return System.getProperty("os.name").toLowerCase().startsWith("linux");
	}

	public static boolean isOsMac() {
		return System.getProperty("os.name").toLowerCase().startsWith("mac");
	}

}
