package steps;

import co.boorse.seleniumtable.SeleniumTable;
import cucumber.api.java.Before;
import cucumber.api.java.bs.A;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import pages.SmartBearLoginPage;
import pages.SmartBearWebOrdersPage;
import utilities.Driver;
import utilities.TableHandler;

public class SmartBearSteps {


    WebDriver driver;
    WebElement table;
    SeleniumTable seleniumTable;
    SmartBearLoginPage smartBearLoginPage;
    SmartBearWebOrdersPage smartBearWebOrdersPage;
    Select select;

    @Before
    public void setup() {
        driver = Driver.getDriver();
        smartBearLoginPage = new SmartBearLoginPage();
        smartBearWebOrdersPage = new SmartBearWebOrdersPage();
    }

    @Given("user is on {string}")
    public void userIsOn(String url) {
        driver.get(url);

    }

    @When("user enters username as {string}")
    public void userEntersUsernameAs(String username) {
        smartBearLoginPage.userNameInput.clear();
        smartBearLoginPage.userNameInput.sendKeys(username);
    }

    @And("user enters password as {string}")
    public void userEntersPasswordAs(String password) {
        smartBearLoginPage.passwordInput.clear();
        smartBearLoginPage.passwordInput.sendKeys(password);
    }

    @And("user clicks on Login button")
    public void userClicksOnLoginButton() {
        smartBearLoginPage.loginbutton.click();
    }

    @Then("user should see {string} message")
    public void userShouldSeeMessage(String errorMessage) {
        Assert.assertEquals(errorMessage, smartBearLoginPage.loginFailureMessage.getText());
    }

    @Then("user should be routed to {string}")
    public void userShouldBeRoutedTo(String url) {
        Assert.assertEquals(url, driver.getCurrentUrl());
    }

    @And("validate below menu items are displayed")
    public void validateBelowMenuItemsAreDisplayed(DataTable data) {

        for (int i = 0; i < data.asList().size(); i++) {

            Assert.assertEquals(data.asList().get(i), smartBearWebOrdersPage.menuItemsList.get(i).getText());

        }


    }


    @When("user clicks on {string} button")
    public void userClicksOnButton(String btn) {
        smartBearWebOrdersPage.clickByVisibleTextBtn(btn);
    }


    @Then("all rows should be checked")
    public void allRowsShouldBeChecked() {
        for (int i = 1; i < TableHandler.getRows(smartBearWebOrdersPage.ordersTable).size(); i++) {
            Assert.assertTrue(TableHandler.getCheckBox(smartBearWebOrdersPage.ordersTable, i, 0).isSelected());
        }


    }

    @Then("all rows should be unchecked")
    public void allRowsShouldBeUnchecked() {
        for (int i = 1; i < TableHandler.getRows(smartBearWebOrdersPage.ordersTable).size(); i++) {
            Assert.assertFalse(TableHandler.getCheckBox(smartBearWebOrdersPage.ordersTable, i, 0).isSelected());

        }
    }


    @When("user clicks on {string} menu item")
    public void userClicksOnMenuItem(String menuOption) {
        smartBearWebOrdersPage.selectMenuItemByText(menuOption);

    }

    @And("user selects {string} as product")
    public void userSelectsAsProduct(String option) {
        select = new Select(smartBearWebOrdersPage.productOptions);
        select.selectByVisibleText(option);
    }

    @And("user enters {int} as quantity")
    public void userEntersAsQuantity(int quantity) {
        smartBearWebOrdersPage.productQuantity.sendKeys(String.valueOf(quantity));
    }


    @And("user enters all address information")
    public void userEntersAllAddressInformation(DataTable data) {
        for (int i = 0; i < data.asList().size(); i++) {
            smartBearWebOrdersPage.orderAddressInfoInputList.get(i).sendKeys(data.asList().get(i));

        }
    }

    @And("user enters all payment information")
    public void userEntersAllPaymentInformation(DataTable data) {
        smartBearWebOrdersPage.selectCardType(data.asList().get(0));
        smartBearWebOrdersPage.orderPmntCardNO.sendKeys(data.asList().get(1));
        smartBearWebOrdersPage.orderPmntCardExp.sendKeys(data.asList().get(2));

    }

    @Then("user should see their order displayed in the {string} table")
    public void userShouldSeeTheirOrderDisplayedInTheTable(String tableName) {
        Assert.assertEquals(tableName, smartBearWebOrdersPage.subHeading.getText());


    }


    @And("validate all information entered displayed correct with the order")
    public void validateAllInformationEnteredDisplayedCorrectWithTheOrder(DataTable data) {

        for (int i = 1; i < TableHandler.getCells(smartBearWebOrdersPage.ordersTable, 1).size() - 1; i++) {
            Assert.assertEquals(data.asList().get(i), TableHandler.getCell(smartBearWebOrdersPage.ordersTable, 1, i).getText());
        }
    }

    @Then("validate all orders are deleted from the {string}")
    public void validateAllOrdersAreDeletedFromThe(String tableName) {
        Assert.assertEquals(tableName, smartBearWebOrdersPage.subHeading.getText());

    }

    @And("validate user sees {string} message")
    public void validateUserSeesMessage(String msg) {
        Assert.assertEquals(msg, smartBearWebOrdersPage.msgEmptyOrder.getText());
    }
}

