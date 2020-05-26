package cz.churchcrm.testframework.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestUtils {
    private static final String OLD_FORMAT = "yyyy-mm-dd";
    private static final String NEW_FORMAT = "mm-dd-yy";

    public static String changeDateFormat(String oldDate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
        Date d = sdf.parse(oldDate);
        sdf.applyPattern(NEW_FORMAT);

        return sdf.format(d);
    }

    public static void waitForElementPresence(ChromeDriver driver, String cssSelector, int timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(cssSelector)));
    }

    public static void selectItemFromDropdown(ChromeDriver driver, String cssSelector, String option){
        WebElement dropdownItem = driver.findElement(By.cssSelector(cssSelector));
        Select dropdownMenu = new Select(dropdownItem);
        dropdownMenu.selectByVisibleText(option);
    }
}
