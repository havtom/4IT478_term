package cz.vse.selenium;

import cz.churchcrm.testframework.components.EventType;
import cz.churchcrm.testframework.components.Grid;
import cz.churchcrm.testframework.components.GridRow;
import cz.churchcrm.testframework.components.SideMenu;
import cz.churchcrm.testframework.pages.EventEditorPage;
import cz.churchcrm.testframework.pages.EventsListPage;
import cz.churchcrm.testframework.pages.LoginPage;
import cz.churchcrm.testframework.utils.TestUtils;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class EventTest extends BaseTest {

    private String testEventTitle;
    private String iFrameInput;

    @Before
    public void initEventTests() throws InterruptedException {
        LoginPage loginpage = new LoginPage(driver);
        loginpage.login();
    }

    @Test
    public void shouldCreateEventAndCheckIt() throws InterruptedException {
        // GIVEN
        SideMenu sideMenu = new SideMenu(driver);
        sideMenu.goToAddEvent();

        // WHEN
        EventEditorPage eventEditorPage = new EventEditorPage(driver);
        EventType eventType = EventType.CHURCH_SERVICE;
        eventEditorPage.createNewEvent(eventType);

        fillEventInfo(eventEditorPage);
        eventEditorPage.saveEvent();

        EventsListPage eventsListPage = new EventsListPage(driver);
        Grid eventsGrid = new Grid(driver);
        GridRow gridRow = getUpdatedFirstRow(eventsGrid);

        eventsListPage.editFirstRecord();

        //THEN
        SoftAssertions softly = new SoftAssertions();

        softly.assertThat(gridRow.shouldContain(testEventTitle)).isTrue();

        WebElement actualEventTitle = driver.findElement(By.cssSelector(EventEditorPage.CSS_SELECTOR_EVENT_TITLE));
        softly.assertThat(actualEventTitle.getAttribute("value")).isEqualTo(testEventTitle);

        WebElement actualFrameValue = driver.switchTo().frame(0).findElement(By.cssSelector("p"));
        softly.assertThat(actualFrameValue.getText()).isEqualTo(iFrameInput);

        driver.switchTo().parentFrame();

        softly.assertAll();
    }

    @Test
    public void shouldDeleteEvent() {
        EventEditorPage eventEditorPage = new EventEditorPage(driver);
        eventEditorPage.goToEventEditorPage();

        eventEditorPage.createNewEvent(EventType.AAA);
        fillEventInfo(eventEditorPage);

        eventEditorPage.saveEvent();

        EventsListPage eventsListPage = new EventsListPage(driver);
        Grid eventsGrid = new Grid(driver);
        GridRow gridRow = getUpdatedFirstRow(eventsGrid);
        assertThat(gridRow.shouldContain(testEventTitle)).isTrue();

        eventsListPage.deleteFirstRecord();

        gridRow = getUpdatedFirstRow(eventsGrid);
        assertThat(gridRow.shouldContain(testEventTitle)).isFalse();
    }

    private GridRow getUpdatedFirstRow(Grid eventsGrid) {
        eventsGrid.searchQuery(testEventTitle).fillGridRowsList();
        return eventsGrid.getRow(1);
    }

    private void fillEventInfo(EventEditorPage eventEditorPage) {
        testEventTitle = "ML" + UUID.randomUUID().toString();
        iFrameInput = "blablablabla";
        eventEditorPage.editTitle(testEventTitle);
        eventEditorPage.editDesc("Newly Created Event");
        eventEditorPage.editTotalCount("8");
        eventEditorPage.setDate("2020-06-06 12:00 AM - 2020-06-06 11:30 PM");
        TestUtils.waitForElementPresence(driver, "iframe", 2);
        eventEditorPage.addIntoIFrame(iFrameInput);
        eventEditorPage.setEventActive(false);
    }
}
