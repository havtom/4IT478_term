package cz.churchcrm.testframework.components;

import cz.churchcrm.testframework.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    private static final String EDIT_RECORD_ICON = "fa-search-plus";
    private static final String SEARCH_SELECTOR = "div.dataTables_filter > label > input[type=search]";
    private static final String PAGINATION_SELECTOR = ".dataTables_length select";
    private static final String DELETE_BUTTON = "deleteSelectedRows";
    private static final String DATA_TABLE_ROWS = "tbody > tr";
    private static final String DATA_TABLE_EMPTY = "dataTables_empty";

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
        driver.findElement(By.className(EDIT_RECORD_ICON)).click();
    }

    public Grid searchQuery(String searchQuery) {
        TestUtils.waitForElementPresence(driver, SEARCH_SELECTOR, 5);

        WebElement searchInput = driver.findElement(By.cssSelector(SEARCH_SELECTOR));
        searchInput.clear();
        searchInput.sendKeys(searchQuery);

        return this;
    }

    public Grid selectPagination(String numberOfEntries) {
        TestUtils.waitForElementPresence(driver, PAGINATION_SELECTOR, 5);
        TestUtils.selectItemFromDropdown(driver, PAGINATION_SELECTOR, numberOfEntries);
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
        driver.findElement(By.id(DELETE_BUTTON)).click();
        return this;
    }

    public boolean shouldBeEmpty(){
        return driver.findElement(By.className(DATA_TABLE_EMPTY)).isDisplayed();
    }

    public WebElement getDeleteButton(){
        return driver.findElement(By.id(DELETE_BUTTON));
    }

    private Grid selectRow(int i) {
        getRows().get(i).click();
        return this;
    }

    private List<WebElement> getRows() {
        return driver.findElements(By.cssSelector(DATA_TABLE_ROWS));
    }
}