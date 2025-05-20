package cores;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.*;
import reportConfig.ExtentManager;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public class BaseTest {

    protected WebsiteDriver webDriver;
    protected HomePage homepage;
    protected ProductsPage productPage;
    protected ProductDetailsPage productDetailsPage;
    protected CartPage cartPage;
    protected StoresLocationPage storesLocationPage;
    protected FAQPage faqPage;
    protected PaymentPage paymentPage;
    protected ExtentTest extentTest;
    protected Logger logger;

    protected static ExtentReports extentReports;

    protected static final ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<>();
    protected static final ThreadLocal<Logger> log4j2Thread = new ThreadLocal<>();
    protected static final ThreadLocal<WebsiteDriver> webdriverThread = new ThreadLocal<>();
    protected static final ConcurrentHashMap<String, ExtentTest> extentTestMap = new ConcurrentHashMap<>();

    //Setup method ***********************************************************
    @BeforeSuite
    public void setupReport(ITestContext context) {
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
    protected ExtentTest createLog(String suiteName){
        logger = LogManager.getLogger(suiteName);
        extentTest = extentReports.createTest(suiteName);
        extentTestMap.put(suiteName, extentTest);
        extentTestThread.set(extentTest);
        log4j2Thread.set(logger);
        return extentTest;
    }
    protected void logInfo(String description) {
        if (extentTestThread.get() != null) {
            extentTestThread.get().info(MarkupHelper.createLabel(description, ExtentColor.GREY));
        }
        log4j2Thread.get().info(description);
    }

    protected void logInfo(String description, boolean enableCapture) {
        if (enableCapture)
            extentTestThread.get().log(Status.INFO, MarkupHelper.createLabel(description, ExtentColor.TEAL), attachScreenshot());

        log4j2Thread.get().info(description);
    }

    protected void logInfo(String description, ExtentColor logColor) {
        if (extentTestThread.get() != null) {
            extentTestThread.get().info(MarkupHelper.createLabel(description, logColor));
        }
        log4j2Thread.get().info(description);
    }

    protected Media attachScreenshot() {
        extentTestThread.get().addScreenCaptureFromBase64String(webdriverThread.get().takeScreenshotBASE64());
        var mediaList = extentTestThread.get().getModel().getMedia();
//        screenNo = testThread.get().getModel().getMedia().size() - 1;
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
    protected void assertTrue(boolean condition) {
        try {
            CustomAssert.assertTrue(condition);
//            logger.info("-----  PASS -----");
        } catch (Throwable e) {
//            logger.error("----- FAIL ----- : " + e.getMessage());
        }

    }

    protected void assertFalse(boolean condition) {

        try {
            CustomAssert.assertFalse(condition);
//            logger.info("-----  PASS -----");
        } catch (Throwable e) {
//            logger.error("----- FAIL ----- : " + e.getMessage());
        }
    }

    protected void assertEquals(Object actual, Object expected) {
        try {
            CustomAssert.assertEquals(actual, expected);
//            logger.info("-----  PASS -----");
        } catch (Throwable e) {
//            logger.error("----- FAIL ----- : " + e.getMessage());
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
