package cores;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import pages.*;
import reportConfig.ExtentManager;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class BaseTest {

    //Test instances
    protected WebsiteDriver webDriver;
    protected HomePage homepage;
    protected ProductsPage productPage;
    protected ProductDetailsPage productDetailsPage;
    protected CartPage cartPage;
    protected StoresLocationPage storesLocationPage;
    protected FAQPage faqPage;
    protected PaymentPage paymentPage;
    protected ExtentTest extentTest;

    //Log instances
    protected Logger logger;
    protected Logger assertionLogger;
    private static final Marker TIME_MARKER = MarkerManager.getMarker("⏰");
    protected static ExtentReports extentReports;

    //Thread instances
    protected static final ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();
    protected static final ThreadLocal<Logger> log4j2Thread = new ThreadLocal<>();
    protected static final ThreadLocal<WebsiteDriver> webdriverThread = new ThreadLocal<>();
    protected static final ConcurrentHashMap<String, ExtentTest> extentTestMap = new ConcurrentHashMap<>();

    //Driver method ***********************************************************
    public WebsiteDriver getWebDriver(Browser browser) {
        webDriver = DriverFactory.initWebsiteDriver(browser);
        webdriverThread.set(webDriver);
        return webDriver;
    }

    //Setup method ***********************************************************
    @BeforeSuite
    public void setupReport() {
        extentReports = ExtentManager.getInstance();
    }

    @AfterSuite(alwaysRun = true)
    void afterSuite() {
        logInfo("- Clean background process (driver)");
        cleanDriverProcess();
        extentTestThread.remove();
        log4j2Thread.remove();
        webdriverThread.remove();
    }

    protected void navigateToHomePage() {
        sleepInSecond(1);
        if (webDriver.getPageTitle().startsWith("Hasaki.vn")) webDriver.click("div.logo_site");
        else webDriver.waitToBeClickable("a[aria-label='Homepage']").click();
    }

    /**
     * Default, User is navigated back in Homepage before log out.
     */
    protected void logout() {
        if (webDriver.isUnDisplayed("#btn-login")) {
            webDriver.moveToElement("div.item_header.item_login.user_login");
            webDriver.findElement("//a[contains(text(),'Thoát')]").click();
        }
    }

    protected void quitBrowser() {
        webDriver.quit();
    }

    protected void cleanDriverProcess() {
        webDriver.killDriverProcess();
    }

    protected void switchToMainWebsite() {
        webDriver.switchWindow("Hasaki.vn | Mỹ Phẩm & Clinic");
    }

    //Logging methods ***********************************************************
    protected ExtentTest createExtentLog(String suiteName) {
        logger = LogManager.getLogger(suiteName);
        extentTest = extentReports.createTest(suiteName);
        extentTestMap.put(suiteName, extentTest);
        extentTestThread.set(extentTest);
        return extentTest;
    }

    protected ExtentTest createExtentLog(Class<?> clazz) {
        logger = LogManager.getLogger(clazz.getSimpleName());
        assertionLogger = LogManager.getLogger("assertions." + clazz.getSimpleName());
        extentTest = extentReports.createTest(clazz.getSimpleName());
        extentTestMap.put(clazz.getName(), extentTest);
        extentTestThread.set(extentTest);
        return extentTest;
    }

    protected void logInfo(String description) {
        if (extentTestThread.get() != null) {
            extentTestThread.get().info(MarkupHelper.createLabel(description, ExtentColor.GREY));
        }
        logger.info(TIME_MARKER, description);
    }

    protected void logInfo(String description, boolean enableCapture) {
        if (enableCapture)
            extentTestThread.get().log(Status.INFO, MarkupHelper.createLabel(description, ExtentColor.TEAL), attachScreenshot());

        logger.info(description);
    }

    protected void logInfo(String description, ExtentColor logColor) {
        if (extentTestThread.get() != null) {
            extentTestThread.get().info(MarkupHelper.createLabel(description, logColor));
        }
        logger.info(description);
    }

    protected Media attachScreenshot() {
        extentTestThread.get().addScreenCaptureFromBase64String(webdriverThread.get().takeScreenshotBASE64());
        var mediaList = extentTestThread.get().getModel().getMedia();
        return mediaList.get(extentTestThread.get().getModel().getMedia().size() - 1);
    }

    //Util methods ***********************************************************
    protected static void sleepInSecond(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected String randomAlphabetic(int targetLength) {
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'

        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(targetLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    //Assertion methods ***********************************************************
    protected void assertTrue(boolean condition, String message) {
        try {
            CustomAssert.assertTrue(condition);
            assertionLogger.info("{} ====> PASS", message);
        } catch (Throwable e) {
            assertionLogger.error("Assert True is FAILED: {}", e.getMessage());
        }

    }

    protected void assertTrue(boolean condition) {
        try {
            CustomAssert.assertTrue(condition);
            assertionLogger.info("Assert True is PASS");
        } catch (Throwable e) {
            assertionLogger.error("Assert True is FAILED: {}", e.getMessage());
        }

    }

    protected void assertFalse(boolean condition, String message) {

        try {
            CustomAssert.assertFalse(condition);
            assertionLogger.info("{} ====> PASS", message);
        } catch (Throwable e) {
            assertionLogger.error("Assert False is FAILED: {}", e.getMessage());
        }
    }

    protected void assertFalse(boolean condition) {

        try {
            CustomAssert.assertFalse(condition);
            assertionLogger.info("Assert False is PASS");
        } catch (Throwable e) {
            assertionLogger.error("Assert False is FAILED: {}", e.getMessage());
        }
    }

    protected void assertEquals(Object actual, Object expected, String message) {
        try {
            CustomAssert.assertEquals(actual, expected);
            assertionLogger.info("{}: [Actual {}] and [Expected {}] ====> PASS", message, actual, expected);
        } catch (Throwable e) {
            assertionLogger.error("Actual: {} but Expected: {} {}", actual, expected,  e.getMessage());
        }

    }

    protected void assertEquals(Object actual, Object expected) {
        try {
            CustomAssert.assertEquals(actual, expected);
            assertionLogger.info("[Actual {}] and [Expected {}] ====> PASS" , actual, expected);
        } catch (Throwable e) {
            assertionLogger.error("Actual: {} but Expected: {} {}", actual, expected,  e.getMessage());
        }

    }

    protected boolean verifyTrue(boolean condition) {
        return new CustomAssert(GlobalVariables.HASAKI_KEYWORD).verifyTrue(condition);
    }

    protected boolean verifyTrue(boolean condition, String message) {
        return new CustomAssert(GlobalVariables.HASAKI_KEYWORD).verifyTrue(condition, message);
    }

    protected boolean verifyFalse(boolean condition) {
        return new CustomAssert(GlobalVariables.HASAKI_KEYWORD).verifyFalse(condition);
    }

    protected boolean verifyFalse(boolean condition, String message) {
        return new CustomAssert(GlobalVariables.HASAKI_KEYWORD).verifyFalse(condition, message);
    }

    protected void verifyEquals(Object actual, Object expected, String message) {
        new CustomAssert(GlobalVariables.HASAKI_KEYWORD).verifyEquals(actual, expected, message);
    }

    protected void verifyEquals(Object actual, Object expected) {
        new CustomAssert(GlobalVariables.HASAKI_KEYWORD).verifyEquals(actual, expected);
    }
}
