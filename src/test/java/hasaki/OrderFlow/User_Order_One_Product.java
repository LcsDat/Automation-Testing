package hasaki.OrderFlow;

import core.BaseTest;
import cores.Browser;
import cores.DriverFactory;
import cores.PageFactory;
import org.testng.Assert;
import org.testng.annotations.*;

public class User_Order_One_Product extends BaseTest {

    @Parameters({"chrome", "url"})
    @BeforeClass
    void beforeClass(Browser browser, String url) {
        webDriver = DriverFactory.initWebsiteDriver(browser);
        homepage = PageFactory.generateHomePage(webDriver);
        productPage = PageFactory.generateProductsPage(webDriver);
        productDetailsPage = PageFactory.generateProductDetailsPage(webDriver);
        cartPage = PageFactory.generateCartPage(webDriver);
        paymentPage = PageFactory.generatePaymentPage(webDriver);

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
        verifyEquals(webDriver.getDomAttribute("input[name='qty']", "value"), "2");

        productDetailsPage.addProductToCart();
        verifyTrue(webDriver.findElement("//div[text()='Sản phẩm chỉ được mua tối đa là 1']").isDisplayed());
        webDriver.waitToBeInvisibleBy("//div[text()='Sản phẩm chỉ được mua tối đa là 1']");

        productDetailsPage.decreaseProductQty();
        productDetailsPage.addProductToCart();
        verifyTrue(webDriver.isDisplayed("//div[text()='Sản Phẩm đã được thêm vào giỏ hàng thành công']"), "A label display product is added to cart.");
        webDriver.waitToBeInvisibleBy("//div[text()='Sản Phẩm đã được thêm vào giỏ hàng thành công']");

        sleepInSecond(2);

        String productQuantity = webDriver.getText("//span[text()='Cart Icon']/following-sibling::span");
        String productName = webDriver.getText("//h1");
        String productPrice = webDriver.getText("span.text-orange.text-lg.font-bold").replaceAll("[^0-9]", "");
        verifyEquals(productQuantity, "1");

        productDetailsPage.clickToCart();
        verifyEquals(webDriver.getText("//a[text()='Combo 2 Nước Tẩy Trang Bí Đao Cocoon Làm Sạch & Giảm Dầu 500ml']"), productName);

        Integer calculatedPrice = (Integer.parseInt(productQuantity) * Integer.parseInt(productPrice));

        sleepInSecond(2);

        String totalPriceeAt = webDriver.getText("//tbody//tr[1]/td[4]/div").replaceAll("[^0-9]", "");
        Integer totalPrice = Integer.parseInt(totalPriceeAt);

        verifyEquals(calculatedPrice, totalPrice);

        cartPage.clickProceedToCart();

        sleepInSecond(2);
        verifyTrue(webDriver.getPageTitle().contains("Thanh toán"));

        //Temp verification of user delivery address
        String[] userInfos = webDriver.getText("//h2[text()='Địa chỉ nhận hàng']/following-sibling::div/child::div")
                .replace("\n", "#")
                .split("#");
        String addressType = userInfos[0];
        String userNameAndPhone = userInfos[1];
        String userAddress = userInfos[2];

        verifyEquals(addressType, "Nhà riêng");
        verifyEquals(userNameAndPhone, "Le Dat - 0345864246");
        verifyEquals(userAddress, "687/5 Lạc Long Quân, Phường 10, Quận Tân Bình, Hồ Chí Minh");

        paymentPage.chooseEdit("Địa chỉ nhận hàng", "Thay đổi");
        paymentPage.clickAddNewAddress();
        paymentPage.clickContinue();

        verifyEquals(paymentPage.getCommonValidationMessageInput("Số điện thoại"), "Vui lòng điền số điện thoại");
        verifyEquals(webDriver.getText("//input[@placeholder='Họ và tên']/following-sibling::p"), "Vui lòng điền Họ và Tên");
        verifyEquals(paymentPage.getCommonValidationMessageDropdown("Chọn Tỉnh/ TP, Quận/ Huyện"), "Vui lòng chọn Tỉnh/ TP, Quận/ Huyện");
        verifyEquals(paymentPage.getCommonValidationMessageDropdown("Chọn Phường/ Xã"), "Vui lòng chọn Phường/ Xã");
        verifyEquals(webDriver.getText("//input[@placeholder='Số nhà + Tên đường']/following-sibling::p"), "Vui lòng điền địa chỉ");

        String phoneNo = "0345864246";
        String userName = "Dat Le Mot";
        String cityName = "Quận Tân Bình";
        String wardName = "Phường 10";
        String streetNumberName = "687 Lạc Long Quân";

        paymentPage.setTextToNewAddressFields("Số điện thoại", phoneNo);
        paymentPage.setTextToNewAddressFields("Họ và tên", userName);
        paymentPage.chooseCity(cityName);
        paymentPage.chooseWard(wardName);
        paymentPage.clickStreetField();

        //Continue button is disable if user doesn't input street number
        verifyFalse(webDriver.isEnabled("//span[text()='Sửa vị trí trên bản đồ']/parent::div//following-sibling::div//button[text()='Tiếp tục']"));

        paymentPage.setTextStreetField("687");

        sleepInSecond(3);
        verifyEquals(webDriver.getText("(//input[@placeholder='Nhập vị trí của bạn']/following-sibling::span)[1]"), "Địa chỉ phải trên 5 ký tự");

        paymentPage.setTextStreetField(streetNumberName);

        //Continue input is enable
        verifyTrue(webDriver.isEnabled("//span[text()='Sửa vị trí trên bản đồ']/parent::div//following-sibling::div//button[text()='Tiếp tục']"));

        String newStreetNo = paymentPage.getStreetNumberInputValue("value");

        paymentPage.clickContinueStreetNumberButton();
        paymentPage.clickContinue();
        webDriver.waitToBeInvisibleBy("//div[text()='Cập nhật địa chỉ thành công']");
        String newUserInfo = webDriver.getText("//p[contains(string(),'Dat Le Mot')]/ancestor::label");
        String newUserInfos = newUserInfo.replaceAll("\n", "#");
        String[] newUserInfosArr = newUserInfos.split("#");

        verifyEquals(newUserInfosArr[0], userName + " - " + phoneNo);
        verifyEquals(newUserInfosArr[3], newStreetNo + ", "
                + wardName + ", " + cityName + ", " + "Hồ Chí Minh");
    }
}
