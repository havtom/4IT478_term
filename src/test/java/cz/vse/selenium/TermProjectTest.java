package cz.vse.selenium;

import cz.churchcrm.testframework.components.Grid;
import cz.churchcrm.testframework.components.GridRow;
import cz.churchcrm.testframework.pages.DepositListingPage;
import cz.churchcrm.testframework.pages.DepositPage;
import cz.churchcrm.testframework.pages.LoginPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;
import java.util.UUID;

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
    public void shouldNotLoginUsingInvalidCredentials() {
        // given
        driver.get("http://digitalnizena.cz/church/");

        // when
        // To be replaced by LoginPage().login()
        loginForm("church", "12345");

        // then
        WebElement errorMsg = driver.findElement(By.className("alert-error"));
        String loginUrl = driver.getCurrentUrl();

        Assert.assertTrue("Warning message not found!", errorMsg.getText().contains("Invalid"));
        Assert.assertTrue("User is not on the login page.", loginUrl.contains("Login.php"));
    }

    /**
     * Test that adds and verifies deposit.
     * TODO: Todo.
     */
    @Test
    public void addDepositAndVerify() {
        LoginPage loginpage = new LoginPage(driver);
        loginpage.login();

        DepositPage depositPage = new DepositPage(driver);
        DepositListingPage dlPage = depositPage.gotoAllDeposits(driver);

        String depositComment = UUID.randomUUID().toString() + "ID";
        String depositDate = "2020-05-20";
        dlPage.addDeposit(depositComment, depositDate);


        Grid depositsGrid = new Grid(driver);
        List<GridRow> rows = depositsGrid.search(depositComment);
        // rows.get(0).shouldContain(depositComment);

        // Verification… found row shouldContain date / type / comment

        // getDepositComment
        // depositsGrid.getRows(column...) / rows.get(0).getDepositComment().equals(depositComment)
    }

    /**
     * Temporary method for the sake of example.
     * TODO: Remove.
     */
    private void loginForm(String login, String password) {
        WebElement usernameInput = driver.findElement(By.id("UserBox"));
        usernameInput.sendKeys(login);
        WebElement passwordInput = driver.findElement(By.id("PasswordBox"));
        passwordInput.sendKeys(password);
        WebElement loginButton = driver.findElement(By.className("btn-primary"));
        loginButton.click();
    }
}
