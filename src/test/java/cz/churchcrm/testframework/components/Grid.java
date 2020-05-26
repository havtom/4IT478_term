package cz.churchcrm.testframework.components;

import cz.churchcrm.testframework.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private String cssSelector;
    private ChromeDriver driver;
    private List<GridRow> gridRows;

    public Grid(String cssSelector, ChromeDriver driver) {
        this.cssSelector = cssSelector;
        this.driver = driver;
        this.gridRows = new ArrayList<>();
    }

    public GridRow getRow(int i) {
        return gridRows.get(i);
    }

    public List<GridRow> search(String searchQuery) {
        String searchSelector = "div.dataTables_filter > label > input[type=search]";
        TestUtils.waitForElementPresence(driver, searchSelector, 5);

        WebElement searchInput = driver.findElement(By.cssSelector(searchSelector));
        searchInput.sendKeys(searchQuery);

        List<WebElement> rows = driver.findElements(By.cssSelector("tbody > tr"));
        rows.remove(0); // there's probably always one empty tr element.

        return fillGridRowsList(rows);
    }

    public Grid selectPagination(String numberOfEntries) {
        String paginationSelector = ".dataTables_length select";
        TestUtils.waitForElementPresence(driver, paginationSelector, 5);

        WebElement paginationItem = driver.findElement(By.cssSelector(paginationSelector));
        Select selectEntriesNoFromDropDown = new Select(paginationItem);
        selectEntriesNoFromDropDown.selectByVisibleText(numberOfEntries);
        return this;
    }

    private List<GridRow> fillGridRowsList(List<WebElement> rows) {
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.cssSelector("td"));
            GridRow gridRow = new GridRow(columns);
            gridRows.add(gridRow);
        }
        return gridRows;
    }
}