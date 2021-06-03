package com.vytrack.serkan;

import com.vytrack.utilities.AnnotatedImage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.IOException;
import java.util.List;

public class Main {
DashBoard dashBoard;
WebDriver driver;
boolean hasPassed = true;
private static final String folderName = "LoginPage";

@FindBy(id = "prependedInput")
WebElement emailTextBox;
@FindBy(id = "prependedInput2")
WebElement passwordTextBar;
@FindBy(id = "_submit")
WebElement submitButton;


public static Main getMain(WebDriver driver) {
   Main main = new Main(driver);
   PageFactory.initElements(driver, main);
   return main;
}

//LoginPage page = PageFactory.initElements(driver, LoginPage.class);
//BaseClass page = PageFactory.initElements(driver, BaseClass.class);
//public LoginPage(WebDriver driver)
//{
//   this.driver = driver;
//   //PageFactory.initElements(driver, this);
//   PageFactory.initElements(driver, this);
//}

public Main(WebDriver driver) {
   this.driver = driver;
}

public DashBoard getDashBoard() {
   this.dashBoard = new DashBoard();
   dashBoard.setDriver(driver);
   PageFactory.initElements(driver, dashBoard);
   return dashBoard;
}

public Main getScreenShot(String message, String textPosition) throws IOException {
   AnnotatedImage.getAnnotatedScreenShot(driver, folderName, (hasPassed) ? "PASSED: " : "FAILED: " + message, textPosition, 25f);
   return this;
}

public Main login(String username, String password) throws IOException {
   signin(username, password, 0);
   return this;
}

public Main verifyUnsuccessfulLogin(String username, String password) throws IOException {
   signin(username, password, 1);
   return this;
}

public void close() {
   driver.close();
}

public Main signin(String username, String password, int unexpectedValue) throws IOException {
   driver.get("https://qa3.vytrack.com/user/login");
   emailTextBox.sendKeys(username);
   passwordTextBar.sendKeys(password);
   submitButton.click();
   
   List<WebElement> list = driver.findElements(By.id("user-menu"));
   
   try {
      Assert.assertFalse(list.size() == unexpectedValue);
   } catch (AssertionError e) {
      hasPassed = false;
      String message = "FAILED: username: " + username + ", password: " + password;
      throw new AssertionError("FAILED: " + AnnotatedImage.getAnnotatedScreenShot(driver, "LoginTest", message, "middle", 40f));
   }
   return this;
}
}
