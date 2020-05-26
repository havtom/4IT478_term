package cz.vse.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public abstract class BaseTest {

    protected ChromeDriver driver;

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
    public void tearDown() throws InterruptedException {
        // Close Chrome after the test completion
        Thread.sleep(3000);
        driver.quit();
    }
}
