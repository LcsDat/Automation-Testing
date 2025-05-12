package cores;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import pages.*;
import reportConfig.ExtentTestManager;

import java.util.Random;

public class BaseTest {


    protected static WebsiteDriver webDriver;
    protected HomePage homepage;
    protected ProductsPage productPage;
    protected ProductDetailsPage productDetailsPage;
    protected CartPage cartPage;
    protected StoresLocationPage storesLocationPage;
    protected FAQPage faqPage;
    protected PaymentPage paymentPage;
    protected static ExtentTest extentTest;
    protected static Logger logger;

    @BeforeSuite
    void beforeSuite(Class className) {
        startTestLog(className, "User order a product on the website on Thread: " + (int) Thread.currentThread().getId(), (int) Thread.currentThread().getId());
    }

    @AfterSuite(alwaysRun = true)
    void afterSuite() {
        logInfo("- Clean background process (driver)");
        cleanDriverProcess();
    }

    public synchronized String takeScreenshot() {
        var driver = getWebDriver().getDriver();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }

    public synchronized WebsiteDriver getWebDriver() {
        return webDriver;
    }

    protected static void logInfo(String description) {
        extentTest.log(Status.INFO, MarkupHelper.createLabel(description, ExtentColor.GREY));
        logger.info(description);
    }

    protected static void logInfo(String description, ExtentColor color) {
        extentTest.log(Status.INFO, MarkupHelper.createLabel(description, color));
        logger.info(description);
    }

    /**
     * Writing log for Extent Report
     *
     * @param testClass Test Class that attach to the Test Suite name
     * @param desc      Description of the test suite
     */
    protected synchronized static void startTestLog(Class<?> testClass, String desc) {
        extentTest = ExtentTestManager.startTest(testClass.getName().split("\\.")[2].replace("_", " ") + " Test Suite",
                desc);
        logger = LogManager.getLogger(testClass);
        System.out.println("Current thread and current class: " + Thread.currentThread().getId() + " " + testClass.getName());
    }

    protected synchronized static void startTestLog(Class<?> testClass, String desc, int currentThread) {
        extentTest = ExtentTestManager.startTest(testClass.getName().split("\\.")[2].replace("_", " ") + " Test Suite",
                desc, currentThread);
        logger = LogManager.getLogger(testClass);
    }

    protected void attachScreenshot(){
        String base64Screenshot = takeScreenshot();
        extentTest.addScreenCaptureFromBase64String(base64Screenshot);
    }

    /**
     * Writing log for Extent Report
     *
     * @param testSuiteName Test Suite name
     * @param desc          Description of the test suite
     */
    protected static void startTestLog(String testSuiteName, String desc) {
        extentTest = ExtentTestManager.startTest(testSuiteName,
                desc);
    }

    protected static int getCurrentThread() {
        var currentThread = (int) Thread.currentThread().getId();
        System.out.println("Current thread of base test: " + currentThread);
        return currentThread;
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
