package com.vytrack.serkan;

import com.vytrack.trya.Login;
import org.testng.annotations.*;

import java.io.IOException;

public class LoginTest {
private Login login;

@BeforeClass
public void init() {
   this.login = new Login();
}

@DataProvider
public Object[][] testData() {
   // 0 - Successful Login Attempt
   // 1 - Failed Login Attempt
   return new Object[][]{
                   //positive test cases
                   {"user7", "UserUser123", "chrome", 0},
                   {"user8", "UserUser123", "firefox", 0},
                   {"user9", "UserUser123", "chrome", 0},
                   {"storemanager55", "UserUser123", "chrome", 0},
                   {"storemanager56", "UserUser123", "chrome", 0},
                   {"salesmanager107", "UserUser123", "chrome", 0},
                   {"salesmanager108", "UserUser123", "chrome", 0},
                   {"salesmanager109", "UserUser123", "chrome", 0},
                   //negative test cases
                   {"user7", "$%^", "chrome", 1},
                   {"user8", "Us", "chrome", 1},
                   {"user9", "^&*(67", "chrome", 1},
                   {"storemanager55", "dfgh", "chrome", 1},
                   {"storemanager56", "dfg67", "chrome", 1},
                   {"salesmanager107", "ert67", "chrome", 1},
                   {"salesmanager108", "UserUser123-67", "chrome", 1},
                   {"salesmanager109", "UserUser123", "chrome", 1}
   };
}

@Test(dataProvider = "testData")
public void testSearch(String username, String password, String browser, int unexpectedValue) throws InterruptedException, IOException {
   login.login(username, password, browser, unexpectedValue);
}
}
