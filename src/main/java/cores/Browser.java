package cores;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public enum Browser {
    CHROME, FIREFOX, EDGE;

    public WebDriver initChromeDriver() {
        return new ChromeDriver();
    }

    public WebDriver initFirefoxDriver() {
        return new FirefoxDriver();
    }

    public WebDriver initEdgeDriver() {
        return new EdgeDriver();
    }
}
