import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class HomePage {
    Actions actions;
    WebDriverWait webDriverWait;

    @Test
    void testHomePage() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        actions = new Actions(driver, Duration.ofSeconds(10));
        webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(10));

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

        //login
        actions.moveToElement(driver.findElement(By.className("item_login"))).perform();
        driver.findElement(By.xpath("//a[text()='Đăng nhập' and @id='hskLoginButton']")).click();
        driver.findElement(By.cssSelector("#username")).sendKeys("0345864246");
        driver.findElement(By.cssSelector("#password")).sendKeys("#Onimusha00");
        driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();


        actions.moveToElement(driver.findElement(By.id("hamber_menu"))).perform();
        actions.moveToElement(driver.findElement(By.xpath("//a[@class='parent_menu' and contains(text(),'Chăm Sóc Da Mặt')]"))).perform();
        actions.click(driver.findElement(By.xpath("//div[@class='col_hover_submenu ']//a[text()='Tẩy Trang Mặt']")))
                .perform();

        driver.findElement(By.xpath("//h1[contains(text(),'Tẩy Trang Mặt')]/parent::div//following-sibling::div[@class='ProductGrid__grid width_common']//div[text()='Combo 2 Nước Tẩy Trang Bí Đao Cocoon Làm Sạch & Giảm Dầu 500ml']")).click();
        driver.findElement(By.cssSelector("button[aria-label='Increase btn']")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector("input[name='qty']")).getDomAttribute("value"),"2");

        webDriverWait.until(ExpectedConditions.elementToBeClickable( driver.findElement(By.xpath("//div[text()='Giỏ hàng']")))).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sản phẩm chỉ được mua tối đa là 1']")).isDisplayed());

        driver.findElement(By.cssSelector("button[aria-label='Descrease btn']")).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable( driver.findElement(By.xpath("//div[text()='Giỏ hàng']")))).click();

        Assert.assertTrue(driver.findElement(By.xpath("//div[text()='Sản Phẩm đã được thêm vào giỏ hàng thành công']")).isDisplayed());
        Thread.sleep(2000);

        String productQuantity = driver.findElement(By.xpath("//span[text()='Cart Icon']/following-sibling::span")).getText();
        String productName = driver.findElement(By.xpath("//h1")).getText();
        String productPrice = driver.findElement(By.cssSelector("span.text-orange.text-lg.font-bold")).getText().replaceAll("[^0-9]","");

        Assert.assertEquals(productQuantity,"1");

        driver.findElement(By.xpath("//span[text()='Cart Icon']//ancestor::button")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Combo 2 Nước Tẩy Trang Bí Đao Cocoon Làm Sạch & Giảm Dầu 500ml']")).getText(), productName);

        Integer calculatedPrice = (Integer.parseInt(productQuantity)*Integer.parseInt(productPrice));

        Thread.sleep(2000);
        String totalPriceeAt = driver.findElement(By.xpath("//tbody//tr[1]/td[4]/div")).getText().replaceAll("[^0-9]","");

        Integer totalPrice = Integer.parseInt(totalPriceeAt);

        Assert.assertEquals(calculatedPrice, totalPrice);
    }
}
