package step_definitions;

import command_providers.ActOn;
import command_providers.AssertThat;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities_qatek.ReadConfigFiles;

import java.util.List;
import java.util.Map;

public class LoginSteps {
    private static final By UserName = By.id("user-name");
    private static final By Password = By.id("password");
    private static final By LoginButton = By.id("login-button");
    private static final By ProductsText = By.xpath("//span[text()='Products']");
    private static final By LoginError = By.xpath("//h3[@data-test]");
    private static final Logger LOGGER = LogManager.getLogger(LoginSteps.class);
    WebDriver driver = Hooks.driver;

    //Precondition
    @Given("^a user is on the login page$")
    public void navigateToLoginPage() {
        ActOn.browser(driver).openBrowser(ReadConfigFiles.getPropertyValues("TestAppUrl"));
        LOGGER.info("user is on the login page");
    }

    @When("^user enters username \"(.+?)\" and password \"(.+?)\"$")
    public void enterUserCredentials(String username, String password) {
        ActOn.element(driver, UserName).setValue(username);
        ActOn.element(driver, Password).setValue(password);
        LOGGER.info("user has entered credentials");
    }

    @And("^click on login button$")
    public void clickOnLogin() {
        ActOn.element(driver, LoginButton).click();
        LOGGER.info("user clicked on Login button");
    }

    @Then("^user is navigated to home page$")
    public void validateUserIsLoggedInSuccessfully() throws Exception {
        AssertThat.elementAssertions(driver, ProductsText).elementIsDisplayed();
        LOGGER.info("User is on the Home Page");
    }

    @When("^user clicks on login button upon entering credentials$")
    public void userClicksOnLoginButtonUponEnteringCredentials(DataTable table) {
        List<Map<String, String>> data = table.asMaps(String.class, String.class);
        for (Map<String, String> cells: data) {
            ActOn.element(driver, UserName).setValue(cells.get("username"));
            ActOn.element(driver, Password).setValue(cells.get("password"));
            LOGGER.info("user has entered credentials");
        }
        ActOn.element(driver, LoginButton).click();
        LOGGER.info("user clicked on Login button");
    }

    @Then("user is failed to login")
    public void validateUserIsFailedToLogin() {
        String errorText = ActOn.element(driver, LoginError).getTextValue();
        Assert.assertEquals("Epic sadface: Username and password do not match any user in this service", errorText);
    }
}
