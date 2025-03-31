package cores;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebsiteDriver {
    private WebDriver driver;
    private WebsiteActions actions;
    private ExplicitWait webDriverWait;
    private JavascriptExecutor jsExecutor;

    public WebsiteDriver(Browser browser) {
        this.driver = DriverFactory.initWebdriver(browser);
        this.actions = new WebsiteActions(driver, Duration.ofSeconds(10));
        this.webDriverWait = new ExplicitWait(driver, Duration.ofSeconds(10));
        this.jsExecutor = (JavascriptExecutor) driver;
    }

    public static WebsiteDriver init(Browser browser) {
        return new WebsiteDriver(browser);
    }

    public WebsiteElement findElement(String locator){
        return new WebsiteElement(driver, locator);
    }

    public WebsiteElement findElement(String locator, String... varargs){
        return new WebsiteElement(driver, locator, varargs);
    }

    public List<WebsiteElement> findElements(String locator){
        List<WebElement> oriEles;
        List<WebsiteElement> newEles = new ArrayList<>();

        if (locator.startsWith("/")) oriEles = driver.findElements(By.xpath(locator));
        else oriEles = driver.findElements(By.cssSelector(locator));

        for(WebElement element : oriEles) {
            newEles.add(new WebsiteElement(driver, locator));
        }

        return newEles;
    }

    public void waitForPageLoad(){
//        return jsExecutor.executeScript("return document.readyState").equals("complete");
        webDriverWait.getWait().until(d -> jsExecutor.executeScript("return document.readyState").equals("complete"));
    }

    public String getText(String locator) {
        return findElement(locator).getText();
    }

    public String getText(String locator, String...varargs) {
        return findElement(locator, varargs).getText();
    }

    public void moveToElement(String locator){
        actions.moveToElement(locator);
    }

    public void moveToElement(String locator, String... varargs){
        actions.moveToElement(locator, varargs);
    }

    public void click(String locator){
        findElement(locator).click();
    }

    public void click(String locator, String... varargs){
        findElement(locator, varargs).click();
    }

    public void setText(String locator, String value){
        findElement(locator).setText(value);
    }

    public Boolean waitToBeInvisibleBy(String locator) {
        return webDriverWait.waitToBeInvisibleBy(locator);
    }

    public WebsiteElement waitToBeClickable(String locator) {
        return webDriverWait.waitToBeClickable(locator);
    }

    public WebsiteElement waitToBeVisible(String locator) {
        return webDriverWait.waitToBeVisible(locator);
    }

//    public WebElement waitToBeVisibleByXpath(String locator) {
//        return webDriverWait.until(ExpectedConditions.visibilityOf(findByXpath(locator)));
//    }
//
//    public WebElement waitToBeVisibleByCss(String locator) {
//        return webDriverWait.until(ExpectedConditions.visibilityOf(findByCss(locator)));
//    }
//
//    public WebElement waitToBeVisibleByClass(String locator) {
//        return webDriverWait.until(ExpectedConditions.visibilityOf(findByClass(locator)));
//    }
//
//    public WebElement waitToBeVisibleByID(String locator) {
//        return webDriverWait.until(ExpectedConditions.visibilityOf(findByID(locator)));
//    }
//
//    public WebElement waitToBeVisibleByName(String locator) {
//        return webDriverWait.until(ExpectedConditions.visibilityOf(findByName(locator)));
//    }
//
//    public Boolean waitToBeInvisibleByXpath(String locator) {
//        return webDriverWait.until(ExpectedConditions.invisibilityOf(findByXpath(locator)));
//    }
//
//    public Boolean waitToBeInvisibleByCss(String locator) {
//        return webDriverWait.until(ExpectedConditions.invisibilityOf(findByCss(locator)));
//    }
//
//    public Boolean waitToBeInvisibleByClass(String locator) {
//        return webDriverWait.until(ExpectedConditions.invisibilityOf(findByClass(locator)));
//    }
//
//    public Boolean waitToBeInvisibleByID(String locator) {
//        return webDriverWait.until(ExpectedConditions.invisibilityOf(findByID(locator)));
//    }
//
//    public Boolean waitToBeInvisibleByName(String locator) {
//        return webDriverWait.until(ExpectedConditions.invisibilityOf(findByName(locator)));
//    }
//
//    public WebElement waitToBeClickableByXpath(String locator) {
//        return webDriverWait.until(ExpectedConditions.elementToBeClickable(findByXpath(locator)));
//    }
//
//    public WebElement waitToBeClickableByCss(String locator) {
//        return webDriverWait.until(ExpectedConditions.elementToBeClickable(findByCss(locator)));
//    }
//
//    public WebElement waitToBeClickableByClass(String locator) {
//        return webDriverWait.until(ExpectedConditions.elementToBeClickable(findByClass(locator)));
//    }
//
//    public WebElement waitToBeClickableByID(String locator) {
//        return webDriverWait.until(ExpectedConditions.elementToBeClickable(findByID(locator)));
//    }
//
//    public WebElement waitToBeClickableByName(String locator) {
//        return webDriverWait.until(ExpectedConditions.elementToBeClickable(findByName(locator)));
//    }

    public void navigate(String applicationURL) {
        driver.get(applicationURL);
    }

    public void quit() {
        driver.quit();
    }

    public void clickByActions(String locator) {
        actions.click(locator);
    }

    public void clickByActions(String locator, String varargs) {
        actions.click(locator, varargs);
    }

//    public void clickByCss(String locator) {
//        actions.click(findByCss(locator)).perform();
//    }
//
//    public void clickByID(String locator) {
//        actions.click(findByID(locator)).perform();
//    }
//
//    public void clickByClass(String locator) {
//        actions.click(findByClass(locator)).perform();
//    }
//
//    public void clickByName(String locator) {
//        actions.click(findByName(locator)).perform();
//    }

//    public void moveToElementByXpath(String locator) {
//        actions.moveToElement(findByXpath(locator)).perform();
//    }
//
//    public void moveToElementByXpath(String locator, String... varargs) {
//        actions.moveToElement(findByXpath(locator, varargs)).perform();
//    }
//
//    public void moveToElementByCss(String locator) {
//        actions.moveToElement(findByCss(locator)).perform();
//    }
//
//    public void moveToElementByClass(String locator) {
//        actions.moveToElement(findByClass(locator)).perform();
//    }
//
//    public void moveToElementByID(String locator) {
//        actions.moveToElement(findByID(locator)).perform();
//    }
//
//    public void moveToElementByName(String locator) {
//        actions.moveToElement(findByName(locator)).perform();
//    }

//    public WebElement findByXpath(String locator) {
//        return driver.findElement(By.xpath(locator));
//    }
//
//    public WebElement findByXpath(String locator, String... varargs) {
//        return driver.findElement(By.xpath(String.format(locator, varargs)));
//    }
//
//    public WebElement findByCss(String locator) {
//        return driver.findElement(By.cssSelector(locator));
//    }
//
//    public WebElement findByID(String locator) {
//        return driver.findElement(By.id(locator));
//    }
//
//    public WebElement findByClass(String locator) {
//        return driver.findElement(By.className(locator));
//    }
//
//    public WebElement findByName(String locator) {
//        return driver.findElement(By.name(locator));
//    }
//
//    public List<WebElement> findAllByXpath(String locator) {
//        return driver.findElements(By.xpath(locator));
//    }
//
//    public List<WebElement> findAllByCss(String locator) {
//        return driver.findElements(By.cssSelector(locator));
//    }
//
//    public List<WebElement> findAllByID(String locator) {
//        return driver.findElements(By.id(locator));
//    }
//
//    public List<WebElement> findAllByName(String locator) {
//        return driver.findElements(By.name(locator));
//    }
//
//    public List<WebElement> findAllByClass(String locator) {
//        return driver.findElements(By.className(locator));
//    }
}
