package cz.vse.selenium;

import cz.churchcrm.testframework.components.EventType;
import cz.churchcrm.testframework.components.SideMenu;
import cz.churchcrm.testframework.pages.EventEditorPage;
import cz.churchcrm.testframework.pages.EventsListPage;
import cz.churchcrm.testframework.pages.LoginPage;
import org.junit.Test;

public class EventTest extends BaseTest {

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





//        eventEditorPage.saveChanges();
        //THEN


    }

    private void fillEventInfo(EventEditorPage eventEditorPage) {
        eventEditorPage.editTitle("dasdsdsdss");
        eventEditorPage.editDesc("Newly Created Event");
        eventEditorPage.editTotalCount("8");
        eventEditorPage.addIntoIFrame("");
        eventEditorPage.setDate("2020-06-06 12:00 AM - 2020-06-06 11:30 PM");
        eventEditorPage.setEventActive(false);
    }
}
