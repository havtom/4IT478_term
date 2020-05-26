package cz.churchcrm.testframework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class EventsListPage extends Page {

    private static final String URL = "https://digitalnizena.cz/church/ListEvents.php";
    private static final String CSS_SELECTOR_EDIT_FIRST_RECORD = "[data-original-title='Edit']";

    public EventsListPage(WebDriver driver) {
        super(driver);
    }

    public void editFirstRecord() {
        WebElement editButton = driver.findElement(By.cssSelector(CSS_SELECTOR_EDIT_FIRST_RECORD));
        editButton.click();
    }
}
