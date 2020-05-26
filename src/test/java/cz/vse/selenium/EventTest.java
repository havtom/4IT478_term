package cz.vse.selenium;

import cz.churchcrm.testframework.components.EventType;
import cz.churchcrm.testframework.components.Grid;
import cz.churchcrm.testframework.components.GridRow;
import cz.churchcrm.testframework.components.SideMenu;
import cz.churchcrm.testframework.pages.EventEditorPage;
import cz.churchcrm.testframework.pages.EventsListPage;
import cz.churchcrm.testframework.pages.LoginPage;
import org.junit.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

public class EventTest extends BaseTest {

    private String testEventTitle;

    @Test
    public void shouldCreateEventAndCheckIt() throws InterruptedException {
        // GIVEN
        LoginPage loginpage = new LoginPage(driver);
        loginpage.login();

        SideMenu sideMenu = new SideMenu(driver);
        sideMenu.goToAddEvent();

        // WHEN
        EventEditorPage eventEditorPage = new EventEditorPage(driver);
        EventType eventType = EventType.CHURCH_SERVICE;
        eventEditorPage.createNewEvent(eventType);

        fillEventInfo(eventEditorPage);

        eventEditorPage.saveEvent();

        EventsListPage eventsListPage = new EventsListPage(driver);


        String eventTableSelector = "listEvents";
        Grid eventsGrid = new Grid(eventTableSelector, driver);

        eventsGrid.search(testEventTitle);
        GridRow gridRow = eventsGrid.getRow(1);

//        eventsListPage.editRecord();

//        eventEditorPage.
//        System.out.println(gridRow.toString());
        assertThat(gridRow.shouldContain(testEventTitle)).isTrue();





//        eventEditorPage.saveChanges();
        //THEN


    }

    private void fillEventInfo(EventEditorPage eventEditorPage) {

        testEventTitle = "ML" + UUID.randomUUID().toString();
        eventEditorPage.editTitle(testEventTitle);
        eventEditorPage.editDesc("Newly Created Event");
        eventEditorPage.editTotalCount("8");
        eventEditorPage.addIntoIFrame("");
        eventEditorPage.setDate("2020-06-06 12:00 AM - 2020-06-06 11:30 PM");
        eventEditorPage.setEventActive(false);
    }
}
