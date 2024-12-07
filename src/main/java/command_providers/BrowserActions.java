package command_providers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BrowserActions {

    private static final Logger LOGGER = LogManager.getLogger(BrowserActions.class);

    private WebDriver driver;

    public BrowserActions(WebDriver driver){
        this.driver = driver;
    }

    public BrowserActions openBrowser(String url){
        driver.manage().deleteAllCookies();
        driver.get(url);
        driver.manage().window().maximize();
        return this;
    }

    public BrowserActions closeBrowser(){
        driver.quit();
        return this;
    }

    public String captureTitle(){
        return driver.getTitle();
    }


}
