package cz.churchcrm.testframework.components;

import cz.churchcrm.testframework.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private ChromeDriver driver;
    private List<GridRow> gridRows;

    public Grid(ChromeDriver driver) {
        this.driver = driver;
    }

    public GridRow getRow(int i) {
        return gridRows.get(i);
    }

    public void searchAndEditRecord(String searchQuery) {
        searchQuery(searchQuery);
        editTheFirstRecord();
    }

    public void editTheFirstRecord() {
        driver.findElement(By.className("fa-search-plus")).click();
    }

    public Grid searchQuery(String searchQuery) {
        String searchSelector = "div.dataTables_filter > label > input[type=search]";
        TestUtils.waitForElementPresence(driver, searchSelector, 5);

        WebElement searchInput = driver.findElement(By.cssSelector(searchSelector));
        searchInput.clear();
        searchInput.sendKeys(searchQuery);

        return this;
    }

    public Grid selectPagination(String numberOfEntries) {
        String paginationSelector = ".dataTables_length select";
        TestUtils.waitForElementPresence(driver, paginationSelector, 5);
        TestUtils.selectItemFromDropdown(driver, paginationSelector, numberOfEntries);
        return this;
    }

    public List<GridRow> fillGridRowsList() {
        gridRows = new ArrayList<>();
        List<WebElement> rows = getRows();
        rows.remove(0); // there's always one empty tr element.
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.cssSelector("td"));
            GridRow gridRow = new GridRow(columns);
            gridRows.add(gridRow);
        }
        return gridRows;
    }

    public Grid selectFirstRow() {
        selectRow(1);
        return this;
    }

    public Grid deleteSelectedRows() {
        driver.findElement(By.id("deleteSelectedRows")).click();
        return this;
    }

    public boolean shouldBeEmpty(){
        return driver.findElement(By.className("dataTables_empty")).isDisplayed();
    }

    private Grid selectRow(int i) {
        getRows().get(i).click();
        return this;
    }

    private List<WebElement> getRows() {
        return driver.findElements(By.cssSelector("tbody > tr"));
    }
}