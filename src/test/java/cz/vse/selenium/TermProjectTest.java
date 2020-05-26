package cz.vse.selenium;

import cz.churchcrm.testframework.components.Grid;
import cz.churchcrm.testframework.components.GridRow;
import cz.churchcrm.testframework.components.SideMenu;
import cz.churchcrm.testframework.pages.DepositListingPage;
import cz.churchcrm.testframework.pages.DepositPage;
import cz.churchcrm.testframework.pages.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.text.ParseException;
import java.util.UUID;

import static cz.churchcrm.testframework.utils.TestUtils.changeDateFormat;
import static org.junit.Assert.assertTrue;

public class TermProjectTest {
    private ChromeDriver driver;

    @Before
    public void init() {
        ChromeOptions chromeOptions = new ChromeOptions();

        boolean runOnTravis = false;
        if (runOnTravis) {
            chromeOptions.addArguments("headless");
        } else {
            // Choose Chrome driver by used OS.
            String os = System.getProperty("os.name").toLowerCase();
            String driverPath = "";
            if (os.startsWith("win")) {
                driverPath = "src/test/resources/drivers/chromedriver.exe";
            } else {
                driverPath = "src/test/resources/drivers/chromedriver_stable";
            }
            System.setProperty("webdriver.chrome.driver", driverPath);
        }

        // ChromeDriverService service = new ChromeDriverService()
        driver = new ChromeDriver(chromeOptions);
        // Run Chrome in the fullscreen.
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        // Close Chrome after the test completion
        // driver.quit();
    }

    /**
     * Basic flow, which tests invalid credentials.
     */
    @Test
    public void shouldNotLoginUsingInvalidCredentials_InvalidUsername() {
        // given
        //driver.get("http://digitalnizena.cz/church/");
        //Duplicity with login driver.get

        // when
        LoginPage loginpageInvalidUsername = new LoginPage(driver);
        loginpageInvalidUsername.login("Milan", "church12345");

        // then
        WebElement errorMsg = driver.findElement(By.className("alert-error"));
        String loginUrl = driver.getCurrentUrl();

        assertTrue("Warning message not found!", errorMsg.getText().contains("Invalid"));
        assertTrue("User is not on the login page.", loginUrl.contains("Login.php"));
    }

    /**
     * Basic flow, which tests invalid credentials.
     */
    @Test
    public void shouldNotLoginUsingInvalidCredentials_InvalidPassword() {
        // given
        //driver.get("http://digitalnizena.cz/church/");

        // when
        LoginPage loginpageInvalidPassword = new LoginPage(driver);
        loginpageInvalidPassword.login("church", "12345");

        // then
        WebElement errorMsg = driver.findElement(By.className("alert-error"));
        String loginUrl = driver.getCurrentUrl();

        assertTrue("Warning message not found!", errorMsg.getText().contains("Invalid"));
        assertTrue("User is not on the login page.", loginUrl.contains("Login.php"));
    }

    /**
     * Basic flow, which tests invalid credentials.
     */
    @Test
    public void shouldNotLoginUsingInvalidCredentials_InvalidUsernameAndPassword() {
        // given
        //driver.get("http://digitalnizena.cz/church/");

        // when
        LoginPage loginpageInvalidUsernameAndPassword = new LoginPage(driver);
        loginpageInvalidUsernameAndPassword.login("Peter221", "1234554321");

        // then
        WebElement errorMsg = driver.findElement(By.className("alert-error"));
        String loginUrl = driver.getCurrentUrl();

        assertTrue("Warning message not found!", errorMsg.getText().contains("Invalid"));
        assertTrue("User is not on the login page.", loginUrl.contains("Login.php"));
    }

    /**
     * Test that adds and verifies deposit.
     * TODO: Todo.
     */
    @Test
    public void addDepositAndVerify() throws ParseException {
        LoginPage loginpage = new LoginPage(driver);
        loginpage.login();

        DepositPage depositPage = new DepositPage(driver);
        DepositListingPage dlPage = depositPage.gotoAllDeposits(driver);

        String depositComment = UUID.randomUUID().toString() + "ID";
        String depositDate = "2020-05-20";
        dlPage.addDeposit(depositComment, depositDate);


        Grid depositsGrid = new Grid(driver);
        depositsGrid.search(depositComment);

        GridRow row = depositsGrid.getRow(0);

        assertTrue(row.shouldContain(depositComment) && row.shouldContain(changeDateFormat(depositDate)));

        // Check edit page
        // getDepositComment
        // depositsGrid.getRows(column...) / rows.get(0).getDepositComment().equals(depositComment)
    }

    /**
     * Menu showcase...
     * TODO: Remove
     */
    @Test
    public void dummyTest() throws InterruptedException {
        LoginPage loginpage = new LoginPage(driver);
        loginpage.login();

        SideMenu sideMenu = new SideMenu(driver);

        sideMenu.goToAddNewPersonPage();
        //sideMenu.goToAllDeposits();
    }
}
