package cz.churchcrm.testframework;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Grid {
    private ChromeDriver driver;

    public Grid(ChromeDriver driver) {
        this.driver = driver;
    }

    public List<GridRow> search(String searchQuery) {
        WebElement searchInput = driver.findElement(By.cssSelector("div.dataTables_filter > label > input[type=search]"));
        searchInput.sendKeys(searchQuery);

        List<WebElement> rows = driver.findElements(By.cssSelector("tbody > tr"));
        rows.remove(0); // there's probably one empty tr element.
        // each found row (tr) should have this structure:
        // td (columns): 0 id, 1 date, 2 total, 3 comment, 4 closed, 5 type
        List<WebElement> columns = rows.get(0).findElements(By.cssSelector("td"));
        String column = columns.get(1).getText();
        // Debug output
        System.out.println(column);
        // Date in tricky format (input YYYY-MM-DD, table MM-DD-YY)
        Assert.assertTrue(column.contains("05-20-20"));
        
        // TODO: Return something useful
        return null;
    }
}
