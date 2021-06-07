package com.vytrack.tulay;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

        protected static ThreadLocal<ChromeDriver> getDriver = new ThreadLocal<>();

        @BeforeMethod

        public void setUp(){

            WebDriverManager.chromedriver().setup();
            getDriver.set(new ChromeDriver());


        }

        public WebDriver getDriver(){

            return getDriver.get();
        }

        @AfterMethod
        public void tearDown() throws InterruptedException {
//        Thread.sleep(3000);
//        getDriver().quit();
        }

    }


