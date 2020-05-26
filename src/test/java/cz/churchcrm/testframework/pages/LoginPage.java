package cz.churchcrm.testframework.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginPage extends Page {
    private static final String URL = "https://digitalnizena.cz/church/";

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public DashboardPage login(String username, String password) {
        driver.get(URL);

        driver.findElement(By.id("UserBox")).sendKeys(username);
        driver.findElement(By.id("PasswordBox")).sendKeys(password);
        driver.findElement(By.className("btn-primary")).click();

        return new DashboardPage(driver);
    }

    public DashboardPage loginWithVisiblePwd(String username, String password) throws InterruptedException {
        driver.get(URL);

        driver.findElement(By.id("UserBox")).sendKeys(username);
        driver.findElement(By.id("PasswordBox")).sendKeys(password);
        Thread.sleep(TimeUnit.SECONDS.toMillis(1));
        driver.findElement(By.cssSelector(".fa")).click();
        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        driver.findElement(By.className("btn-primary")).click();

        return new DashboardPage(driver);
    }

    public void shouldNotBeOpen() {
        Assert.assertEquals(driver.getCurrentUrl(), "xxxx");
        Assert.assertNotEquals(driver.getCurrentUrl(), "");
        // Assert.assertEquals(driver.findElements(By.cssSelector(".login-box"))).isEmpty();
    }

    public void shouldBeOpen() {
        // assertThat(driver.getCurrentUrl()).isEqualTo(URL_LOGIN);
        // assertThat(driver.findElements(By.cssSelector(".login-box"))).isNotEmpty();
    }

    public void shouldBeErrorMessage() {
        // WebElement errorDiv = driver.findElement(By.cssSelector("#Login .alert.alert-error"));    // there are in fact two .alert .alert-error boxes in page, we want that for #Login div
        // assertThat(errorDiv.getText()).isEqualTo("Invalid login or password");
    }

    public DashboardPage login() throws InterruptedException {
        return this.login("church", "church12345");
    }
}
