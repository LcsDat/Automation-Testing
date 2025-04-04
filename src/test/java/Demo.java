import cores.Browser;
import cores.DriverFactory;
import cores.WebsiteDriver;
import cores.WebsiteElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.time.Duration;

public class Demo {

    @Test
    public void test() throws InterruptedException {
        WebsiteDriver driver = new WebsiteDriver(Browser.CHROME);
        System.out.println(WebsiteDriver.class.getName());
        System.out.println(driver.getClass().getName());
    }

}
