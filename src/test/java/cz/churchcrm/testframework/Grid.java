package cz.churchcrm.testframework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Grid {
    private ChromeDriver driver;
    // TODO: Check if it's really needed
    private String cssSelector;


    public Grid(ChromeDriver driver, String cssSelector) {
        this.driver = driver;
        this.cssSelector = cssSelector;
    }

    public List<GridRow> search(String searchQuery) {
        WebElement gridWrapper = driver.findElement(By.cssSelector(""));
        WebElement searchInput = gridWrapper.findElement(By.cssSelector("#depositsTable_filter > label > input[type=search]"));
        searchInput.sendKeys(searchQuery);
        searchInput.submit();

        // TODO: Return something useful
        return null;
    }
}
