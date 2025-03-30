import cores.Browser;
import cores.WebsiteDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.Homepage;
import pages.ProductDetailsPage;
import pages.ProductPage;

public class HomePageTest {
    WebsiteDriver webDriver;
    Homepage homepage;
    ProductPage productPage;
    ProductDetailsPage productDetailsPage;

    @BeforeTest
    void setup() throws InterruptedException {
        webDriver = WebsiteDriver.init(Browser.CHROME);
        homepage = new Homepage(webDriver);
        productPage = new ProductPage(webDriver);
        productDetailsPage = new ProductDetailsPage(webDriver);

        webDriver.navigate("https://hasaki.vn/");

        homepage.cancelPopup();
        homepage.cancelCookie();
//        Thread.sleep(2000);
        homepage.login("0345864246","#Onimusha00");


    }

    @Test
    public void demo(){
        webDriver.findElement("search").setText("hello world");
//        webDriver.findByXpath("search").sendKeys("aa");
    }

//    @AfterTest
//    void teardown() {
//        if (!webDriver.findByCss("nav[aria-label='Main'] button.p-0 a span:nth-child(3)").getText().equals("0")) {
//            webDriver.findByCss("nav[aria-label='Main'] button.p-0").click();
//
//            while (true) {
//                Integer size = webDriver.findAllByXpath("//tbody/tr").size();
//                if (size != 0) {
//                    webDriver.waitToBeClickableByXpath("//tbody/tr//button[text()='Xóa']").click();
//                    webDriver.waitToBeInvisibleByCss("div.animate-spin");
//                } else break;
//            }
//        }
//        webDriver.moveToElementByXpath("//nav[@aria-label='Main']//li[1]");
//        webDriver.findByXpath("//span[text()='Thoát']").click();
//        webDriver.quit();
//    }

//    @Test
//    void test() throws InterruptedException {
//
//        //Choose product
//        homepage.chooseProductType("Chăm Sóc Da Mặt", "Tẩy Trang Mặt");
//
//        productPage.chooseProduct("Combo 2 Nước Tẩy Trang Bí Đao Cocoon Làm Sạch & Giảm Dầu 500ml");
//
//        productDetailsPage.increaseProductQty();
//        Assert.assertEquals(webDriver.findByCss("input[name='qty']").getDomAttribute("value"), "2");
//
//        productDetailsPage.addProductToCart();
//        Assert.assertTrue(webDriver.findByXpath("//div[text()='Sản phẩm chỉ được mua tối đa là 1']").isDisplayed());
//        webDriver.waitToBeInvisibleByXpath("//div[text()='Sản phẩm chỉ được mua tối đa là 1']");
//
////        Thread.sleep(3000);
//        productDetailsPage.decreaseProductQty();
//        productDetailsPage.addProductToCart();
//        Assert.assertTrue(webDriver.findByXpath("//div[text()='Sản Phẩm đã được thêm vào giỏ hàng thành công']").isDisplayed());
//
//        Thread.sleep(2000);
//
//        String productQuantity = webDriver.findByXpath("//span[text()='Cart Icon']/following-sibling::span").getText();
//        String productName = webDriver.findByXpath("//h1").getText();
//        String productPrice = webDriver.findByCss("span.text-orange.text-lg.font-bold").getText().replaceAll("[^0-9]", "");
//
//        Assert.assertEquals(productQuantity, "1");
//
//        productDetailsPage.clickToCart();
//        Assert.assertEquals(webDriver.findByXpath("//a[text()='Combo 2 Nước Tẩy Trang Bí Đao Cocoon Làm Sạch & Giảm Dầu 500ml']").getText(), productName);
//
//        Integer calculatedPrice = (Integer.parseInt(productQuantity) * Integer.parseInt(productPrice));
//        Thread.sleep(2000);
//        String totalPriceeAt = webDriver.findByXpath("//tbody//tr[1]/td[4]/div").getText().replaceAll("[^0-9]", "");
//        Integer totalPrice = Integer.parseInt(totalPriceeAt);
//
//        Assert.assertEquals(calculatedPrice, totalPrice);
//    }


}
