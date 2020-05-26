package cz.vse.selenium;

import cz.churchcrm.testframework.components.SideMenu;
import cz.churchcrm.testframework.pages.LoginPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PersonFamilyTest extends BaseTest {

    @Test
    public void shouldAddPeopleFromMenu() throws InterruptedException {
        //Given
        LoginPage loginpage = new LoginPage(driver);
        loginpage.login();

        //When
        SideMenu sideMenu = new SideMenu(driver);
        sideMenu.goToAddNewPersonPage();

        WebElement genderSelector = driver.findElementByName("Gender");
        Select selectGenderFromDropDown = new Select(genderSelector);
        selectGenderFromDropDown.selectByVisibleText("Male");

        driver.findElementByCssSelector("#Title").sendKeys("Mr.");
        driver.findElementByCssSelector("#FirstName").sendKeys("Anthony");
        driver.findElementByCssSelector("#LastName").clear(); //to be sure LastName is empty

        WebElement familySelectorElement = driver.findElementByName("Family");
        Select selectFamilyFromDropdown = new Select(familySelectorElement);
        selectFamilyFromDropdown.selectByVisibleText("Unassigned"); //to be sure Family is not assigned

        driver.findElementByCssSelector("#PersonSaveButton").click();

        //Then
        Assert.assertTrue(driver.findElementByCssSelector("div.alert.alert-danger.alert-dismissable").getText().contains("Invalid fields or selections. Changes not saved! Please correct and try again!"));
    }

    @Test
    public void shouldAddFamilyAndPerson() throws InterruptedException {
        //Given
        LoginPage loginpage = new LoginPage(driver);
        loginpage.login();

        //When
        SideMenu sideMenuFamily = new SideMenu(driver);
        sideMenuFamily.goToAddNewFamilyPage();

        driver.findElementByCssSelector("#FamilyName").sendKeys("Stastkovci");
        driver.findElement(By.cssSelector("[value='Save']")).click();

        SideMenu sideMenuPerson = new SideMenu(driver);
        sideMenuPerson.goToAddNewPersonPage();

        WebElement genderSelector = driver.findElementByName("Gender");
        Select selectGenderFromDropDown = new Select(genderSelector);
        selectGenderFromDropDown.selectByVisibleText("Female");

        driver.findElementByCssSelector("#Title").sendKeys("Mrs.");
        driver.findElementByCssSelector("#FirstName").sendKeys("Tamy");

        WebElement familySelectorElement = driver.findElementByName("Family");
        Select selectFamilyFromDropdown = new Select(familySelectorElement);
        selectFamilyFromDropdown.selectByValue("74");
        driver.findElement(By.cssSelector("#PersonSaveButton")).click();

        driver.findElement(By.cssSelector("#EditPerson")).click();
        driver.findElement(By.cssSelector("#MiddleName")).sendKeys("Matilda");
        driver.findElementByName("Email").sendKeys("testmail@test.cz");
        driver.findElement(By.cssSelector("#PersonSaveButton")).click();

        //Then
        driver.findElementByCssSelector(".fa").getText().contains("Matilda");
        driver.findElementByCssSelector(".fa").getText().contains("Updated by Church Admin");

        SideMenu sideMenuAllPersons = new SideMenu(driver);
        sideMenuAllPersons.goToViewAllPersonsPage();

        driver.findElementByCssSelector("div.dataTables_filter > label > input[type=search]").sendKeys("testmail@test.cz");
        driver.findElementByCssSelector("tbody > tr").getText().contains("testemail@test.cz");
    }
}
