package com.vytrack.serkan;

import org.testng.annotations.*;

import java.io.IOException;

public class LoginTest {
private Login login;

@BeforeMethod
public void init() {
   login = new Login();
   login.initElement();
}

@DataProvider
public Object[][] testDataPositive() {
   return new Object[][]{
                   {"user7", "UserUser123"},
                   {"user8", "UserUser123"},
                   {"user9", "UserUser123"},
                   {"storemanager55", "UserUser123"},
                   {"storemanager56", "UserUser123"},
                   {"salesmanager107", "UserUser123"},
                   {"salesmanager108", "UserUser123"},
                   {"salesmanager109", "UserUser123"}
   };
}

@DataProvider
public Object[][] testDataNegative() {
   return new Object[][]{
                   {"user7", "$%^"},
                   {"user8", "Us"},
                   {"user9", "^&*(67"},
                   {"storemanager55", "dfgh"},
                   {"storemanager56", "dfg67"},
                   {"salesmanager107", "ert67"},
                   {"salesmanager108", "UserUser123-67"},
                   {"salesmanager109", "UserUser1234"}
   };
}

@Test(dataProvider = "testDataPositive")
public void positiveTestCases(String username, String password) throws IOException {
   login.login(username, password);
}

@Test(dataProvider = "testDataNegative")
public void negativeTestCases(String username, String password) throws IOException {
   login.verifyUnsuccessfulLogin(username, password);
}

@AfterMethod
public void tearDDown() {
   login.driver.close();
   login = null;
}

}
