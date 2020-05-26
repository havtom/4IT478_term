package cz.churchcrm.testframework.pages;

import org.openqa.selenium.WebDriver;

public class EventsListPage extends Page {

    private static final String URL = "https://digitalnizena.cz/church/ListEvents.php";

    public EventsListPage(WebDriver driver) {
        super(driver);
    }
}
