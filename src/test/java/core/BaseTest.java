package core;

import cores.WebsiteDriver;
import pages.*;

public class BaseTest {
    protected WebsiteDriver webDriver;
    protected HomePage homepage;
    protected ProductsPage productPage;
    protected ProductDetailsPage productDetailsPage;
    protected CartPage cartPage;
    protected StoresLocationPage storesLocationPage;
    protected FAQPage faqPage;

    private final static String HASAKI_KEYWORD = "hasaki";

    protected static void sleepInSecond(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
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
        return new CustomAssert(HASAKI_KEYWORD).verifyTrue(condition);
    }

    protected boolean verifyTrue(boolean condition, String message) {
        return new CustomAssert(HASAKI_KEYWORD).verifyTrue(condition, message);
    }

    protected boolean verifyFalse(boolean condition) {
        return new CustomAssert(HASAKI_KEYWORD).verifyFalse(condition);
    }

    protected boolean verifyFalse(boolean condition, String message) {
        return new CustomAssert(HASAKI_KEYWORD).verifyFalse(condition, message);
    }

    protected void verifyEquals(Object actual, Object expected, String message) {
        new CustomAssert(HASAKI_KEYWORD).verifyEquals(actual, expected, message);
    }

    protected void verifyEquals(Object actual, Object expected) {
        new CustomAssert(HASAKI_KEYWORD).verifyEquals(actual, expected);
    }
}
