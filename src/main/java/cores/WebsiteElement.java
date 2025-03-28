package cores;

import org.openqa.selenium.*;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

public class WebsiteElement {
    private WebElement element;

    public WebsiteElement(WebDriver driver, String locator) {
        List<By> list = Arrays.asList(By.xpath(locator), By.cssSelector(locator), By.className(locator), By.id(locator), By.name(locator));
        if (locator.startsWith("/")) {
            element = driver.findElement(list.get(0));
        } else {
            if (locator.startsWith("#") || locator.startsWith(".")) {
                element = driver.findElement(list.get(0));
            } else {
                for (int i = 1; i < list.size() - 1; i++) {
                    try {
                        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds((long) 1.5));
                        element = driver.findElement(list.get(i));
                        if (element != null) {
                            break;
                        }
                    } catch (NoSuchElementException e) {
                    }
                }
                if (element == null) {
                    throw new InvalidSelectorException("Invalid locator");
                }
            }
        }

    }


    public void click() {
        element.click();
    }

    public void setText(String value) {
        element.sendKeys(value);
    }
}
