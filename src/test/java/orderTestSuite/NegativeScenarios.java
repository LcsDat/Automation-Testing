package orderTestSuite;

import core.BaseTest;
import cores.Browser;
import cores.DriverFactory;
import cores.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class NegativeScenarios extends BaseTest {


    @BeforeTest
    void beforeClass(){
        webDriver = DriverFactory.initWebsiteDriver(Browser.CHROME);
        homepage = PageFactory.generateHomepage(webDriver);
        productPage = PageFactory.generateProductpage(webDriver);
        cartPage = PageFactory.generateCartpage(webDriver);
        webDriver.navigate("https://hasaki.vn/");

        homepage.cancelPopup();
        homepage.cancelCookie();
    }

    @AfterMethod
    void afterMethod() {
        if (webDriver.getPageTitle().startsWith("Hasaki.vn")) webDriver.click("div.logo_site");
        else webDriver.click("a[aria-label='Homepage']");
    }

    @AfterClass
    void afterClass(){
        homepage.checkCartQuantity();
        if (webDriver.isUnDisplayed("#btn-login")){
            webDriver.moveToElement("//nav[@aria-label='Main']//li[1]");
            webDriver.findElement("//span[text()='Thoát']").click();
        }
        webDriver.quit();
    }

    @AfterTest
    void afterTest() {
        webDriver.killDriverProcess();
    }

    @Test()
    void tc01() {
        homepage.chooseProductFromSearchDropdown("Cerave");
        cartPage.addProductToCart();

        Assert.assertTrue(webDriver.waitToBeVisible("div[role='dialog']").isDisplayed());
        Assert.assertEquals(
                webDriver.findElement("input[name='username']").getDomAttribute("placeholder"),
                "Nhập email hoặc số điện thoại");
        Assert.assertEquals(
                webDriver.findElement("input[name='password']").getDomAttribute("placeholder"),
                "Nhập password");

        cartPage.closeLoginDialog();
        cartPage.shipExpress2h();

        Assert.assertTrue(webDriver.findElement("div[role='dialog']").isDisplayed());
        Assert.assertEquals(
                webDriver.findElement("input[name='username']").getDomAttribute("placeholder"),
                "Nhập email hoặc số điện thoại");
        Assert.assertEquals(
                webDriver.findElement("input[name='password']").getDomAttribute("placeholder"),
                "Nhập password");

        cartPage.closeLoginDialog();
    }

    @Test()
    void tc02() {
        homepage.chooseProductType("Chăm Sóc Da Mặt", "Tẩy Trang Mặt");
        productPage.chooseProduct("Combo 2 Nước Tẩy Trang Bí Đao Cocoon Làm Sạch & Giảm Dầu 500ml");
        cartPage.addProductToCart();

        Assert.assertTrue(webDriver.waitToBeVisible("div[role='dialog']").isDisplayed());
        Assert.assertEquals(
                webDriver.findElement("input[name='username']").getDomAttribute("placeholder"),
                "Nhập email hoặc số điện thoại");
        Assert.assertEquals(
                webDriver.findElement("input[name='password']").getDomAttribute("placeholder"),
                "Nhập password");

        cartPage.closeLoginDialog();
        cartPage.shipExpress2h();

        Assert.assertTrue(webDriver.findElement("div[role='dialog']").isDisplayed());
        Assert.assertEquals(
                webDriver.findElement("input[name='username']").getDomAttribute("placeholder"),
                "Nhập email hoặc số điện thoại");
        Assert.assertEquals(
                webDriver.findElement("input[name='password']").getDomAttribute("placeholder"),
                "Nhập password");

        cartPage.closeLoginDialog();
    }


}
