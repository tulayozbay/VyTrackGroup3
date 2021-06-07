package com.vytrack.tulay;

import com.vytrack.utilities.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class LoginTask {

    public static void main(String[] args) throws InterruptedException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("https://qa3.vytrack.com/user/login");

        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        //bu asgidaki once username yazan yeri inspect yaptik sonra bu kode buraya yazdik
        driver.findElement(By.xpath("//input[@id='prependedInput']")).sendKeys("user7");

        //for re-usability we need to declare as a WebElement
        WebElement password = driver.findElement(By.xpath("//input[@id='prependedInput2']"));
        password.sendKeys("UserUser123");
        password.submit();// instead of password.submit(); We can do this ---> password.sendKeys("UserUser123" + Keys.ENTER);

        driver.navigate().refresh();//before this it popped-up alert on the page

        //for vehicles menu
        List<WebElement> fleetButton = driver.findElements(By.xpath("//span[@class='title title-level-1']"));
        fleetButton.get(0).click();

        Thread.sleep(3000);

        //for car
        List<WebElement> vehicle = driver.findElements(By.xpath("//span[@class='title title-level-2']"));
        vehicle.get(0).click();

        Thread.sleep(3000);

        //for vehicles list
        List<WebElement> vehicles = driver.findElements(By.xpath("//tbody[@class='grid-body']"));
        WebElement target = vehicles.get((int) (Math.random() * vehicles.size()));
        Actions actions = new Actions(driver);
        actions.moveToElement(target).click().perform();
        Thread.sleep(1000);

        //for activities menu find by xpath
        List<WebElement> activitiesBtn = driver.findElements(By.xpath("//span[@class='title title-level-1']"));
        activitiesBtn.get(2).click();//index number

        Thread.sleep(3000);

        //for calender find by relative xpath
        driver.findElement(By.xpath("//span[.='Calendar Events']")).click();

        //for create calender event btn
        WebElement calenderCreateBtn = driver.findElement(By.xpath("//a[@class='btn main-group btn-primary pull-right ']"));
        calenderCreateBtn.click();

        //for title
        driver.findElement(By.xpath("//input[@data-name='field__title']")).sendKeys("Meeting for new project group-3");

        //for save and close btn
        driver.findElement(By.xpath("//button[@class='btn btn-success action-button']")).click();

        //for reset menu
        driver.findElement(By.xpath("//a[@title='Reset']")).click();










    }

}
