import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;

public class HomePage {
    WebsiteDriver webDriver;

    @BeforeTest
    void setup() {
        webDriver = WebsiteDriver.init(Browser.CHROME);

        webDriver.navigate("https://hasaki.vn/");

        //Handle popup and cookies
        webDriver.findByID("onesignal-slidedown-cancel-button").click();
        webDriver.findByID("rejectCookies").click();

        //Login
        webDriver.moveToElementByCss("div.item_header.item_login ");
        webDriver.findByXpath("//a[text()='Đăng nhập' and @id='hskLoginButton']").click();
        webDriver.findByCss("#username").sendKeys("0345864246");
        webDriver.findByCss("#password").sendKeys("#Onimusha00");
        webDriver.findByXpath("//button[text()='Đăng nhập']").click();
    }

    @AfterTest
    void teardown() {
        if (!webDriver.findByCss("nav[aria-label='Main'] button.p-0 a span:nth-child(3)").getText().equals("0")) {
            webDriver.findByCss("nav[aria-label='Main'] button.p-0").click();

            while (true) {
                Integer size = webDriver.findAllByXpath("//tbody/tr").size();
                System.out.println(size);
                if (size != 0) {
                    webDriver.waitToBeClickableByXpath("//tbody/tr//button[text()='Xóa']").click();
                    webDriver.waitToBeInvisibleByCss("div.animate-spin");
                } else break;
            }
        }
        webDriver.moveToElementByXpath("//nav[@aria-label='Main']//li[1]");
        webDriver.findByXpath("//span[text()='Thoát']").click();
        webDriver.quit();
    }

    @Test
    void test() throws InterruptedException {

        //Choose product
        webDriver.moveToElementByID("hamber_menu");
        webDriver.moveToElementByXpath("//a[@class='parent_menu' and contains(text(),'Chăm Sóc Da Mặt')]");
        webDriver.clickByXpath("//div[@class='col_hover_submenu ']//a[text()='Tẩy Trang Mặt']");
        webDriver.findByXpath("//h1[contains(text(),'Tẩy Trang Mặt')]" +
                "/parent::div//following-sibling::div[@class='ProductGrid__grid width_common']" +
                "//div[text()='Combo 2 Nước Tẩy Trang Bí Đao Cocoon Làm Sạch & Giảm Dầu 500ml']").click();

        webDriver.findByCss("button[aria-label='Increase btn']").click();
        Assert.assertEquals(webDriver.findByCss("input[name='qty']").getDomAttribute("value"), "2");

        webDriver.waitToBeClickableByXpath("//div[text()='Giỏ hàng']").click();
        Assert.assertTrue(webDriver.findByXpath("//div[text()='Sản phẩm chỉ được mua tối đa là 1']").isDisplayed());

        webDriver.findByCss("button[aria-label='Descrease btn']").click();
        webDriver.waitToBeClickableByXpath("//div[text()='Giỏ hàng']").click();
        Assert.assertTrue(webDriver.findByXpath("//div[text()='Sản Phẩm đã được thêm vào giỏ hàng thành công']").isDisplayed());

        Thread.sleep(2000);

        String productQuantity = webDriver.findByXpath("//span[text()='Cart Icon']/following-sibling::span").getText();
        String productName = webDriver.findByXpath("//h1").getText();
        String productPrice = webDriver.findByCss("span.text-orange.text-lg.font-bold").getText().replaceAll("[^0-9]", "");

        Assert.assertEquals(productQuantity, "1");

        webDriver.findByXpath("//span[text()='Cart Icon']//ancestor::button").click();
        Assert.assertEquals(webDriver.findByXpath("//a[text()='Combo 2 Nước Tẩy Trang Bí Đao Cocoon Làm Sạch & Giảm Dầu 500ml']").getText(), productName);

        Integer calculatedPrice = (Integer.parseInt(productQuantity) * Integer.parseInt(productPrice));
        Thread.sleep(2000);
        String totalPriceeAt = webDriver.findByXpath("//tbody//tr[1]/td[4]/div").getText().replaceAll("[^0-9]", "");
        Integer totalPrice = Integer.parseInt(totalPriceeAt);

        Assert.assertEquals(calculatedPrice, totalPrice);
    }


}
