package com.nba.warriors;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import com.framework.abstractsuite.AbstarctTestSuite;
import com.framework.abstractsuite.ConfigAdaptor;

public class CoreProductTestSuite extends AbstarctTestSuite {

    @Test
    public void loginTest() {
        getDriver().findElement(By.id("qqq")).sendKeys(ConfigAdaptor.getUsername());
        getDriver().findElement(By.id("password")).sendKeys(ConfigAdaptor.getPassword());
        getDriver().findElement(By.id("login")).click();
    }
    @Test
    public void loginTest1() {
        getDriver().findElement(By.id("ssss")).sendKeys(ConfigAdaptor.getUsername());
        getDriver().findElement(By.id("password")).sendKeys(ConfigAdaptor.getPassword());
        getDriver().findElement(By.id("login")).click();
    }
    @Test
    public void loginTest2() {
        getDriver().findElement(By.id("ffsdf")).sendKeys(ConfigAdaptor.getUsername());
        getDriver().findElement(By.id("password")).sendKeys(ConfigAdaptor.getPassword());
        getDriver().findElement(By.id("login")).click();
    }
    @Test
    public void loginTest3() {
        getDriver().findElement(By.id("eee")).sendKeys(ConfigAdaptor.getUsername());
        getDriver().findElement(By.id("f")).sendKeys(ConfigAdaptor.getPassword());
        getDriver().findElement(By.id("login")).click();
    }
    @Test
    public void loginTest4() {
        getDriver().findElement(By.id("qqqq")).sendKeys(ConfigAdaptor.getUsername());
        getDriver().findElement(By.id("password")).sendKeys(ConfigAdaptor.getPassword());
        getDriver().findElement(By.id("login")).click();
    }
    @Test
    public void loginTest5() {
        getDriver().findElement(By.id("hhhh")).sendKeys(ConfigAdaptor.getUsername());
        getDriver().findElement(By.id("password")).sendKeys(ConfigAdaptor.getPassword());
        getDriver().findElement(By.id("login")).click();
    }
    
}