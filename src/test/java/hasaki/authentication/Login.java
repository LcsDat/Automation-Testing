package hasaki.authentication;

import com.aventstack.extentreports.markuputils.ExtentColor;
import cores.*;
import org.openqa.selenium.WindowType;
import org.testng.annotations.*;
import utilities.TestData;

import java.lang.reflect.Method;


public class Login extends BaseTest {
    WebsiteDriver driver;

    @Parameters("browser")
    @BeforeClass
    void beforeClass(Browser browser) {
        createExtentLog(Login.class);
        logInfo("Open the first Browser: " + browser, ExtentColor.LIME);
        webDriver = getWebDriver(browser);
        driver = webDriver;
    }

    @Parameters("url")
    @BeforeMethod
    void beforeMethod(String url) {
//        logInfo("Open the first Browser: " + browser, ExtentColor.LIME);
//        webDriver = getWebDriver(browser);


        logInfo("------ Setup steps include ------");
        logInfo("- Initialize relevant pages");
        homepage = PageFactory.generateHomePage(webDriver);

//        logInfo("- First browser: " + browser + " - Navigate to " + url);
        webDriver.navigate(url);
//        DriverFactory.initWebsiteDriver(Browser.CHROME);

//        logInfo("- First browser: " + browser + " - Close popup");
        homepage.cancelPopup();

//        logInfo("- First browser: " + browser + " - Reject cookie");
        homepage.cancelCookie();
    }

    @AfterMethod
    void afterMethod() {
        logInfo("------ Tear down steps include ------");
        logInfo("Remove product from Cart if existed");
        homepage.removeProductFromCart();

        logInfo("- Log out");
        logout();

        logInfo("- Close the browser");
//        quitBrowser();
        driver.closeTab();
    }

//    @Parameters({"browser", "url"})

    @Test(dataProvider = "loginTestData", dataProviderClass = TestData.class)
    void userLoginWithValidData(String username, String password, String expectedResult) {
//        logInfo("Open the first Browser: " + browser, ExtentColor.LIME);
//        webDriver = getWebDriver(browser);
//
//
//        logInfo("------ Setup steps include ------");
//        logInfo("- Initialize relevant pages");
//        homepage = PageFactory.generateHomePage(webDriver);
//
//        logInfo("- First browser: " + browser + " - Navigate to " + url);
//        webDriver.navigate(url);
//
//        logInfo("- First browser: " + browser + " - Close popup");
//        homepage.cancelPopup();
//
//        logInfo("- First browser: " + browser + " - Reject cookie");
//        homepage.cancelCookie();


//        logInfo("Test Case: " + method.getName());
//        logInfo("Test Case: " + method.getName() + " Bien driver: " + webDriver);
        homepage.login(username, password);

        logInfo("Verify user is logged in successfully");
        assertEquals(homepage.getWelcomeText(), "Chào " + expectedResult, "A welcome label + user first name is displayed to the user");
    }

