import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebsiteDriver {
    private WebDriver driver;
    private Actions actions;
    private WebDriverWait webDriverWait;

    public WebsiteDriver(Browser browser) {
        this.driver = DriverFactory.init(browser);
        this.actions = new Actions(driver, Duration.ofSeconds(10));
        this.webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public static WebsiteDriver init(Browser browser) {
        return new WebsiteDriver(browser);
    }

    public WebElement waitToBeVisibleByXpath(String locator) {
        return webDriverWait.until(ExpectedConditions.visibilityOf(findByXpath(locator)));
    }

    public WebElement waitToBeVisibleByCss(String locator) {
        return webDriverWait.until(ExpectedConditions.visibilityOf(findByCss(locator)));
    }

    public WebElement waitToBeVisibleByClass(String locator) {
        return webDriverWait.until(ExpectedConditions.visibilityOf(findByClass(locator)));
    }

    public WebElement waitToBeVisibleByID(String locator) {
        return webDriverWait.until(ExpectedConditions.visibilityOf(findByID(locator)));
    }

    public WebElement waitToBeVisibleByName(String locator) {
        return webDriverWait.until(ExpectedConditions.visibilityOf(findByName(locator)));
    }

    public WebElement waitToBeClickableByXpath(String locator) {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(findByXpath(locator)));
    }

    public WebElement waitToBeClickableByCss(String locator) {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(findByCss(locator)));
    }

    public WebElement waitToBeClickableByClass(String locator) {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(findByClass(locator)));
    }

    public WebElement waitToBeClickableByID(String locator) {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(findByID(locator)));
    }

    public WebElement waitToBeClickableByName(String locator) {
        return webDriverWait.until(ExpectedConditions.elementToBeClickable(findByName(locator)));
    }

    public void navigate(String applicationURL) {
        driver.get(applicationURL);
    }

    public void clickByXpath(String locator) {
        actions.click(findByXpath(locator)).perform();
    }

    public void clickByCss(String locator) {
        actions.click(findByCss(locator)).perform();
    }

    public void clickByID(String locator) {
        actions.click(findByID(locator)).perform();
    }

    public void clickByClass(String locator) {
        actions.click(findByClass(locator)).perform();
    }

    public void clickByName(String locator) {
        actions.click(findByName(locator)).perform();
    }

    public void moveToElementByXpath(String locator) {
        actions.moveToElement(findByXpath(locator)).perform();
    }

    public void moveToElementByCss(String locator) {
        actions.moveToElement(findByCss(locator)).perform();
    }

    public void moveToElementByClass(String locator) {
        actions.moveToElement(findByClass(locator)).perform();
    }

    public void moveToElementByID(String locator) {
        actions.moveToElement(findByID(locator)).perform();
    }

    public void moveToElementByName(String locator) {
        actions.moveToElement(findByName(locator)).perform();
    }

    public WebElement findByXpath(String locator) {
        return driver.findElement(By.xpath(locator));
    }

    public WebElement findByCss(String locator) {
        return driver.findElement(By.cssSelector(locator));
    }

    public WebElement findByID(String locator) {
        return driver.findElement(By.id(locator));
    }

    public WebElement findByClass(String locator) {
        return driver.findElement(By.className(locator));
    }

    public WebElement findByName(String locator) {
        return driver.findElement(By.name(locator));
    }
}
