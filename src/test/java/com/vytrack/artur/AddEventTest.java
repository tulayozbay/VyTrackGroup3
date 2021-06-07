package com.vytrack.artur;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.List;
import java.util.Random;

public class AddEventTest extends UserLogin {

    @BeforeClass
    public void setupClass() {
        login();
    }

    @Test
    public void fleetVehicles() throws InterruptedException {
        List<WebElement> optionsModule = driver.findElements(By.xpath("//span[@class='title title-level-1']"));
        List<WebElement> insideFleetMenu = driver.findElements(By.xpath("//span[@class='title title-level-2']"));
        /*
        driver.findElement(By.xpath("//*[@id='main-menu']/ul/li[1]/a/span")).click();
        driver.findElement(By.xpath("//div[@id='main-menu']//span[.='Vehicles']")).click();
         */

        optionsModule.get(0).click();
        Thread.sleep(5000);
        insideFleetMenu.get(0).click();
        Thread.sleep(5000);
        String expectedHeader = "Cars";
        String actualHeader = driver.findElement(By.xpath("//h1[.='Cars']")).getText();
        Assert.assertEquals(actualHeader, expectedHeader, "header verification");
    }

    @Test
    public void randomCar() throws InterruptedException {
        List<WebElement> carsTable = driver.findElements(By.xpath("//tbody[@class='grid-body']"));
        Random random = new Random();
        int allCars = random.nextInt(carsTable.size() + 1);
        WebElement randomCar = carsTable.get(allCars);
        Thread.sleep(5000);
        randomCar.click();
        String test = eventButton().isDisplayed() ? "PASSED!" : "FAILED!";
        System.out.println(test);
        Thread.sleep(5000);
        eventButton().click();
    }

    @Test
    public void positiveAddEvent() throws InterruptedException {
        Thread.sleep(5000);
        WebElement inputBox = driver.findElement(By.cssSelector("[id*=oro_calendar_event_form_t]"));
        Thread.sleep(5000);
        inputBox.sendKeys("New event");

    }
}
