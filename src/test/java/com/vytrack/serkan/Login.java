package com.vytrack.serkan;

import com.vytrack.utilities.AnnotatedImage;
import com.vytrack.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Login {
WebDriver driver;
private static final String folderName = "LoginPage";

@FindBy(id = "prependedInput")
WebElement emailTextBox;
@FindBy(id = "prependedInput2")
WebElement passwordTextBar;
@FindBy(id = "_submit")
WebElement submitButton;

public Login initElement() {
   PageFactory.initElements(driver, this);
   return this;
}

public Login() {
   this.driver = WebDriverFactory.getDriver("chrome");
}

public Login getScreenShot(String message, String textPosition) throws IOException {
   AnnotatedImage.getAnnotatedScreenShot(driver, folderName, message, textPosition, 25f);
   return this;
}

public Login login(String username, String password) throws IOException {
   signin(username, password, 0);
   return this;
}

public Login verifyUnsuccessfulLogin(String username, String password) throws IOException {
   signin(username, password, 1);
   return this;
}

public Login signin(String username, String password, int unexpectedValue) throws IOException {
   driver.get("https://qa3.vytrack.com/user/login");
   emailTextBox.sendKeys(username);
   passwordTextBar.sendKeys(password);
   submitButton.click();
   
   driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
   List<WebElement> list = driver.findElements(By.id("user-menu"));
   
   try {
      Assert.assertFalse(list.size() == unexpectedValue);
   } catch (AssertionError e) {
      String message = "FAILED: username: " + username + ", password: " + password;
      throw new AssertionError("FAILED: " + AnnotatedImage.getAnnotatedScreenShot(driver, "LoginTest", message, "middle", 40f));
   }
   return this;
}
}
