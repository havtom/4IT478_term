package cz.churchcrm.testframework.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
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
        rows.remove(0); // there's probably always one empty tr element.

        return fillGridRows(rows);
    }

    private List<GridRow> fillGridRows(List <WebElement> rows) {
        List<GridRow> gridRows = new ArrayList<GridRow>();
        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.cssSelector("td"));

            GridRow gridRow = new GridRow();
            // each found row (tr) should have these columns:
            // td (columns): 0 id, 1 date, 2 total, 3 comment, 4 closed, 5 type
            System.out.println(columns.get(0).getText());
            gridRow.setId(columns.get(0).getText());
            // Date in tricky format (input YYYY-MM-DD, table MM-DD-YY)
            gridRow.setDate(columns.get(1).getText());
            gridRow.setComment(columns.get(3).getText());
            gridRow.setType(columns.get(5).getText());

            gridRows.add(gridRow);
        }
        return gridRows;
    }
}
