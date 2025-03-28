package cores;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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

    public WebsiteElement findElement(String locator){
        return new WebsiteElement(driver, locator);
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

    public Boolean waitToBeInvisibleByXpath(String locator) {
        return webDriverWait.until(ExpectedConditions.invisibilityOf(findByXpath(locator)));
    }

    public Boolean waitToBeInvisibleByCss(String locator) {
        return webDriverWait.until(ExpectedConditions.invisibilityOf(findByCss(locator)));
    }

    public Boolean waitToBeInvisibleByClass(String locator) {
        return webDriverWait.until(ExpectedConditions.invisibilityOf(findByClass(locator)));
    }

    public Boolean waitToBeInvisibleByID(String locator) {
        return webDriverWait.until(ExpectedConditions.invisibilityOf(findByID(locator)));
    }

    public Boolean waitToBeInvisibleByName(String locator) {
        return webDriverWait.until(ExpectedConditions.invisibilityOf(findByName(locator)));
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

    public void quit() {
        driver.quit();
    }

    public void clickByXpath(String locator) {
        actions.click(findByXpath(locator)).perform();
    }

    public void clickByXpath(String locator, String varargs) {
        actions.click(findByXpath(locator, varargs)).perform();
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

    public void moveToElementByXpath(String locator, String... varargs) {
        actions.moveToElement(findByXpath(locator, varargs)).perform();
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

    public WebElement findByXpath(String locator, String... varargs) {
        return driver.findElement(By.xpath(String.format(locator, varargs)));
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

    public List<WebElement> findAllByXpath(String locator) {
        return driver.findElements(By.xpath(locator));
    }

    public List<WebElement> findAllByCss(String locator) {
        return driver.findElements(By.cssSelector(locator));
    }

    public List<WebElement> findAllByID(String locator) {
        return driver.findElements(By.id(locator));
    }

    public List<WebElement> findAllByName(String locator) {
        return driver.findElements(By.name(locator));
    }

    public List<WebElement> findAllByClass(String locator) {
        return driver.findElements(By.className(locator));
    }
}
