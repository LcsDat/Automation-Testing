import cores.*;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.HomePage;
import pages.CartPage;
import pages.ProductPage;

public class HomePageTest extends BaseTest {

    @Test(priority = 1)
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

        sleepInSecond(2);

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
