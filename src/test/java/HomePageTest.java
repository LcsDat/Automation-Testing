import cores.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.CartPage;
import pages.ProductPage;

public class HomePageTest {
    WebsiteDriver webDriver;
    HomePage homepage;
    ProductPage productPage;
    CartPage cartPage;

    @BeforeTest
    void setup() throws InterruptedException {
        webDriver = DriverFactory.initWebsiteDriver(Browser.CHROME);
        homepage = PageFactory.generateHomepage(webDriver);
        productPage = PageFactory.generateProductpage(webDriver);
        cartPage = PageFactory.generateCartpage(webDriver);

        webDriver.navigate("https://hasaki.vn/");

        homepage.cancelPopup();
        homepage.cancelCookie();
//        homepage.login("0345864246", "#Onimusha00");
//        homepage.checkCartQuantity();

    }

    @Test(priority = 1)
    void tc01() {
        webDriver.setText("input_search", "Sữa Rửa Mặt CeraVe");
        webDriver.findElements("//div[@id='suggestion_products']//h2")
                .stream()
                .filter(e -> e.getText().equals("Sữa Rửa Mặt CeraVe Sạch Sâu Cho Da Thường Đến Da Dầu 88ml"))
                .findFirst().get().click();
        webDriver.click("//div[text()='Giỏ hàng']");

        Assert.assertTrue(webDriver.waitToBeVisible("div[role='dialog']").isDisplayed());
        Assert.assertEquals(
                webDriver.findElement("input[name='username']").getDomAttribute("placeholder"),
                "Nhập email hoặc số điện thoại");
        Assert.assertEquals(
                webDriver.findElement("input[name='password']").getDomAttribute("placeholder"),
                "Nhập password");

        webDriver.click("button[aria-label='Close notify form']");
        webDriver.click("//div[text()='Mua ngay NowFree 2H ']");

        Assert.assertTrue(webDriver.findElement("div[role='dialog']").isDisplayed());
        Assert.assertEquals(
                webDriver.findElement("input[name='username']").getDomAttribute("placeholder"),
                "Nhập email hoặc số điện thoại");
        Assert.assertEquals(
                webDriver.findElement("input[name='password']").getDomAttribute("placeholder"),
                "Nhập password");

        webDriver.click("button[aria-label='Close notify form']");
    }

    @Test(priority = 2)
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

        webDriver.click("button[aria-label='Close notify form']");
        webDriver.click("//div[text()='Mua ngay NowFree 2H ']");

        Assert.assertTrue(webDriver.findElement("div[role='dialog']").isDisplayed());
        Assert.assertEquals(
                webDriver.findElement("input[name='username']").getDomAttribute("placeholder"),
                "Nhập email hoặc số điện thoại");
        Assert.assertEquals(
                webDriver.findElement("input[name='password']").getDomAttribute("placeholder"),
                "Nhập password");

        webDriver.click("button[aria-label='Close notify form']");
    }

    @AfterMethod
    void afterMethod() {
        if (webDriver.getPageTitle().startsWith("Hasaki.vn")) webDriver.click("div.logo_site");
        else webDriver.click("a[aria-label='Homepage']");
    }

    @AfterClass(alwaysRun = true)
    void afterClass() {
        cartPage.checkCartQuantity();
        webDriver.moveToElement("//nav[@aria-label='Main']//li[1]");
        webDriver.findElement("//span[text()='Thoát']").click();
        webDriver.quit();
    }

    @AfterTest
    void afterTest() {
        webDriver.killDriverProcess();
    }

    @Test(priority = 3)
    void tc03() throws InterruptedException {
        homepage.login("0345864246", "#Onimusha00");
        //Choose product
        homepage.chooseProductType("Chăm Sóc Da Mặt", "Tẩy Trang Mặt");

        productPage.chooseProduct("Combo 2 Nước Tẩy Trang Bí Đao Cocoon Làm Sạch & Giảm Dầu 500ml");

        cartPage.increaseProductQty();
        Assert.assertEquals(webDriver.findElement("input[name='qty']").getDomAttribute("value"), "2");

        cartPage.addProductToCart();
        Assert.assertTrue(webDriver.findElement("//div[text()='Sản phẩm chỉ được mua tối đa là 1']").isDisplayed());
        webDriver.waitToBeInvisibleBy("//div[text()='Sản phẩm chỉ được mua tối đa là 1']");

        cartPage.decreaseProductQty();
        cartPage.addProductToCart();
        Assert.assertTrue(webDriver.findElement("//div[text()='Sản Phẩm đã được thêm vào giỏ hàng thành công']").isDisplayed());
        webDriver.waitToBeInvisibleBy("//div[text()='Sản Phẩm đã được thêm vào giỏ hàng thành công']");

        String productQuantity = webDriver.findElement("//span[text()='Cart Icon']/following-sibling::span").getText();
        String productName = webDriver.findElement("//h1").getText();
        String productPrice = webDriver.findElement("span.text-orange.text-lg.font-bold").getText().replaceAll("[^0-9]", "");

        Assert.assertEquals(productQuantity, "1");

        cartPage.clickToCart();
        Assert.assertEquals(webDriver.findElement("//a[text()='Combo 2 Nước Tẩy Trang Bí Đao Cocoon Làm Sạch & Giảm Dầu 500ml']").getText(), productName);

        Integer calculatedPrice = (Integer.parseInt(productQuantity) * Integer.parseInt(productPrice));
        Thread.sleep(2000);
        String totalPriceeAt = webDriver.findElement("//tbody//tr[1]/td[4]/div").getText().replaceAll("[^0-9]", "");
        Integer totalPrice = Integer.parseInt(totalPriceeAt);

        Assert.assertEquals(calculatedPrice, totalPrice);
    }
}
