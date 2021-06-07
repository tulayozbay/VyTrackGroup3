package com.vytrack.artur;

import com.vytrack.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class UserLogin {
    WebDriver driver;

    public void login() {
        driver = WebDriverFactory.getDriver("chrome");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://qa3.vytrack.com/user/login");
        driver.findElement(By.id("prependedInput")).sendKeys("user9");
        driver.findElement(By.id("prependedInput2")).sendKeys("UserUser123");
        driver.findElement(By.id("_submit")).click();

    }

    public WebElement eventButton() {
        WebElement addEventButton = driver.findElement(By.xpath("//a[@class='btn icons-holder-text no-hash']"));
        return addEventButton;
    }
}
