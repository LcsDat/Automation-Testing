package cores;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class WebsiteElement {

    private WebElement element;

    public WebsiteElement(WebDriver driver, String locator) {
        List<By> list = Arrays.asList(
                By.xpath(locator),
                By.cssSelector(locator),
                By.className(locator.replace(" ", "")),
                By.id(locator),
                By.name(locator));

        if (locator.startsWith("/") || locator.startsWith("(")) {
            element = driver.findElement(list.get(0));
        } else {

            if (locator.startsWith("#") || locator.startsWith(".")) {
                element = driver.findElement(list.get(1));
            } else {
                for (int i = 1; i < list.size() - 1; i++) {
                    try {
                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
                        element = driver.findElement(list.get(i));
                        if (element != null) {
                            break;
                        }
                    } catch (NoSuchElementException e) {
                        System.out.println("No element found using " + list.get(i) + " " + locator);
                    }
                }

                driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

                if (element == null) {
                    throw new InvalidSelectorException("No strategy can be used with " + locator);
                }
            }
        }
    }

    public WebsiteElement(WebDriver driver, String locator, String... varargs) {
        element = driver.findElement(By.xpath(String.format(locator, varargs)));
    }

    public WebElement getElement() {
        return element;
    }

    public void click() {
        element.click();
    }

    public void setText(String value) {
        element.sendKeys(value);
    }

    public String getText() {
        return element.getText();
    }

    public String getDomAttribute(String attributeValue){
        return element.getDomAttribute(attributeValue);
    }

    public boolean isDisplayed() {
        return element.isDisplayed();
    }



    public String getCssValue(String value){
        return element.getCssValue(value);
    }
}
