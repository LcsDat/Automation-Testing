package hasaki.OrderFlow;

import core.BaseTest;
import cores.Browser;
import cores.DriverFactory;
import cores.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class Scenario01 extends BaseTest {

//    @BeforeTest
//    void beforeTest() {
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
        homepage.login("0345864246", "#Onimusha00");
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

    @Test()
    void tc01() {

//        Choose product
        homepage.chooseProductType("Chăm Sóc Da Mặt", "Tẩy Trang Mặt");

        productPage.chooseProduct("Combo 2 Nước Tẩy Trang Bí Đao Cocoon Làm Sạch & Giảm Dầu 500ml");

        productDetailsPage.increaseProductQty();
        Assert.assertEquals(webDriver.getDomAttribute("input[name='qty']", "value"), "2");

        productDetailsPage.addProductToCart();
        Assert.assertTrue(webDriver.findElement("//div[text()='Sản phẩm chỉ được mua tối đa là 1']").isDisplayed());
        webDriver.waitToBeInvisibleBy("//div[text()='Sản phẩm chỉ được mua tối đa là 1']");

        productDetailsPage.decreaseProductQty();
        productDetailsPage.addProductToCart();
        Assert.assertTrue(webDriver.isDisplayed("//div[text()='Sản Phẩm đã được thêm vào giỏ hàng thành công']"));
        webDriver.waitToBeInvisibleBy("//div[text()='Sản Phẩm đã được thêm vào giỏ hàng thành công']");

        sleepInSecond(2);

        String productQuantity = webDriver.getText("//span[text()='Cart Icon']/following-sibling::span");
        String productName = webDriver.getText("//h1");
        String productPrice = webDriver.getText("span.text-orange.text-lg.font-bold").replaceAll("[^0-9]", "");

        Assert.assertEquals(productQuantity, "1");

        productDetailsPage.clickToCart();
        Assert.assertEquals(webDriver.getText("//a[text()='Combo 2 Nước Tẩy Trang Bí Đao Cocoon Làm Sạch & Giảm Dầu 500ml']"), productName);

        Integer calculatedPrice = (Integer.parseInt(productQuantity) * Integer.parseInt(productPrice));

        sleepInSecond(2);

        String totalPriceeAt = webDriver.getText("//tbody//tr[1]/td[4]/div").replaceAll("[^0-9]", "");
        Integer totalPrice = Integer.parseInt(totalPriceeAt);

        Assert.assertEquals(calculatedPrice, totalPrice);
    }
}
