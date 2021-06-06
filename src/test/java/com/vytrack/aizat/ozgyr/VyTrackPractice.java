package com.vytrack.aizat.ozgyr;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import java.util.List;

public class VyTrackPractice  extends BaseTest{


        @Test
        public void VyTrack() throws InterruptedException {


            getDriver().get("https://qa3.vytrack.com/user/login");
            getDriver().manage().window().maximize();

            // getDriver().manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
            getDriver().findElement(By.xpath("//input[@id='prependedInput']")).sendKeys("user7");

            WebElement passWord = getDriver().findElement(By.xpath("//input[@name='_password']"));
            passWord.sendKeys("UserUser123" + Keys.ENTER);
            getDriver().navigate().refresh();

            List<WebElement> fleetButton = getDriver().findElements(By.xpath("//span[@class='title title-level-1']"));
            fleetButton.get(0).click();

            Thread.sleep(3000);

            List<WebElement> vehicle = getDriver().findElements(By.xpath("//span[@class='title title-level-2']"));
            vehicle.get(0).click();

            List<WebElement> vehicles = getDriver().findElements(By.className("grid-body-cell-LicensePlate"));
            WebElement target = vehicles.get((int) (Math.random() * vehicles.size()));
            Actions actions = new Actions(getDriver());
            actions.moveToElement(target).click().perform();
            Thread.sleep(1000);



//
//            getDriver().findElement(By.xpath("//span[@class='title title-level-2']")).click();




        }
    }



