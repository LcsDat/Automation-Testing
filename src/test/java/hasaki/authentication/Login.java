package hasaki.authentication;

import com.aventstack.extentreports.markuputils.ExtentColor;
import cores.BaseTest;
import cores.Browser;
import cores.PageFactory;
import hasaki.OrderFlow.User_Order_One_Product_FireFox;
import org.testng.annotations.*;

public class Login extends BaseTest {

    @Parameters({"browser", "url"})
    @BeforeClass
    void beforeClass(Browser browser, String url) {
        createExtentLog(Login.class);

        logInfo("Browser: " + browser, ExtentColor.LIME);
        webDriver = getWebDriver(browser);
        sleepInSecond(3);

        logInfo("------ Setup steps include ------");
        logInfo("- Initialize relevant pages");
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

//        logInfo("- Login with Username: " + username + " Password: " + password);
//        homepage.login(username, password);
//
//        logInfo("- Remove products in Cart if they exist");
//        homepage.removeProductFromCart();
    }

//    @AfterMethod
//    void afterMethod() {
//        logInfo("Switch back to main tab after each test case");
//        switchToMainWebsite();
//
//        logInfo("Navigate back to Home page after each test case");
//        navigateToHomePage();
//        logout();
//    }


//    @AfterClass
//    void afterClass() {
//        logInfo("------ Tear down steps include ------");
//        logInfo("- Log out");
//        logout();
//
//        logInfo("- Close the browser");
//        quitBrowser();
//    }

    @Test(priority = 1)
    void userLoginWithValidData() {
        logInfo("log in with valid data");
        homepage.login("0345864246", "#Onimusha00");

        assertEquals(homepage.getWelcomeText(), "Chào Dat", "A welcome message is displayed to the user");

//        if (webDriver.isUnDisplayed("#btn-login")) {
//            webDriver.moveToElement("div.item_header.item_login.user_login");
//            webDriver.findElement("//a[contains(text(),'Thoát')]").click();
//        }
//        logInfo("wait");
//        webDriver.waitToBeInvisible("#btn-login");
        logInfo("log out");
//        logInfo("wait 5s");
//        sleepInSecond(5);
        webDriver.moveToElement("div.item_header.item_login.user_login");

        webDriver.click("//a[contains(text(),'Thoát')]");
    }

    @Test
    void userLoginWithInValidData() {
        logInfo("log in with invalid data");
        homepage.login("03458642466", "#Onimusha00");
        webDriver.clear("#username");
        webDriver.clear("#password");
        assertEquals(homepage.getWarningMessage(), "Tên đăng nhập hoặc mật khẩu không khớp !", "validation message with invalid data");
        webDriver.click("div#popup-login button.mfp-close");
    }

    @Test
    void userLoginWithBlankUsername() {
        logInfo("log in with blank username");
        homepage.login("", "#Onimusha00");
        webDriver.clear("#password");
//        sleepInSecond(5);
        assertEquals(homepage.getWarningMessage(), "Vui lòng nhập tên đăng nhập", "validation message require username");
        webDriver.click("div#popup-login button.mfp-close");
    }

    @Test
    void userLoginWithBlankPassword() {
        logInfo("log in with blank password");
//        sleepInSecond(5);
        homepage.login("0345864246", "");
        webDriver.clear("#username");
        assertEquals(homepage.getWarningMessage(), "Vui lòng nhập mật khẩu", "validation message require password");
        webDriver.click("div#popup-login button.mfp-close");
    }

    @Test
    void userLoginWithAllBlankFields() {
        logInfo("log in with blank fields");
        homepage.login("", "");
        assertEquals(homepage.getWarningMessage(), "Vui lòng nhập tên đăng nhập");
        webDriver.click("div#popup-login button.mfp-close");
    }

    @Test
    void userLoginWithFacebook() {
        webDriver.click("#btn-login");
        sleepInSecond(3);
        webDriver.click("#lg_login a.login-facebook");
        sleepInSecond(3);
        webDriver.switchWindow("Log in to Facebook");
        sleepInSecond(3);
        webDriver.setText("#email", "hideyashy11@gmail.com");
        sleepInSecond(3);
        webDriver.setText("#pass", "#Onimusha00");
        sleepInSecond(3);
        webDriver.click("#loginbutton");
        sleepInSecond(3);

        webDriver.click("//span[contains(text(),'Tiếp tục dưới')]");
        sleepInSecond(3);
        webDriver.switchWindow("Hasaki.vn | Mỹ Phẩm & Clinic");
        sleepInSecond(3);
        assertEquals(homepage.getWelcomeText(), "Chào Đạt");
//        sleepInSecond(3);
//        webDriver.waitToBeInvisible("#btn-login");
        sleepInSecond(3);
        webDriver.moveToElement("div.item_header.item_login.user_login");
        sleepInSecond(3);
        webDriver.findElement("//a[contains(text(),'Thoát')]").click();
        sleepInSecond(3);
    }

    @Test
    void userLoginWithDifferentAccountsInASameBrowser() {

    }

    @Test
    void userLoginWithSameAccountsInASameBrowser() {

    }

    @Test
    void userLoginWithDifferentAccountsInDifferentBrowsers() {

    }

    @Test
    void userLoginWithSameAccountsInDifferentBrowsers() {

    }


}
