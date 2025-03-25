import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class WebsiteDriver {
    private WebDriver driver;
    private Actions actions;

    public WebsiteDriver(Browser browser) {
        this.driver = DriverFactory.init(browser);
        this.actions = new Actions(driver, Duration.ofSeconds(10));
    }

    public void navigate(String applicationURL){
        driver.get(applicationURL);
    }

    public void moveToElement(String locator){
        actions.moveToElement(driver.findElement(By.xpath(locator))).perform();
    }

    public WebElement findElement(String locator){
        return driver.findElement(By.xpath(locator));
    }
}
