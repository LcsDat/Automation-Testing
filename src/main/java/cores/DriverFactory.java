package cores;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverFactory {

    public static WebDriver initWebdriver(Browser browser){
        WebDriver driver = null;

        switch (browser){
            case FIREFOX -> driver = browser.initFirefoxDriver();

            case CHROME -> driver = browser.initChromeDriver();
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    public static WebsiteDriver initWebsiteDriver(Browser browser){
        return new WebsiteDriver(browser);
    }
}
