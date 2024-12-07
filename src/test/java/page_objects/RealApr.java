package page_objects;

import command_providers.ActOn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RealApr extends NavigationBar{

    private static final Logger LOGGER = LogManager.getLogger(RealApr.class);

    private final By RatesLink = By.linkText("Rates");
    private final By RealAprLink = By.linkText("Real APR");
    private final By CalculatorTab = By.xpath("//label[text()='Calculator']");
    private final By HomePriceInputField = By.name("HomeValue");
    private final By DownpaymentInputField = By.name("DownPayment");
    private final By DownpaymentInDollar = By.id("DownPaymentSel0");
    private final By InterestRateInputField = By.name("Interest");
    private final By CalculateRateButton = By.name("calculate");
    private final By ActualAprRate = By.xpath("//strong[text()='3.130%']");

    public RealApr(WebDriver driver) {
        super(driver);
    }

    public RealApr waitForPageLoad(){
        LOGGER.debug("Waiting for Real APR page to load");
        ActOn.wait(driver, CalculatorTab).waitForElementToBeVisible();
        return this;
    }

    public RealApr typeHomePrice(String value){
        LOGGER.debug("Typing Home value: " + value);
        ActOn.element(driver, HomePriceInputField).setValue(value);
        return this;
    }

    public RealApr clickDownPaymentInDollar(){
        LOGGER.debug("Click on the $ for Down Payment");
        ActOn.element(driver, DownpaymentInDollar).click();
        return this;
    }

    public RealApr typeDownPayment(String value){
        LOGGER.debug("Typing Down Payment value: " + value);
        ActOn.element(driver, DownpaymentInputField).setValue(value);
        return this;
    }

    public RealApr typeInterestRate(String value){
        LOGGER.debug("Typing Interest Rate value: " + value);
        ActOn.element(driver, InterestRateInputField).setValue(value);
        return this;
    }

    public RealApr clickCalculateRateButton(){
        LOGGER.debug("Click on the Calculate Rate button");
        ActOn.element(driver, CalculateRateButton).click();
        return this;
    }

    public RealApr validateRealAprRate(String expectedValue){
        LOGGER.debug("Validate Real Apr rate is: " + expectedValue);
        String actualRealAprRate = ActOn.element(driver, ActualAprRate).getTextValue();
        Assert.assertEquals(expectedValue, actualRealAprRate);
        return this;
    }


}
