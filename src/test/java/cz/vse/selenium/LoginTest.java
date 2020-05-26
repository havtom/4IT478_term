package cz.vse.selenium;


import cz.churchcrm.testframework.pages.LoginPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class LoginTest extends BaseTest {

    @Test
    public void shouldNotLoginUsingInvalidCredentials_InvalidUsername() throws InterruptedException {
        //Given
        //driver.get("http://digitalnizena.cz/church/");
        //Duplicity with login driver.get

        //When
        LoginPage loginpageInvalidUsername = new LoginPage(driver);
        loginpageInvalidUsername.loginWithVisiblePwd("Milan", "church12345");

        //Then
        WebElement errorMsg = driver.findElement(By.className("alert-error"));
        String loginUrl = driver.getCurrentUrl();

        Assert.assertTrue("Warning message not found!", errorMsg.getText().contains("Invalid"));
        Assert.assertTrue("User is not on the login page.", loginUrl.contains("Login.php"));
    }


    @Test
    public void shouldNotLoginUsingInvalidCredentials_InvalidPassword() throws InterruptedException {
        //Given
        //driver.get("http://digitalnizena.cz/church/");

        //When
        LoginPage loginpageInvalidPassword = new LoginPage(driver);
        loginpageInvalidPassword.loginWithVisiblePwd("church", "12345");

        //Then
        WebElement errorMsg = driver.findElement(By.className("alert-error"));
        String loginUrl = driver.getCurrentUrl();

        Assert.assertTrue("Warning message not found!", errorMsg.getText().contains("Invalid"));
        Assert.assertTrue("User is not on the login page.", loginUrl.contains("Login.php"));
    }

    @Test
    public void shouldNotLoginUsingInvalidCredentials_InvalidUsernameAndPassword() throws InterruptedException {
        //Given
        //driver.get("http://digitalnizena.cz/church/");

        //When
        LoginPage loginpageInvalidUsernameAndPassword = new LoginPage(driver);
        loginpageInvalidUsernameAndPassword.loginWithVisiblePwd("Peter221", "1234554321");

        //Then
        WebElement errorMsg = driver.findElement(By.className("alert-error"));
        String loginUrl = driver.getCurrentUrl();

        Assert.assertTrue("Warning message not found!", errorMsg.getText().contains("Invalid"));
        Assert.assertTrue("User is not on the login page.", loginUrl.contains("Login.php"));
    }

    @Test
    public void ShouldLoginUsingValidCredentials () {
        //Given

        //When
        LoginPage login = new LoginPage(driver);
        login.login("church", "church12345");

        //Then
        Assert.assertTrue(driver.getTitle().startsWith("ChurchCRM: Welcome to"));
    }
}

