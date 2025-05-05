package hasaki.OrderFlow;

import cores.BaseTest;
import cores.Browser;
import cores.DriverFactory;
import cores.PageFactory;
import org.testng.annotations.*;
import reportConfig.ExtentTestManager;

public class User_Order_One_Product extends BaseTest {

    @Parameters({"browser", "url", "username", "password"})
    @BeforeClass
    void beforeClass(Browser browser, String url, String username, String password) {
        ExtentTestManager.startTest(User_Order_One_Product.class.getName().split("\\.")[2].replace("_", " ") + " " + browser + " Test Suite",
                "User order a product on the website.");
        extentTest = ExtentTestManager.getTest();

        logInfoExtent("------ Setup steps include ------");
        logInfoExtent("- Initialize relevant pages");
        webDriver = DriverFactory.initWebsiteDriver(browser);
        homepage = PageFactory.generateHomePage(webDriver);
        productPage = PageFactory.generateProductsPage(webDriver);
        productDetailsPage = PageFactory.generateProductDetailsPage(webDriver);
        cartPage = PageFactory.generateCartPage(webDriver);
        paymentPage = PageFactory.generatePaymentPage(webDriver);

        logInfoExtent("- Navigate to " + url);
        webDriver.navigate(url);

        logInfoExtent("- Close popup");
        homepage.cancelPopup();

        logInfoExtent("- Reject cookie");
        homepage.cancelCookie();

        logInfoExtent("- Login with Username: " + username + " Password: " + password);
        homepage.login(username, password);

        logInfoExtent("- Remove products in Cart if they exist");
        homepage.removeProductFromCart();

    }

    @AfterMethod
    void afterMethod() {
        logInfoExtent("Switch back to main tab after each test case");
        switchToMainWebsite();

        logInfoExtent("Navigate back to Home page after each test case");
        navigateToHomePage();
    }

    @AfterClass
    void afterClass() {
        logInfoExtent("------ Tear down steps include ------");
        logInfoExtent("- Log out");
        logout();

        logInfoExtent("- Close the browser");
        quitBrowser();
    }

    @AfterTest(alwaysRun = true)
    void afterTest() {
        logInfoExtent("- Clean background process (driver)");
        cleanDriverProcess();
    }

