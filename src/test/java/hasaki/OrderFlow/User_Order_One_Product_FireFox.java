package hasaki.OrderFlow;

import com.aventstack.extentreports.markuputils.ExtentColor;
import cores.*;
import org.apache.logging.log4j.LogManager;
import org.testng.annotations.*;
import reportConfig.ExtentTestManager;

public class User_Order_One_Product_FireFox extends BaseTest {

    static {
        startTestLog(User_Order_One_Product_FireFox.class, "User order a product on the website on Thread: " + (int) Thread.currentThread().getId(), (int) Thread.currentThread().getId());
    }
//    @BeforeSuite
//    void beforeSuite() {
//        startTestLog(User_Order_One_Product_FireFox.class, "User order a product on the website on Thread: " + (int) Thread.currentThread().getId(), (int) Thread.currentThread().getId());
//    }

    @Parameters({"browser", "url", "username", "password"})
    @BeforeClass
    void beforeClass(Browser browser, String url, String username, String password) {
        logInfo("Browser: " + browser, ExtentColor.LIME);
        webDriver = DriverFactory.initWebsiteDriver(browser);


        homepage = PageFactory.generateHomePage(webDriver);
        productPage = PageFactory.generateProductsPage(webDriver);
        productDetailsPage = PageFactory.generateProductDetailsPage(webDriver);
        cartPage = PageFactory.generateCartPage(webDriver);
        paymentPage = PageFactory.generatePaymentPage(webDriver);

        logInfo("- Navigate to " + url);
        webDriver.navigate(url);

        logInfo("- Close popup");
        homepage.cancelPopup();

        logInfo("- Reject cookie");
        homepage.cancelCookie();

        logInfo("- Login with Username: " + username + " Password: " + password);
        homepage.login(username, password);

        logInfo("- Remove products in Cart if they exist");
        homepage.removeProductFromCart();
    }

    @AfterMethod
    void afterMethod() {
        logInfo("Switch back to main tab after each test case");
        switchToMainWebsite();

        logInfo("Navigate back to Home page after each test case");
        navigateToHomePage();
    }

    @AfterClass
    void afterClass() {
        logInfo("------ Tear down steps include ------");
        logInfo("- Log out");
        logout();

        logInfo("- Close the browser");
        quitBrowser();
    }

//    @AfterSuite(alwaysRun = true)
//    void afterSuite() {
//        logInfo("- Clean background process (driver)");
//        cleanDriverProcess();
//    }

