package cz.churchcrm.testframework.components;

import org.openqa.selenium.WebElement;

import java.util.List;

public class GridRow {
    private String row;

    public GridRow(List<WebElement> columns) {
        String separator = "%-%";
        for (WebElement column : columns) {
            row += column.getText() + separator;
        }
    }

    public boolean shouldContain(String match) {
        return row.contains(match);
    }

    public String getRow() {
        return row;
    }
}
