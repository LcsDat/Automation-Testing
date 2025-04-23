package hasaki.OrderFlow;

import cores.BaseTest;
import cores.Browser;
import cores.DriverFactory;
import cores.PageFactory;
import org.testng.annotations.*;

public class User_Order_One_Product_FireFox extends BaseTest {

    @Parameters({"firefox", "url"})
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
        verifyTrue(webDriver.isDisplayed("//div[text()='Sản phẩm chỉ được mua tối đa là 1']"));
        webDriver.waitToBeInvisible("//div[text()='Sản phẩm chỉ được mua tối đa là 1']");

        productDetailsPage.decreaseProductQty();
        productDetailsPage.addProductToCart();
        verifyTrue(webDriver.isDisplayed("//div[text()='Sản Phẩm đã được thêm vào giỏ hàng thành công']"), "A label display product is added to cart.");
        productDetailsPage.waitToBeInvisible("//div[text()='Sản Phẩm đã được thêm vào giỏ hàng thành công']");

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
        paymentPage.clickContinue("Thêm địa chỉ mới");

        verifyEquals(paymentPage.getCommonValidationMessageInput("Số điện thoại"), "Vui lòng điền số điện thoại");
        verifyEquals(webDriver.getText("//input[@placeholder='Họ và tên']/following-sibling::p"), "Vui lòng điền Họ và Tên");
        verifyEquals(paymentPage.getCommonValidationMessageDropdown("Chọn Tỉnh/ TP, Quận/ Huyện"), "Vui lòng chọn Tỉnh/ TP, Quận/ Huyện");
        verifyEquals(paymentPage.getCommonValidationMessageDropdown("Chọn Phường/ Xã"), "Vui lòng chọn Phường/ Xã");
        verifyEquals(webDriver.getText("//input[@placeholder='Số nhà + Tên đường']/following-sibling::p"), "Vui lòng điền địa chỉ");

        String phoneNo = "0345864246";
        String userName = "Dat Le Mot" + randomAlphabetic(4);
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
        paymentPage.clickContinue("Thêm địa chỉ mới");
        paymentPage.waitForMessageInvisible("Cập nhật địa chỉ thành công");

        String[] newUserInfosArr = webDriver.getText("//p[contains(string(),'Dat Le Mot')]/ancestor::label")
                .replaceAll("\n", "#")
                .split("#");

        verifyEquals(newUserInfosArr[0], userName + " - " + phoneNo);
        verifyEquals(newUserInfosArr[3], newStreetNo + ", "
                + wardName + ", " + cityName + ", " + "Hồ Chí Minh");


        paymentPage.deleteAddress(newUserInfosArr[0]);
        paymentPage.waitForMessageInvisible("Thông tin địa chỉ nhận hàng đã được xóa.");

        //Only Firefox, need to duplicate click
        paymentPage.clickContinue("Địa chỉ nhận hàng");
        paymentPage.clickContinue("Địa chỉ nhận hàng");
        paymentPage.waitForMessageInvisible("Cập nhật địa chỉ thành công");

        paymentPage.chooseEdit("Hình thức thanh toán", "Thay đổi");

        //Choose by name
        paymentPage.choosePaymentMethod("Thanh toán trực tuyến VNPAY");
        paymentPage.clickContinue("Hình thức thanh toán");
        paymentPage.waitForMessageInvisible("Cập nhật hình thức thanh toán thành công");

        paymentPage.chooseEdit("Phiếu mua hàng", "Chọn phiếu mua hàng");

        verifyTrue(webDriver.isDisplayed("//h2[text()='Bạn có phiếu mua hàng']"));

        paymentPage.closePopup();

        paymentPage.chooseEdit("Mã giảm giá", "Nhập mã giảm giá");

        verifyTrue(webDriver.isDisplayed("//h2[text()='Bạn có mã giảm giá']"));

        paymentPage.closePopup();

        paymentPage.changeProduct();

        verifyTrue(paymentPage.getPageTitle().contains("Giỏ hàng"));
    }
}
