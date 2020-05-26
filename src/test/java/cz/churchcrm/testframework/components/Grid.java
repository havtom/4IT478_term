package cz.churchcrm.testframework.components;

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
        this.gridRows = new ArrayList<>();
    }

    public GridRow getRow(int i){
        return gridRows.get(i);
    }

    public List<GridRow> search(String searchQuery) {
        WebElement searchInput = driver.findElement(By.cssSelector("div.dataTables_filter > label > input[type=search]"));
        searchInput.sendKeys(searchQuery);

        List<WebElement> rows = driver.findElements(By.cssSelector("tbody > tr"));
        rows.remove(0); // there's probably always one empty tr element.

        return fillGridRowsList(rows);
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