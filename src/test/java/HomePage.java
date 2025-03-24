import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.time.Duration;
import java.util.Set;

public class HomePage {
    @Test
    void testHomePage() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.get("https://hasaki.vn/");
        Thread.sleep(2000);

        //Verify popup message
        Assert.assertEquals(driver.findElement(By.className("slidedown-body-message")).getText(), "Click vào Đồng Ý để nhận thông tin khuyến mãi nhanh nhất từ Hasaki");
        //Handle popup
        driver.findElement(By.id("onesignal-slidedown-cancel-button")).click();
        //handle cookie
        driver.findElement(By.id("rejectCookies")).click();

        Assert.assertTrue(driver.findElement(By.className("menu_hamber")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.id("search")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.className("item_login")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.className("item_header_hethong")).isDisplayed());
        Assert.assertTrue(driver.findElement(By.className("item_header_giohang")).isDisplayed());

        new Actions(driver).moveToElement(driver.findElement(By.className("item_login"))).perform();
        driver.findElement(By.xpath("//a[text()='Đăng nhập' and @id='hskLoginButton']")).click();
        driver.findElement(By.cssSelector("#username")).sendKeys("0345864246");
        driver.findElement(By.cssSelector("#password")).sendKeys("#Onimusha00");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
    }
}
