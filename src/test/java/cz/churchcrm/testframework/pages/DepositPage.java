package cz.churchcrm.testframework.pages;

import org.openqa.selenium.chrome.ChromeDriver;

public class DepositPage extends Page {
    public DepositPage(ChromeDriver driver) {
        super(driver);
    }

    public DepositListingPage gotoAllDeposits(ChromeDriver driver) {
        driver.get("http://digitalnizena.cz/church/FindDepositSlip.php");
        return new DepositListingPage(driver);
    }
}
