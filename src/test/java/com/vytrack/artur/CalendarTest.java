package com.vytrack.artur;

import com.vytrack.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CalendarTest {
    WebDriver driver;

    @BeforeClass
    public void setupClass() {
        WebDriver driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("https://qa3.vytrack.com/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("user9");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        driver.findElement(By.id("_submit")).click();
    }

    @Test
    public void addEventTest() {

    }
}
