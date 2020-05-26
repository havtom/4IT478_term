package cz.churchcrm.testframework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Page {
    protected WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementPresence(String cssSelector, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
    }

    public void shouldBeOpen() {

    }

    public void open(String url) {
        driver.get(url);
    }
}
