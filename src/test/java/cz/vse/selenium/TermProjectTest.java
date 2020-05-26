package cz.vse.selenium;

import cz.churchcrm.testframework.components.Grid;
import cz.churchcrm.testframework.components.GridRow;
import cz.churchcrm.testframework.components.SideMenu;
import cz.churchcrm.testframework.pages.DepositListingPage;
import cz.churchcrm.testframework.pages.DepositPage;
import cz.churchcrm.testframework.pages.EditDepositPage;
import cz.churchcrm.testframework.pages.LoginPage;
import cz.churchcrm.testframework.utils.TestUtils;
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
     * Test that adds and verifies deposit.
     * TODO: Todo.
     */
    @Test
    public void addDepositAndVerify() throws ParseException, InterruptedException {
        LoginPage loginpage = new LoginPage(driver);
        DepositPage depositPage = new DepositPage(driver);
        DepositListingPage dlPage = new DepositListingPage(driver);
        EditDepositPage editDepositPage = new EditDepositPage(driver);
        Grid depositsGrid = new Grid(driver);
        SideMenu sideMenu = new SideMenu(driver);
        ConfirmDialog confirmDialog = new ConfirmDialog(driver);

        String depositComment = UUID.randomUUID().toString() + "-ID";
        String depositDate = "2020-05-20";

        loginpage.login();

        sideMenu.goToAllDeposits();

        dlPage.addDeposit(depositComment, depositDate);

        depositsGrid
                .selectPagination("100")
                .searchQuery(depositComment)
                .fillGridRowsList();

        // Verify found row
        GridRow row = depositsGrid.getRow(0);
        Assert.assertTrue(row.shouldContain(depositComment) && row.shouldContain(changeDateFormat(depositDate)));

        // Verify edit page
        depositsGrid.editTheFirstRecord();
        Assert.assertTrue(editDepositPage.verifyDepositSlip(row.getId(), depositComment, depositDate));

        // Go back
        TestUtils.goBack(driver);

        // Delete row
        depositsGrid
                .searchQuery(depositComment)
                .selectFirstRow()
                .deleteSelectedRows();
        confirmDialog.confirmAction();

        // Verify no record is found
        Assert.assertTrue(depositsGrid.searchQuery(depositComment).shouldBeEmpty());
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
