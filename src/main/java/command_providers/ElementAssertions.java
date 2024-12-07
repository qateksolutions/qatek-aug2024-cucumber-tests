package command_providers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import utilities_qatek.ScreenCapture;

public class ElementAssertions {

    private static final Logger LOGGER = LogManager.getLogger(ElementAssertions.class);

    private WebDriver driver;
    private By locator;

    public ElementAssertions(WebDriver driver, By locator){
        this.driver = driver;
        this.locator = locator;
    }

    public void elementIsDisplayed() throws Exception{
        try{
            boolean displayed = driver.findElement(locator).isDisplayed();
            if(!displayed){
                ScreenCapture.getScreenshot(driver);
                throw new Exception("Element is not displayed for the locator: " + locator);
            }
        } catch(NoSuchElementException e){
            ScreenCapture.getScreenshot(driver);
            throw new Exception("Failed to find the element for the locator: " + locator);
        }
    }

}
