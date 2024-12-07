package step_definitions;

import command_providers.ActOn;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import page_objects.NavigationBar;
import page_objects.RealApr;
import utilities_qatek.ReadConfigFiles;

public class MortgageSteps {
    private static final Logger LOGGER = LogManager.getLogger(MortgageSteps.class);
    WebDriver driver = Hooks.driver;

    @Given("^user is in mortgage calculator home page$")
    public void userIsInMortgageCalculatorHomePage() {
        ActOn.browser(driver).openBrowser(ReadConfigFiles.getPropertyValues("MortgageAppUrl"));
        LOGGER.info("Landed on the Mortgage Calculator Home Page");
    }

    @And("^user navigate to Real Apr page through Rates$")
    public void user_navigate_to_real_apr_page_through_rates() {
        new NavigationBar(driver)
                .mouseHoverToRates()
                .navigateToRealApr()
                .waitForPageLoad();
        LOGGER.info("Navigated to Real APR Page");
    }

    @When("^user click on calculate button upon entering the data of \"(.+?)\", \"(.+?)\", and \"(.+?)\"$")
    public void user_click_on_calculate_button_upon_entering_the_data(String homePrice, String downPayment, String interestRate) {
        new RealApr(driver)
                .typeHomePrice(homePrice)
                .clickDownPaymentInDollar()
                .typeDownPayment(downPayment)
                .typeInterestRate(interestRate)
                .clickCalculateRateButton();
    }

    @Then("^the real apr rate is \"(.+?)\"$")
    public void the_real_apr_rate_is(String expectedRealAprRate) {
        new RealApr(driver)
                .validateRealAprRate(expectedRealAprRate);
    }
}
