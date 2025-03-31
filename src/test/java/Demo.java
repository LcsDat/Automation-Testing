import cores.Browser;
import cores.DriverFactory;
import cores.WebsiteDriver;
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
//        WebDriver driver= new ChromeDriver();
        WebsiteDriver driver1 = DriverFactory.initWebsiteDriver(Browser.CHROME);
//        driver.manage().window().maximize();
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


        driver1.navigate("https://hasaki.vn");

//        By by1 = By.xpath("//div[@class='title_cate_home']");
//        By by2 = By.xpath("//span[@class='icon_lam_category_top'][2]");
//
//        WebElement ele1 = driver.findElement(by1);
////        WebElement ele2 = driver.findElement(by2);
//
//        System.out.println("By1: " + by1);
//        System.out.println("By2: " + by2);
//
//        driver.findElements(by1).forEach(e -> System.out.println(e.getText()));
//        System.out.println("Ele2: " + ele2);

        driver1.findElements("//div[@class='title_cate_home']").forEach(e -> System.out.println(e.getText()));
    }

}