    @Test()
    void tc01() {

//        Choose product
        logInfo("Choose 'Skin Care' in Category Menu, then choose Cleansing product type");
        homepage.chooseProductType("Chăm Sóc Da Mặt", "Tẩy Trang Mặt");

        logInfo("Choose a specific product");
        productPage.chooseProduct("Combo 2 Nước Tẩy Trang Bí Đao Cocoon Làm Sạch & Giảm Dầu 500ml");

        logInfo("Increase product quantity by 1");
        productDetailsPage.increaseProductQty();

        verifyEquals(webDriver.getDomAttribute("input[name='qty']", "value"), "2");

        logInfo("Click add product to Cart");
        productDetailsPage.addProductToCart();
//        attachScreenshot();
        sleepInSecond(1);

        logInfo("Wait for warning message invisible: 'Maximum quantity is 1'");
        webDriver.waitToBeInvisible("//div[text()='Sản phẩm chỉ được mua tối đa là 1']");

        logInfo("Decrease product quantity by 1");
        productDetailsPage.decreaseProductQty();

        logInfo("Click add product to cart");
        productDetailsPage.addProductToCart();

        verifyTrue(webDriver.isDisplayed("//div[text()='Sản Phẩm đã được thêm vào giỏ hàng thành công']"), "A label display product is added to cart.");

        logInfo("Wait for success message invisible: 'Successfully add product to the cart'");
        productDetailsPage.waitToBeInvisible("//div[text()='Sản Phẩm đã được thêm vào giỏ hàng thành công']");

        sleepInSecond(2);

        String productQuantity = webDriver.getText("//span[text()='Cart Icon']/following-sibling::span");
        String productName = webDriver.getText("//h1");
        String productPrice = webDriver.getText("span.text-orange.text-lg.font-bold").replaceAll("[^0-9]", "");

        verifyEquals(productQuantity, "1");

        logInfo("Click to view Cart info");
        productDetailsPage.clickToCart();

        verifyEquals(webDriver.getText("//a[text()='Combo 2 Nước Tẩy Trang Bí Đao Cocoon Làm Sạch & Giảm Dầu 500ml']"), productName);

        Integer calculatedPrice = (Integer.parseInt(productQuantity) * Integer.parseInt(productPrice));

        sleepInSecond(2);

        String totalPriceeAt = webDriver.getText("//tbody//tr[1]/td[4]/div").replaceAll("[^0-9]", "");
        Integer totalPrice = Integer.parseInt(totalPriceeAt);

        verifyEquals(calculatedPrice, totalPrice);

        logInfo("Click proceed to Cart");
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

//        logInfo("Click to edit Delivery address");
//        paymentPage.chooseEdit("Địa chỉ nhận hàng", "Thay đổi");
//
//        logInfo("Click to add a new address");
//        paymentPage.clickAddNewAddress();
//
//        logInfo("Click Continue to create a new address");
//        paymentPage.clickContinue("Thêm địa chỉ mới");
//
//        verifyEquals(paymentPage.getCommonValidationMessageInput("Số điện thoại"), "Vui lòng điền số điện thoại");
//        verifyEquals(webDriver.getText("//input[@placeholder='Họ và tên']/following-sibling::p"), "Vui lòng điền Họ và Tên");
//        verifyEquals(paymentPage.getCommonValidationMessageDropdown("Chọn Tỉnh/ TP, Quận/ Huyện"), "Vui lòng chọn Tỉnh/ TP, Quận/ Huyện");
//        verifyEquals(paymentPage.getCommonValidationMessageDropdown("Chọn Phường/ Xã"), "Vui lòng chọn Phường/ Xã");
//        verifyEquals(webDriver.getText("//input[@placeholder='Số nhà + Tên đường']/following-sibling::p"), "Vui lòng điền địa chỉ");

//        String phoneNo = "0345864246";
//        String userName = "Dat Le Mot" + randomAlphabetic(4);
//        String cityName = "Quận Tân Bình";
//        String wardName = "Phường 10";
//        String streetNumberName = "687 Lạc Long Quân";
//
//        logInfo("Input phone number for new address");
//        paymentPage.setTextToNewAddressFields("Số điện thoại", phoneNo);
//
//        logInfo("Input Name contact for new address");
//        paymentPage.setTextToNewAddressFields("Họ và tên", userName);
//
//        logInfo("Input City for new address");
//        paymentPage.chooseCity(cityName);
//
//        logInfo("Input Ward for new address");
//        paymentPage.chooseWard(wardName);
//
//        logInfo("Click to Street field");
//        paymentPage.clickStreetField();
//
//        //Continue button is disable if user doesn't input street number
//        verifyFalse(webDriver.isEnabled("//span[text()='Sửa vị trí trên bản đồ']/parent::div//following-sibling::div//button[text()='Tiếp tục']"));
//
//        logInfo("Input street for new address, which does not meet minimum length of characters");
//        paymentPage.setTextStreetField("687");
//
//        sleepInSecond(3);
//
//        verifyEquals(webDriver.getText("(//input[@placeholder='Nhập vị trí của bạn']/following-sibling::span)[1]"), "Địa chỉ phải trên 5 ký tự");
//
//        logInfo("Input street for new address, which is a valid length");
//        paymentPage.setTextStreetField(streetNumberName);
//
//        //Continue input is enable
//        verifyTrue(webDriver.isEnabled("//span[text()='Sửa vị trí trên bản đồ']/parent::div//following-sibling::div//button[text()='Tiếp tục']"));
//
//        String newStreetNo = paymentPage.getStreetNumberInputValue("value");
//
//        logInfo("Click to create a new street address");
//        paymentPage.clickContinueStreetNumberButton();
//
//        logInfo("Click to create a new address");
//        paymentPage.clickContinue("Thêm địa chỉ mới");
//
//        logInfo("Wait for success message invisible: 'Successfully update a new delivery address'");
//        paymentPage.waitForMessageInvisible("Cập nhật địa chỉ thành công");
//
//        String[] newUserInfosArr = webDriver.getText("//p[contains(string(),'Dat Le Mot')]/ancestor::label")
//                .replaceAll("\n", "#")
//                .split("#");
//
//        verifyEquals(newUserInfosArr[0], userName + " - " + phoneNo);
//        verifyEquals(newUserInfosArr[3], newStreetNo + ", "
//                + wardName + ", " + cityName + ", " + "Hồ Chí Minh");
//
//        logInfo("Click to delete an address");
//        paymentPage.deleteAddress(newUserInfosArr[0]);
//
//        logInfo("Wait for success message invisible: 'Successfully delete the address'");
//        paymentPage.waitForMessageInvisible("Thông tin địa chỉ nhận hàng đã được xóa.");
//
        //Only Firefox, need to duplicate click
//        logInfo("Click to continue cart process");
//        paymentPage.clickContinue("Địa chỉ nhận hàng");
//
//        logInfo("Click to continue cart process again");
//        paymentPage.clickContinue("Địa chỉ nhận hàng");
//
//        logInfo("Wait for success message invisible: 'Successfully update delivery address'");
//        paymentPage.waitForMessageInvisible("Cập nhật địa chỉ thành công");

        logInfo("Click to Edit the payment method");
        paymentPage.chooseEdit("Hình thức thanh toán", "Thay đổi");

        //Choose by name
        logInfo("Change payment method to VNPAY");
        paymentPage.choosePaymentMethod("Thanh toán trực tuyến VNPAY");

        logInfo("Click to continue cart process");
        paymentPage.clickContinue("Hình thức thanh toán");

        logInfo("Wait for success message invisible: 'Successfully update payment method'");
        paymentPage.waitForMessageInvisible("Cập nhật hình thức thanh toán thành công");

        logInfo("Click to edit coupons");
        paymentPage.chooseEdit("Phiếu mua hàng", "Chọn phiếu mua hàng");

        verifyTrue(webDriver.isDisplayed("//h2[text()='Bạn có phiếu mua hàng']"));

        logInfo("Close Coupon popup");
        paymentPage.closePopup();

        sleepInSecond(1);

        logInfo("Click to edit vouchers");
        paymentPage.chooseEdit("Mã giảm giá", "Nhập mã giảm giá");

        verifyTrue(webDriver.isDisplayed("//h2[text()='Bạn có mã giảm giá']"));

        logInfo("Close Voucher popup");
        paymentPage.closePopup();

        sleepInSecond(1);

        paymentPage.changeProduct();

        sleepInSecond(2);

        verifyTrue(paymentPage.getPageTitle().contains("Giỏ hàng"));
    }
}
