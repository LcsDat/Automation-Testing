package orderTestSuite;

import core.BaseTest;
import cores.Browser;
import cores.DriverFactory;
import cores.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class NegativeScenarios extends BaseTest {


//    @BeforeTest
//    void beforeTest() {
//
//    }

    @Parameters({"chrome", "url"})
    @BeforeClass
    void beforeClass(Browser browser, String url) {
        webDriver = DriverFactory.initWebsiteDriver(browser);
        homepage = PageFactory.generateHomePage(webDriver);
        productPage = PageFactory.generateProductsPage(webDriver);
        productDetailsPage = PageFactory.generateProductDetailsPage(webDriver);
        cartPage = PageFactory.generateCartPage(webDriver);

        webDriver.navigate(url);

        homepage.cancelPopup();
        homepage.cancelCookie();
        homepage.removeProductFromCart();
    }

    @AfterMethod
    void afterMethod() {
        navigateToHomePage();
    }

    @AfterClass
    void afterClass() {
        logout();
        quitBrowser();
    }

    @AfterTest
    void afterTest() {
        cleanDriverProcess();
    }

    @Test()
    void tc01() {
        homepage.chooseProductFromSearchDropdown("Cerave");
        productDetailsPage.addProductToCart();

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
        productDetailsPage.addProductToCart();

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
