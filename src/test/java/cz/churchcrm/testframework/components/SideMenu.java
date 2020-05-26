package cz.churchcrm.testframework.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SideMenu {
    private ChromeDriver driver;
    // Just an idea: map with indexes / names.

    public SideMenu(ChromeDriver driver) {
        this.driver = driver;
    }

    public void goToAddNewPersonPage() throws InterruptedException {
        openSubmenuAndClickItem(2, 1);
    }

    public void goToAllDeposits() throws InterruptedException {
        openSubmenuAndClickItem(7, 0);
    }

    /**
     * Returns side menu as list of 'li' items (clickable).
     */
    private List<WebElement> sideMenu() {
        return driver.findElements(By.cssSelector("aside.main-sidebar > section > ul > li"));
    }

    /**
     * Returns open submenu as a list of hyperlinks.
     */
    private List<WebElement> subMenuItems(WebElement menuItem) throws InterruptedException {
        return menuItem.findElements(By.cssSelector("li.treeview.menu-open > ul > li > a"));
    }

    /**
     * Opens menu item given by [menuItemNumber] and then selects (and click on) sub menu item given by [subMenuItemNumber]
     */
    private void openSubmenuAndClickItem(int menuItemNumber, int subMenuItemNumber) throws InterruptedException {
        WebElement menuItem = sideMenu().get(menuItemNumber);
        menuItem.click();
        Thread.sleep(TimeUnit.SECONDS.toMillis(2)); // stability improvement
        subMenuItems(menuItem).get(subMenuItemNumber).click();
    }
}
