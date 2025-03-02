package com.framework.main;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Interactive;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CoreWebDriver implements WebDriver, TakesScreenshot, JavascriptExecutor, Interactive {

	private static final Logger LOG = LogManager.getLogger(CoreWebDriver.class);
	protected boolean maximized = false;
	protected boolean allowUserIntervention;
	protected String currentPage;
	private static final ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

	private String username;
	private String password;

	public CoreWebDriver(RemoteWebDriver driver) {
		threadLocalDriver.set(driver);
	}

	   public static WebDriver getDriver() {
	        return threadLocalDriver.get();
	    }
	   
	@Override
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		if (threadLocalDriver instanceof TakesScreenshot) {
			return ((TakesScreenshot) threadLocalDriver).getScreenshotAs(target);
		}
		throw new UnsupportedOperationException("Screenshots not supported");
	}

	// Override any other method that needs customization
	@Override
	public void get(String url) {
		System.out.println("Opening URL: " + url);
		threadLocalDriver.get().get(url);
	}

	@Override
	public String getTitle() {
		return threadLocalDriver.get().getTitle();
	}

	// Delegate all other methods to the actual d
	@Override
	public void close() {
		threadLocalDriver.get().close();
	}

	@Override
	public void quit() {
		quitDriver();
	}
	 public static void quitDriver() {
	        if (threadLocalDriver.get() != null) {
	            threadLocalDriver.get().quit();
	            threadLocalDriver.remove();
	        }
	    }

	@Override
	public WebElement findElement(By by) {
		return threadLocalDriver.get().findElement(by);
	}

	@Override
	public java.util.List<WebElement> findElements(By by) {
		return threadLocalDriver.get().findElements(by);
	}

	@Override
	public String getCurrentUrl() {
		return threadLocalDriver.get().getCurrentUrl();
	}

	@Override
	public String getPageSource() {
		return threadLocalDriver.get().getPageSource();
	}

	@Override
	public void perform(Collection<Sequence> actions) {
		// TODO Auto-generated method stub

	}

	@Override
	public void resetInputState() {
		// TODO Auto-generated method stub

	}

	@Override
	public Object executeScript(String script, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object executeAsyncScript(String script, Object... args) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getWindowHandles() {
		// TODO Auto-generated method stub
		return threadLocalDriver.get().getWindowHandles();
	}

	@Override
	public String getWindowHandle() {
		// TODO Auto-generated method stub
		return threadLocalDriver.get().getWindowHandle();
	}

	@Override
	public TargetLocator switchTo() {
		// TODO Auto-generated method stub
		return threadLocalDriver.get().switchTo();
	}

	@Override
	public Navigation navigate() {
		return threadLocalDriver.get().navigate();
	}

	@Override
	public Options manage() {
		return threadLocalDriver.get().manage();
	}

}