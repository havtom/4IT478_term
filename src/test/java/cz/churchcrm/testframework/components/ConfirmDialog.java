package cz.churchcrm.testframework.components;

import cz.churchcrm.testframework.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ConfirmDialog {
    private ChromeDriver driver;

    public ConfirmDialog(ChromeDriver driver) {
        this.driver = driver;
    }

    public void confirmAction() throws InterruptedException {
        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
        driver.findElement(By.cssSelector(".btn-primary.bootbox-accept")).click();
    }

    public void cancelAction(){
        driver.findElement(By.cssSelector(".btn-secondary.bootbox-cancel")).click();
    }
}
