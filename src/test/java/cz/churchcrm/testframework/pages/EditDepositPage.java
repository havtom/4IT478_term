package cz.churchcrm.testframework.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class EditDepositPage extends Page {
    public EditDepositPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyDepositSlip(String id, String comment, String date) {
        return driver.findElement(By.className("content-header")).getText().contains(id)
                && driver.findElement(By.id("DepositDate")).getAttribute("value").matches(date)
                && driver.findElement(By.id("Comment")).getAttribute("value").matches(comment);
    }
}