    @Test()
    void tc01() {
//        Choose product

        logInfoExtent("Choose 'Skin Care' in Category Menu, then choose Cleansing product type");
        homepage.chooseProductType("Chăm Sóc Da Mặt", "Tẩy Trang Mặt");

        logInfoExtent("Choose a specific product");
        productPage.chooseProduct("Combo 2 Nước Tẩy Trang Bí Đao Cocoon Làm Sạch & Giảm Dầu 500ml");

        logInfoExtent("Increase product quantity by 1");
        productDetailsPage.increaseProductQty();

        verifyEquals(webDriver.getDomAttribute("input[name='qty']", "value"), "2", "Verify product quantity is 2");

        logInfoExtent("Click add product to Cart");
        productDetailsPage.addProductToCart();

        verifyTrue(webDriver.isDisplayed("//div[text()='Sản phẩm chỉ được mua tối đa là 1']"), "Verify warning message display: Maximum quantity can buy is 1");

        logInfoExtent("Wait for warning message invisible: 'Maximum quantity is 1'");
        webDriver.waitToBeInvisible("//div[text()='Sản phẩm chỉ được mua tối đa là 1']");

        logInfoExtent("Decrease product quantity by 1");
        productDetailsPage.decreaseProductQty();

        logInfoExtent("Click add product to cart");
        productDetailsPage.addProductToCart();

        verifyTrue(webDriver.isDisplayed("//div[text()='Sản Phẩm đã được thêm vào giỏ hàng thành công']"), "Verify success message: Product is added to cart.");

        logInfoExtent("Wait for success message invisible: 'Successfully add product to the cart'");
        productDetailsPage.waitToBeInvisible("//div[text()='Sản Phẩm đã được thêm vào giỏ hàng thành công']");

        sleepInSecond(2);

        String productQuantity = webDriver.getText("//span[text()='Cart Icon']/following-sibling::span");
        String productName = webDriver.getText("//h1");
        String productPrice = webDriver.getText("span.text-orange.text-lg.font-bold").replaceAll("[^0-9]", "");

        verifyEquals(productQuantity, "1", "Verify product quantity is 1");

        logInfoExtent("Click to view Cart info");
        productDetailsPage.clickToCart();

        verifyEquals(webDriver.getText("//a[text()='Combo 2 Nước Tẩy Trang Bí Đao Cocoon Làm Sạch & Giảm Dầu 500ml']"), productName, "Verify the product name");

        Integer calculatedPrice = (Integer.parseInt(productQuantity) * Integer.parseInt(productPrice));

        sleepInSecond(2);

        String totalPriceeAt = webDriver.getText("//tbody//tr[1]/td[4]/div").replaceAll("[^0-9]", "");
        Integer totalPrice = Integer.parseInt(totalPriceeAt);

        verifyEquals(calculatedPrice, totalPrice, "Verify the price");

        logInfoExtent("Click proceed to Cart");
        cartPage.clickProceedToCart();

        sleepInSecond(2);

        verifyTrue(webDriver.getPageTitle().contains("Thanh toán"), "Verify the page title");

        //Temp verification of user delivery address
        String[] userInfos = webDriver.getText("//h2[text()='Địa chỉ nhận hàng']/following-sibling::div/child::div")
                .replace("\n", "#")
                .split("#");
        String addressType = userInfos[0];
        String userNameAndPhone = userInfos[1];
        String userAddress = userInfos[2];

        verifyEquals(addressType, "Nhà riêng", "Verify the address type");
        verifyEquals(userNameAndPhone, "Le Dat - 0345864246", "Verify delivery name and delivery phone number");
        verifyEquals(userAddress, "687/5 Lạc Long Quân, Phường 10, Quận Tân Bình, Hồ Chí Minh", "Verify the delivery address");

        logInfoExtent("Click to edit Delivery address");
        paymentPage.chooseEdit("Địa chỉ nhận hàng", "Thay đổi");

        logInfoExtent("Click to add a new address");
        paymentPage.clickAddNewAddress();

        logInfoExtent("Click Continue to create a new address");
        paymentPage.clickContinue("Thêm địa chỉ mới");

        verifyEquals(paymentPage.getCommonValidationMessageInput("Số điện thoại"), "Vui lòng điền số điện thoại", "Verify phone number is required");
        verifyEquals(webDriver.getText("//input[@placeholder='Họ và tên']/following-sibling::p"), "Vui lòng điền Họ và Tên", "Verify delivery name is required");
        verifyEquals(paymentPage.getCommonValidationMessageDropdown("Chọn Tỉnh/ TP, Quận/ Huyện"), "Vui lòng chọn Tỉnh/ TP, Quận/ Huyện", "Verify delivery city name is required");
        verifyEquals(paymentPage.getCommonValidationMessageDropdown("Chọn Phường/ Xã"), "Vui lòng chọn Phường/ Xã", "Verify delivery ward is required");
        verifyEquals(webDriver.getText("//input[@placeholder='Số nhà + Tên đường']/following-sibling::p"), "Vui lòng điền địa chỉ", "Verify deliver address is required");

        String phoneNo = "0345864246";
        String userName = "Dat Le Mot" + randomAlphabetic(4);
        String cityName = "Quận Tân Bình";
        String wardName = "Phường 10";
        String streetNumberName = "687 Lạc Long Quân";

        logInfoExtent("Input phone number for new address");
        paymentPage.setTextToNewAddressFields("Số điện thoại", phoneNo);

        logInfoExtent("Input Name contact for new address");
        paymentPage.setTextToNewAddressFields("Họ và tên", userName);

        logInfoExtent("Input City for new address");
        paymentPage.chooseCity(cityName);

        logInfoExtent("Input Ward for new address");
        paymentPage.chooseWard(wardName);

        logInfoExtent("Click to Street field");
        paymentPage.clickStreetField();

        //Continue button is disable if user doesn't input street number
        verifyFalse(webDriver.isEnabled("//span[text()='Sửa vị trí trên bản đồ']/parent::div//following-sibling::div//button[text()='Tiếp tục']"), "Verify Continue is disabled if user doesn't input the address");

        logInfoExtent("Input street for new address, which does not meet minimum length of characters");
        paymentPage.setTextStreetField("687");

        sleepInSecond(2);

        verifyEquals(webDriver.getText("(//input[@placeholder='Nhập vị trí của bạn']/following-sibling::span)[1]"), "Địa chỉ phải trên 5 ký tự", "Verify minimum length to find address is 5 characters");

        logInfoExtent("Input street for new address, which is a valid length");
        paymentPage.setTextStreetField(streetNumberName);

        //Continue input is enable
        verifyTrue(webDriver.isEnabled("//span[text()='Sửa vị trí trên bản đồ']/parent::div//following-sibling::div//button[text()='Tiếp tục']"), "Verify Continue is enable after input valid address");

        String newStreetNo = paymentPage.getStreetNumberInputValue("value");

        logInfoExtent("Click to create a new street address");
        paymentPage.clickContinueStreetNumberButton();

        logInfoExtent("Click to create a new address");
        paymentPage.clickContinue("Thêm địa chỉ mới");

        logInfoExtent("Wait for success message invisible: 'Successfully update a new delivery address'");
        paymentPage.waitForMessageInvisible("Cập nhật địa chỉ thành công");

        String[] newUserInfosArr = webDriver.getText("//p[contains(string(),'Dat Le Mot')]/ancestor::label")
                .replaceAll("\n", "#")
                .split("#");

        verifyEquals(newUserInfosArr[0], userName + " - " + phoneNo, "Verify deliver name and phone number display correctly");
        verifyEquals(newUserInfosArr[3], newStreetNo + ", "
                + wardName + ", " + cityName + ", " + "Hồ Chí Minh", "Verify deliver address display correctly");

        logInfoExtent("Click to delete an address");
        paymentPage.deleteAddress(newUserInfosArr[0]);

        logInfoExtent("Wait for success message invisible: 'Successfully delete the address'");
        paymentPage.waitForMessageInvisible("Thông tin địa chỉ nhận hàng đã được xóa.");

        logInfoExtent("Click to continue cart process");
        paymentPage.clickContinue("Địa chỉ nhận hàng");

        logInfoExtent("Wait for success message invisible: 'Successfully update delivery address'");
        paymentPage.waitForMessageInvisible("Cập nhật địa chỉ thành công");

        logInfoExtent("Click to Edit the payment method");
        paymentPage.chooseEdit("Hình thức thanh toán", "Thay đổi");

        //Choose by name
        logInfoExtent("Change payment method to VNPAY");
        paymentPage.choosePaymentMethod("Thanh toán trực tuyến VNPAY");

        logInfoExtent("Click to continue cart process");
        paymentPage.clickContinue("Hình thức thanh toán");

        logInfoExtent("Wait for success message invisible: 'Successfully update payment method'");
        paymentPage.waitForMessageInvisible("Cập nhật hình thức thanh toán thành công");

        logInfoExtent("Click to edit coupons");
        paymentPage.chooseEdit("Phiếu mua hàng", "Chọn phiếu mua hàng");

        verifyTrue(webDriver.isDisplayed("//h2[text()='Bạn có phiếu mua hàng']"), "Verify coupon popup is displayed");

        logInfoExtent("Close Coupon popup");
        paymentPage.closePopup();

        logInfoExtent("Click to edit vouchers");
        paymentPage.chooseEdit("Mã giảm giá", "Nhập mã giảm giá");

//        Assert.assertTrue(webDriver.isDisplayed("//h2[text()='Bạn có mã giảm giá']"));
        verifyTrue(webDriver.isDisplayed("//h2[text()='Bạn có mã giảm giá']"), "Verify voucher popup is displayed");

        logInfoExtent("Close Voucher popup");
        paymentPage.closePopup();

        logInfoExtent("Click to change the desired product");
        paymentPage.changeProduct();

        sleepInSecond(2);

//        Assert.assertTrue(paymentPage.getPageTitle().contains("Giỏ hàng"));
        verifyTrue(paymentPage.getPageTitle().contains("Giỏ hàng"));
    }
}
