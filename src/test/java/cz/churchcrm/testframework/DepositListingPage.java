package cz.churchcrm.testframework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DepositListingPage extends Page {
    public DepositListingPage(WebDriver driver) {
        super(driver);
    }

    public void addDeposit(String depositComment, String depositDate) {
        WebElement depositCommentInput = driver.findElement(By.id("depositComment"));
        depositCommentInput.sendKeys(depositComment);

        WebElement depositCommentDate = driver.findElement(By.id("depositDate"));
        depositCommentDate.clear();
        depositCommentDate.sendKeys(depositDate);

        WebElement depositSubmitComment = driver.findElement(By.id("addNewDeposit"));
        depositSubmitComment.click();
    }
}
