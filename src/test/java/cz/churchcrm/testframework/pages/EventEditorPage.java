package cz.churchcrm.testframework.pages;

import cz.churchcrm.testframework.components.EventType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class EventEditorPage extends Page {

    public static final String URL = "https://digitalnizena.cz/church/EventEditor.php";
    public static final String CSS_SELECTOR_EVENT_TITLE = "[name='EventTitle']";
    public static final String CSS_SELECTOR_EVENT_DESC = "[name='EventDesc']";
    public static final String CSS_SELECTOR_EVENT_TOTAL_COUNT = "[name='EventCount[]']";
    public static final String CSS_SELECTOR_EVENT_STATUS_INACTIVE = "[name='EventStatus'][value='1']";
    public static final String CSS_SELECTOR_SAVE_EVENT_BTN = "[name='SaveChanges']";
    public static final String CSS_SELECTOR_EVENT_DATE_RANGE = "#EventDateRange";

    public EventEditorPage(WebDriver driver) {
        super(driver);
    }

    public void goToEventEditorPage() {
        driver.get(URL);
    }

    public void createNewEvent(EventType eventType) {

        WebElement classificationSelectElement = driver.findElement(By.cssSelector("#event_type_id"));
        Select classificationSelect = new Select(classificationSelectElement);
        classificationSelect.selectByIndex(eventType.nr);
    }

    public void editTitle(String s) {
        fillField(s, CSS_SELECTOR_EVENT_TITLE);
    }

    public void editDesc(String s) {
        fillField(s, CSS_SELECTOR_EVENT_DESC);
    }

    public void editTotalCount(String s) {
        fillField(s, CSS_SELECTOR_EVENT_TOTAL_COUNT);
    }

    public void setDate(String s) {
        fillField(s, CSS_SELECTOR_EVENT_DATE_RANGE);
    }

    public void setEventActive(boolean b) {
        if (!b) {
            WebElement inactiveEventStatusRadioBtn = driver
                    .findElement(By.cssSelector(CSS_SELECTOR_EVENT_STATUS_INACTIVE));
            inactiveEventStatusRadioBtn.click();
        }
    }

    public void addIntoIFrame(String s) {
        WebElement frame = driver.switchTo().frame(0).findElement(By.cssSelector("body"));
        frame.sendKeys(s);
        driver.switchTo().parentFrame();
    }

    public void saveEvent() {
        WebElement saveChangesButton = driver.findElement(By.cssSelector(CSS_SELECTOR_SAVE_EVENT_BTN));
        saveChangesButton.click();
    }

    private void fillField(String s, String cssSelector) {
        WebElement titleInput = driver.findElement(By.cssSelector(cssSelector));
        titleInput.clear();
        titleInput.sendKeys(s);
    }
}
