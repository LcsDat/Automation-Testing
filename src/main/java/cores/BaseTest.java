package cores;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import pages.*;
import reportConfig.ExtentTestManager;

import java.util.Random;

public class BaseTest {

    protected WebsiteDriver webDriver;
    protected HomePage homepage;
    protected ProductsPage productPage;
    protected ProductDetailsPage productDetailsPage;
    protected CartPage cartPage;
    protected StoresLocationPage storesLocationPage;
    protected FAQPage faqPage;
    protected PaymentPage paymentPage;
    protected static ExtentTestManager extentTestManager = ExtentTestManager.init();
    protected ExtentTest extentTest;
    protected Logger logger;

    protected String testClass;

    public static int getScreenNo() {
        return screenNo;
    }

    private static int screenNo;

    @AfterSuite(alwaysRun = true)
    void afterSuite() {
        logInfo("- Clean background process (driver)");
        cleanDriverProcess();
    }

    private String getScreenshotBASE64(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    public WebsiteDriver getWebDriver() {
        return webDriver;
    }

    protected void logInfo(String description) {
        extentTestManager.getExtentTest(testClass).info(MarkupHelper.createLabel(description, ExtentColor.GREY));
        logger.info(description);
    }

    protected void logInfo(String description, boolean enableCapture, WebDriver driver) {
//        var mediaList = extentTest.getModel().getMedia();
//        screenNo = mediaList.size() - 1;
        if (enableCapture)
            extentTestManager.getExtentTest(testClass).log(Status.INFO, MarkupHelper.createLabel(description, ExtentColor.TEAL), attachScreenshot(driver));

        logger.info(description);
//         extentTestManager.getTest(testClass).log(Status.INFO, "Screenshot ", attachScreenshot(testClass));
    }

    protected void logInfo(String description, ExtentColor logColor) {
        extentTestManager.getExtentTest(testClass).info(MarkupHelper.createLabel(description, logColor));
        logger.info(description);
    }

    /**
     * Writing log for Extent Report
     *
     * @param testClass Test Class that attach to the Test Suite name
     * @param desc      Description of the test suite
     */
    protected ExtentTest startTestLog(String desc) {
        logger = LogManager.getLogger(testClass);
        return extentTestManager.startTest(testClass, desc);
    }

    private ExtentTest getExtentTest() {
        return extentTestManager.getMap().get(testClass);
    }

    protected Media attachScreenshot(WebDriver driver) {
        getExtentTest().addScreenCaptureFromBase64String(getScreenshotBASE64(driver));
        var mediaList = extentTestManager.getExtentTest().getModel().getMedia();
        screenNo = extentTestManager.getExtentTest().getModel().getMedia().size() -1;
        return mediaList.get(screenNo);
    }

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

    protected void navigateToHomePage() {
        sleepInSecond(1);
        if (webDriver.getPageTitle().startsWith("Hasaki.vn")) webDriver.click("div.logo_site");
        else webDriver.click("a[aria-label='Homepage']");
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

    protected void assertTrue(boolean condition) {
        try {
            CustomAssert.assertTrue(condition);
            logger.info("-----  PASS -----");
        } catch (Throwable e) {
            logger.error("----- FAIL ----- : " + e.getMessage());
        }

    }

    protected void assertFalse(boolean condition) {

        try {
            CustomAssert.assertFalse(condition);
            logger.info("-----  PASS -----");
        } catch (Throwable e) {
            logger.error("----- FAIL ----- : " + e.getMessage());
        }
    }

    protected void assertEquals(Object actual, Object expected) {
        try {
            CustomAssert.assertEquals(actual, expected);
            logger.info("-----  PASS -----");
        } catch (Throwable e) {
            logger.error("----- FAIL ----- : " + e.getMessage());
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