    //    @Parameters({"browser", "url"})
    @Test(dataProvider = "loginTestData1", dataProviderClass = TestData.class)
    void userLoginWithIncorrectData(String username, String password, String a) {
//        logInfo("Open the first Browser: " + browser, ExtentColor.LIME);
//        webDriver = getWebDriver(browser);
//
//
//        logInfo("------ Setup steps include ------");
//        logInfo("- Initialize relevant pages");
//        homepage = PageFactory.generateHomePage(webDriver);
//
//        logInfo("- First browser: " + browser + " - Navigate to " + url);
//        webDriver.navigate(url);
//
//        logInfo("- First browser: " + browser + " - Close popup");
//        homepage.cancelPopup();
//
//        logInfo("- First browser: " + browser + " - Reject cookie");
//        homepage.cancelCookie();


//        logInfo("Test Case: " + method.getName());
//        logInfo("Test Case: " + method.getName() + " Bien driver: " + webDriver);
        homepage.login(username, password);
        System.out.println(a);
        assertEquals(homepage.getWarningMessage(), "Tên đăng nhập hoặc mật khẩu không khớp !", "Error message display when user incorrect data");
    }

////    @Parameters({"browser", "url"})
//    @Test(dataProvider = "loginTestData1", dataProviderClass = TestData.class)
//    void userLoginWithBlankUsername(Method method, Browser browser, String url) {
////        logInfo("Open the first Browser: " + browser, ExtentColor.LIME);
////        webDriver = getWebDriver(browser);
////
////
////        logInfo("------ Setup steps include ------");
////        logInfo("- Initialize relevant pages");
////        homepage = PageFactory.generateHomePage(webDriver);
////
////        logInfo("- First browser: " + browser + " - Navigate to " + url);
////        webDriver.navigate(url);
////
////        logInfo("- First browser: " + browser + " - Close popup");
////        homepage.cancelPopup();
////
////        logInfo("- First browser: " + browser + " - Reject cookie");
////        homepage.cancelCookie();
//
//
//        logInfo("Test Case: " + method.getName());
//        logInfo("Test Case: " + method.getName() + " Bien driver: " + webDriver);
//        homepage.login("", "#Onimusha00");
//
//        assertEquals(homepage.getWarningMessage(), "Vui lòng nhập tên đăng nhập", "Validation message display that username is required");
//    }
//
////    @Parameters({"browser", "url"})
//    @Test(dataProvider = "loginTestData", dataProviderClass = TestData.class)
//    void userLoginWithBlankPassword(Method method, Browser browser, String url) {
////        logInfo("Open the first Browser: " + browser, ExtentColor.LIME);
////        webDriver = getWebDriver(browser);
////
////
////        logInfo("------ Setup steps include ------");
////        logInfo("- Initialize relevant pages");
////        homepage = PageFactory.generateHomePage(webDriver);
////
////        logInfo("- First browser: " + browser + " - Navigate to " + url);
////        webDriver.navigate(url);
////
////        logInfo("- First browser: " + browser + " - Close popup");
////        homepage.cancelPopup();
////
////        logInfo("- First browser: " + browser + " - Reject cookie");
////        homepage.cancelCookie();
//
//
//        logInfo("Test Case: " + method.getName());
//        logInfo("Test Case: " + method.getName() + " Bien driver: " + webDriver);
//        homepage.login("0345864246", "");
//
//        assertEquals(homepage.getWarningMessage(), "Vui lòng nhập mật khẩu", "Validation message display that password is required");
//    }
//
////    @Parameters({"browser", "url"})
//    @Test(dataProvider = "loginTestData", dataProviderClass = TestData.class)
//    void userLoginWithAllBlankFields(Method method, Browser browser, String url) {
////        logInfo("Open the first Browser: " + browser, ExtentColor.LIME);
////        webDriver = getWebDriver(browser);
////
////
////        logInfo("------ Setup steps include ------");
////        logInfo("- Initialize relevant pages");
////        homepage = PageFactory.generateHomePage(webDriver);
////
////        logInfo("- First browser: " + browser + " - Navigate to " + url);
////        webDriver.navigate(url);
////
////        logInfo("- First browser: " + browser + " - Close popup");
////        homepage.cancelPopup();
////
////        logInfo("- First browser: " + browser + " - Reject cookie");
////        homepage.cancelCookie();
//
//
//        logInfo("Test Case: " + method.getName());
//        logInfo("Test Case: " + method.getName() + " Bien driver: " + webDriver);
//        homepage.login("", "");
//
//        assertEquals(homepage.getWarningMessage(), "Vui lòng nhập tên đăng nhập", "Validation message display that user need to fill credentials");
//    }
//
////    @Parameters({"browser", "url"})
//    @Test(dataProvider = "loginTestData", dataProviderClass = TestData.class)
//    void userLoginWithFacebook(Method method, Browser browser, String url) {
////        logInfo("Open the first Browser: " + browser, ExtentColor.LIME);
////        webDriver = getWebDriver(browser);
////
////
////        logInfo("------ Setup steps include ------");
////        logInfo("- Initialize relevant pages");
////        homepage = PageFactory.generateHomePage(webDriver);
////
////        logInfo("- First browser: " + browser + " - Navigate to " + url);
////        webDriver.navigate(url);
////
////        logInfo("- First browser: " + browser + " - Close popup");
////        homepage.cancelPopup();
////
////        logInfo("- First browser: " + browser + " - Reject cookie");
////        homepage.cancelCookie();
//
//
//        logInfo("Test Case: " + method.getName());
//        logInfo("Test Case: " + method.getName() + " Bien driver: " + webDriver);
//        homepage.loginByFacebook();
//
//        assertEquals(homepage.getWelcomeText(), "Chào Đạt");
//        sleepInSecond(2);
//    }
//
////    @Parameters({"browser", "url"})
//    @Test(dataProvider = "loginTestData", dataProviderClass = TestData.class)
//    void userLoginWithDifferentAccountsInASameBrowser(Method method, Browser browser, String url) {
////        logInfo("Open the first Browser: " + browser, ExtentColor.LIME);
////        webDriver = getWebDriver(browser);
////
////
////        logInfo("------ Setup steps include ------");
////        logInfo("- Initialize relevant pages");
////        homepage = PageFactory.generateHomePage(webDriver);
////
////        logInfo("- First browser: " + browser + " - Navigate to " + url);
////        webDriver.navigate(url);
////
////        logInfo("- First browser: " + browser + " - Close popup");
////        homepage.cancelPopup();
////
////        logInfo("- First browser: " + browser + " - Reject cookie");
////        homepage.cancelCookie();
//
//
//        logInfo("Test Case: " + method.getName());
//        logInfo("Test Case: " + method.getName() + " Bien driver: " + webDriver);
//        //Get window handle of 1st tab
//        var firstWindow = webDriver.getDriver().getWindowHandle();
//        System.out.println("1st window: " + firstWindow);
//
//        //Open 2nd tab, get window handle of 2nd tab
//        logInfo("Open a new tab then navigate to the application");
//        webDriver.openNewTab().get("https://hasaki.vn/");
//
//        var secondWindow = webDriver.getDriver().getWindowHandle();
//        System.out.println("2nd window: " + secondWindow);
//
//        //Log in second account in 2nd tab
//        logInfo("Log in a new different account in the second tab");
//        homepage.login("0345864246", "#Onimusha00");
//        //Verify it's logged
//        assertEquals(homepage.getWelcomeText(), "Chào Dat", "A welcome label + user first name is displayed to the user");
//
//        //Switch back 1st, verify the 2nd account now is logged in tab 1
//        logInfo("Switch back to the first tab");
//        webDriver.switchWindowByID(firstWindow);
//
//        //Log in 1st account in 1st tab
//        logInfo("Log in another account in the first tab");
//        homepage.login("0796280280", "27051993@Phuong");
//
//        //Verify it's logged
//        assertEquals(homepage.getWelcomeText(), "Chào Phương", "A welcome label + user first name is displayed to the user");
//
//        //Switch back 2nd, refresh and verify the 1st account now is logged in tab 2
//        logInfo("Switch back to the second tab");
//        webDriver.switchWindowByID(secondWindow);
//
//        logInfo("Refresh page");
//        homepage.refreshPage();
//
//        assertEquals(homepage.getWelcomeText(), "Chào Phương", "The account in the first tab, now is displayed in the second tab");
//
//        logInfo("Close the second tab");
//        webDriver.closeTab();
//
//        //Switch back to first window
//        logInfo("Switch back to the first tab");
//        webDriver.switchWindowByID(firstWindow);
//    }
//
////    @Parameters({"browser", "url"})
//    @Test(dataProvider = "loginTestData", dataProviderClass = TestData.class)
//    void userLoginWithSameAccountsInASameBrowser(Method method, Browser browser, String url) {
////        logInfo("Open the first Browser: " + browser, ExtentColor.LIME);
////        webDriver = getWebDriver(browser);
////
////
////        logInfo("------ Setup steps include ------");
////        logInfo("- Initialize relevant pages");
////        homepage = PageFactory.generateHomePage(webDriver);
////
////        logInfo("- First browser: " + browser + " - Navigate to " + url);
////        webDriver.navigate(url);
////
////        logInfo("- First browser: " + browser + " - Close popup");
////        homepage.cancelPopup();
////
////        logInfo("- First browser: " + browser + " - Reject cookie");
////        homepage.cancelCookie();
//
//
//        logInfo("Test Case: " + method.getName());
//        logInfo("Test Case: " + method.getName() + " Bien driver: " + webDriver);
//        //Get window handle of 1st tab
//        var firstWindow = webDriver.getDriver().getWindowHandle();
//        System.out.println("1st window: " + firstWindow);
//
//        //Open 2nd tab, get window handle of 2nd tab
//        logInfo("Open a new tab then navigate to the application");
//        webDriver.openNewTab().get("https://hasaki.vn/");
//
//        var secondWindow = webDriver.getDriver().getWindowHandle();
//        System.out.println("2nd window: " + secondWindow);
//
//        //Log in account in 2nd tab
//        logInfo("Log in with valid account in the second tab");
//        homepage.login("0345864246", "#Onimusha00");
//
//        //Verify it's logged
//        assertEquals(homepage.getWelcomeText(), "Chào Dat", "A welcome label + user first name is displayed to the user");
//
//        //Switch back to first tab, log in again
//        logInfo("Switch back to the first tab");
//        webDriver.getDriver().switchTo().window(firstWindow);
//
//        logInfo("log in with same account");
//        homepage.login("0345864246", "#Onimusha00");
//
//        //Verify that the account is still logged in 1st tab
//        assertEquals(homepage.getWelcomeText(), "Chào Dat", "User is enable to log in 2 tabs with same account");
//
//        //Add something to cart
//        logInfo("Add a product into cart");
//        webDriver.click("(//div[@class='item_sp_hasaki width_common relative'])[1]");
//        webDriver.waitToBeClickable("//div[text()='Giỏ hàng']/parent::button").click();
//
//        assertTrue(webDriver.waitToBeVisible("//div[text()='Sản Phẩm đã được thêm vào giỏ hàng thành công']").isDisplayed());
//
//        //Switch back to second tab and refresh
//        logInfo("Switch back to the second tab");
//        webDriver.getDriver().switchTo().window(secondWindow);
//
//        logInfo("Get item quantity before refreshing page");
//        int initialQty = Integer.parseInt(webDriver.getText("span.counter_number.counter"));
//
//        logInfo("Refresh the page");
//        webDriver.refreshPage();
//
//        //Verify the cart is updated
//        assertEquals(webDriver.getText("span.counter_number.counter"), String.valueOf(initialQty + 1), "The item quantity is updated.");
//    }
//
////    @Parameters({"browser", "url"})
//    @Test(dataProvider = "loginTestData", dataProviderClass = TestData.class)
//    void userLoginWithDifferentAccountsInDifferentBrowsers(Method method, Browser browser, String url) {
////        logInfo("Open the first Browser: " + browser, ExtentColor.LIME);
////        webDriver = getWebDriver(browser);
////
////
////        logInfo("------ Setup steps include ------");
////        logInfo("- Initialize relevant pages");
////        homepage = PageFactory.generateHomePage(webDriver);
////
////        logInfo("- First browser: " + browser + " - Navigate to " + url);
////        webDriver.navigate(url);
////
////        logInfo("- First browser: " + browser + " - Close popup");
////        homepage.cancelPopup();
////
////        logInfo("- First browser: " + browser + " - Reject cookie");
////        homepage.cancelCookie();
//
//
//        logInfo("Test Case: " + method.getName());
//        logInfo("Test Case: " + method.getName() + " Bien driver: " + webDriver);
//        //Start first browser
//        System.out.println("first driver: " + webDriver.getDriver());
//
//        //Log in first account
//        homepage.login("0345864246", "#Onimusha00");
//
//        //Verify it's logged
//        assertEquals(homepage.getWelcomeText(), "Chào Dat", "A welcome message is displayed to the user");
//
//        //Open 2nd browser
//        var secondDriver = DriverFactory.initWebsiteDriver(Browser.HEADLESSFIREFOX);
//        System.out.println("second driver: " + secondDriver.getDriver());
//        secondDriver.navigate("https://hasaki.vn/");
//
//        //Log in second account
//        var secondHomepage = PageFactory.generateHomePage(secondDriver);
//        secondHomepage.cancelPopup();
//        secondHomepage.cancelCookie();
//        secondHomepage.login("0796280280", "27051993@Phuong");
//
//        //Verify it's logged
//        assertEquals(secondHomepage.getWelcomeText(), "Chào Phương", "A welcome message is displayed to the user");
//    }
//
////    @Parameters({"browser", "url"})
//    @Test(dataProvider = "loginTestData", dataProviderClass = TestData.class)
//    void userLoginWithSameAccountsInDifferentBrowsers(Method method, Browser browser, String url) {
////        logInfo("Open the first Browser: " + browser, ExtentColor.LIME);
////        webDriver = getWebDriver(browser);
////
////
////        logInfo("------ Setup steps include ------");
////        logInfo("- Initialize relevant pages");
////        homepage = PageFactory.generateHomePage(webDriver);
////
////        logInfo("- First browser: " + browser + " - Navigate to " + url);
////        webDriver.navigate(url);
////
////        logInfo("- First browser: " + browser + " - Close popup");
////        homepage.cancelPopup();
////
////        logInfo("- First browser: " + browser + " - Reject cookie");
////        homepage.cancelCookie();
//
//
//        logInfo("Test Case: " + method.getName());
//        logInfo("Test Case: " + method.getName() + " Bien driver: " + webDriver);
//        //Start first browser
//        System.out.println("first driver: " + webDriver.getDriver());
//
//        //Log in first account
//        homepage.login("0345864246", "#Onimusha00");
//
//        //Verify it's logged
//        assertEquals(homepage.getWelcomeText(), "Chào Dat", "A welcome message is displayed to the user");
//
//        //Open 2nd browser
//        var secondDriver = DriverFactory.initWebsiteDriver(Browser.HEADLESSFIREFOX);
//        System.out.println("second driver: " + secondDriver.getDriver());
//        secondDriver.navigate("https://hasaki.vn/");
//
//        //Log in first account
//        var secondHomepage = PageFactory.generateHomePage(secondDriver);
//        secondHomepage.cancelPopup();
//        secondHomepage.cancelCookie();
//        secondHomepage.login("0345864246", "#Onimusha00");
//
//        //Verify it's logged in 2nd browser
//        assertEquals(secondHomepage.getWelcomeText(), "Chào Dat", "A welcome message is displayed to the user");
//    }


}
