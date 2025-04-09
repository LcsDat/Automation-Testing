package hasaki.FAQPage;

import core.BaseTest;
import cores.Browser;
import cores.DriverFactory;
import cores.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class FAQPageTCs extends BaseTest {
    @Parameters({"chrome", "url"})
    @BeforeClass
    void beforeClass(Browser browser, String url) {
        webDriver = DriverFactory.initWebsiteDriver(browser);
        homepage = PageFactory.generateHomePage(webDriver);
        productPage = PageFactory.generateProductsPage(webDriver);
        productDetailsPage = PageFactory.generateProductDetailsPage(webDriver);
        cartPage = PageFactory.generateCartPage(webDriver);
        storesLocationPage = PageFactory.generateStoresLocationPage(webDriver);
        faqPage = PageFactory.generateFAQPage(webDriver);

        webDriver.navigate(url);

        homepage.cancelPopup();
        homepage.cancelCookie();
        homepage.removeProductFromCart();
    }

    @AfterMethod
    void afterMethod() {
        switchToMainWebsite();
        navigateToHomePage();
    }

    @AfterClass
    void afterClass() {
        logout();
        quitBrowser();
    }

    @AfterTest(alwaysRun = true)
    void afterTest() {
        cleanDriverProcess();
    }

    @Test
    void tc01() {
        homepage.navigateFAQPage();
        homepage.switchWindow("Hỗ trợ khách hàng");

        Assert.assertEquals(faqPage.getSloganHeader(), "Xin chào! Chúng tôi có thể giúp gì cho bạn?");

    }

    @Test
    void tc02() {
        homepage.switchWindow("Hỗ trợ khách hàng");
        faqPage.navigateStoresLocationPage();

        Assert.assertEquals(storesLocationPage.getBreadcrumbText(), "Hệ Thống Cửa Hàng Hasaki Trên Toàn Quốc | Hasaki.vn");
        Assert.assertTrue(storesLocationPage.isStoresDisplayed());
        Assert.assertEquals(storesLocationPage.getTotalStores(), "Có 254 cửa hàng Hasaki trên toàn quốc");

        faqPage.clickStore("1");

        Assert.assertEquals(faqPage.getFullAddress("1").trim(),"Địa chỉ: 71 Hoàng Hoa Thám, Phường 13, Quận Tân Bình, Hồ Chí Minh");
        Assert.assertEquals(faqPage.getClockQuantity("1"),2);
    }
}
