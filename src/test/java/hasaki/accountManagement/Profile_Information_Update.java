package hasaki.accountManagement;

import com.aventstack.extentreports.markuputils.ExtentColor;
import cores.BaseTest;
import cores.Browser;
import cores.PageFactory;
import hasaki.OrderFlow.User_Order_One_Product;
import org.testng.annotations.*;

import java.lang.reflect.Method;

public class Profile_Information_Update extends BaseTest {

    @BeforeTest
    void beforeTest() {
        createLog(Profile_Information_Update.class);
    }

    @Parameters({"browser", "url", "username", "password"})
    @BeforeMethod
    void beforeMethod(Browser browser, String url, String username, String password, Method method) {

        createTestCase(method);

        logInfo(method, "Browser: " + browser, ExtentColor.LIME);
        webDriver = getWebDriver(browser);

        logInfo(method, "------ Setup steps include ------");
        logInfo(method, "- Initialize relevant pages");
        homepage = PageFactory.generateHomePage(webDriver);
        productPage = PageFactory.generateProductsPage(webDriver);
        productDetailsPage = PageFactory.generateProductDetailsPage(webDriver);
        cartPage = PageFactory.generateCartPage(webDriver);
        paymentPage = PageFactory.generatePaymentPage(webDriver);
        accountPage = PageFactory.generateAccountPage(webDriver);

        logInfo(method, "- Navigate to " + url);
        webDriver.navigate(url);

//        logInfo(method, "- Close popup");
//        homepage.cancelPopup();

        logInfo(method, "- Reject cookie");
        homepage.cancelCookie();

        logInfo(method, "- Login with Username: " + username + " Password: " + password);
        homepage.login(username, password);

        logInfo(method, "- Remove products in Cart if they exist");
        homepage.removeProductFromCart();
    }

    @AfterMethod
    void afterMethod(Method method) {
        logInfo(method, "------ Tear down steps include ------");
        logInfo(method, "- Log out");
        logout();

        logInfo(method, "- Close the browser");
        quitBrowser();
    }

    @Test
    void EditProfileUnderUserAvatar(Method method) {
        System.out.println("Test merge");
        logInfo(method, "Navigate to My Account");
        homepage.navigateToMyAccount();

        logInfo(method, "Select Edit Your Account, under the avatar");
        accountPage.clickToEditAccount();

        assertEquals(webDriver.getText("div.title_profile_page"), "Thông tin tài khoản");

        logInfo(method, "Update YOB to 1990");
        accountPage.selectYearOfBirth("1990");

        logInfo(method, "Update information");
        accountPage.updateInformation();

        assertEquals(webDriver.getText("div.alert-success"), "Cập nhật thông tin tài khoản thành công.");

        logInfo(method, "Update initial YOB");
        accountPage.selectYearOfBirth("1993");

        logInfo(method, "Update information");
        accountPage.updateInformation();

    }

    @Test
    void EditProfileInAccountManagement(Method method) {
        logInfo(method, "Navigate to My Account");
        homepage.navigateToMyAccount();

        logInfo(method, "Select Edit");
        accountPage.clickToEdit();

        assertEquals(webDriver.getText("div.title_profile_page"), "Thông tin tài khoản");

        logInfo(method, "Update YOB to 1990");
        accountPage.selectYearOfBirth("1990");

        logInfo(method, "Update information");
        accountPage.updateInformation();

        assertEquals(webDriver.getText("div.alert-success"), "Cập nhật thông tin tài khoản thành công.");

        logInfo(method, "Update initial YOB");
        accountPage.selectYearOfBirth("1993");

        logInfo(method, "Update information");
        accountPage.updateInformation();
    }

    @Test
    void EditProfileInAccountInformation(Method method) {
        logInfo(method, "Navigate to My Account");
        homepage.navigateToMyAccount();

        logInfo(method, "Select Account Information");
        accountPage.clickToAccountInformation();

        assertEquals(webDriver.getText("div.title_profile_page"), "Thông tin tài khoản");

        logInfo(method, "Update YOB to 1990");
        accountPage.selectYearOfBirth("1990");

        logInfo(method, "Update information");
        accountPage.updateInformation();

        assertEquals(webDriver.getText("div.alert-success"), "Cập nhật thông tin tài khoản thành công.");

        logInfo(method, "Update initial YOB");
        accountPage.selectYearOfBirth("1993");

        logInfo(method, "Update information");
        accountPage.updateInformation();
    }
}
