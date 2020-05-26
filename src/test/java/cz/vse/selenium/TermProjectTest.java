package cz.vse.selenium;

import cz.churchcrm.testframework.components.Grid;
import cz.churchcrm.testframework.components.GridRow;
import cz.churchcrm.testframework.components.SideMenu;
import cz.churchcrm.testframework.pages.DepositListingPage;
import cz.churchcrm.testframework.pages.DepositPage;
import cz.churchcrm.testframework.pages.LoginPage;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.ParseException;
import java.util.UUID;

import static cz.churchcrm.testframework.utils.TestUtils.changeDateFormat;

public class TermProjectTest extends BaseTest {

    /**
     * Basic flow, which tests invalid credentials.
     */
    @Test
    public void shouldNotLoginUsingInvalidCredentials_InvalidUsername() {
        // given
        //driver.get("http://digitalnizena.cz/church/");
        //Duplicity with login driver.get

        // when
        LoginPage loginpageInvalidUsername = new LoginPage(driver);
        loginpageInvalidUsername.login("Milan", "church12345");

        // then
        WebElement errorMsg = driver.findElement(By.className("alert-error"));
        String loginUrl = driver.getCurrentUrl();

        Assert.assertTrue("Warning message not found!", errorMsg.getText().contains("Invalid"));
        Assert.assertTrue("User is not on the login page.", loginUrl.contains("Login.php"));
    }

    /**
     * Basic flow, which tests invalid credentials.
     */
    @Test
    public void shouldNotLoginUsingInvalidCredentials_InvalidPassword() {
        // given
        //driver.get("http://digitalnizena.cz/church/");

        // when
        LoginPage loginpageInvalidPassword = new LoginPage(driver);
        loginpageInvalidPassword.login("church", "12345");

        // then
        WebElement errorMsg = driver.findElement(By.className("alert-error"));
        String loginUrl = driver.getCurrentUrl();

        Assert.assertTrue("Warning message not found!", errorMsg.getText().contains("Invalid"));
        Assert.assertTrue("User is not on the login page.", loginUrl.contains("Login.php"));
    }

    /**
     * Basic flow, which tests invalid credentials.
     */
    @Test
    public void shouldNotLoginUsingInvalidCredentials_InvalidUsernameAndPassword() {
        // given
        //driver.get("http://digitalnizena.cz/church/");

        // when
        LoginPage loginpageInvalidUsernameAndPassword = new LoginPage(driver);
        loginpageInvalidUsernameAndPassword.login("Peter221", "1234554321");

        // then
        WebElement errorMsg = driver.findElement(By.className("alert-error"));
        String loginUrl = driver.getCurrentUrl();

        Assert.assertTrue("Warning message not found!", errorMsg.getText().contains("Invalid"));
        Assert.assertTrue("User is not on the login page.", loginUrl.contains("Login.php"));
    }

    /**
     * Test that adds and verifies deposit.
     * TODO: Todo.
     */
    @Test
    public void addDepositAndVerify() throws ParseException {
        LoginPage loginpage = new LoginPage(driver);
        loginpage.login();

        DepositPage depositPage = new DepositPage(driver);
        DepositListingPage dlPage = depositPage.gotoAllDeposits(driver);

        String depositComment = UUID.randomUUID().toString() + "-ID";
        //String depositComment = "2eb5c1ed-2c1e-497b-bbe1-079cc3afd1cdID";
        String depositDate = "2020-05-20";

        dlPage.addDeposit(depositComment, depositDate);


        Grid depositsGrid = new Grid(driver);
        depositsGrid.search(depositComment);

        GridRow row = depositsGrid.getRow(0);

        Assert.assertTrue(row.shouldContain(depositComment) && row.shouldContain(changeDateFormat(depositDate)));

        // Check edit page
        // getDepositComment
        // depositsGrid.getRows(column...) / rows.get(0).getDepositComment().equals(depositComment)
    }

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
        driver.findElement(By.cssSelector("#PersonSaveButton")).click();

        //Then
        driver.findElementByCssSelector(".fa").getText().contains("Matilda");
        driver.findElementByCssSelector(".fa").getText().contains("Updated by Church Admin");
        //
    }

    /**
     * Menu showcase...
     * TODO: Remove
     */
    @Test
    public void dummyTest() throws InterruptedException {
        LoginPage loginpage = new LoginPage(driver);
        loginpage.login();

        SideMenu sideMenu = new SideMenu(driver);

        sideMenu.goToAddNewPersonPage();
        //sideMenu.goToAllDeposits();
    }
}
