package pages;

import cores.WebsiteDriver;

public class Homepage {
    private WebsiteDriver driver;

    public Homepage(WebsiteDriver driver) {
        this.driver = driver;
    }

    private static final String HEADER_LOGIN_ITEM = "div.item_header.item_login ";
    private static final String LOGIN_LINK = "//a[text()='Đăng nhập' and @id='hskLoginButton']";
    private static final String USERNAME_INPUT = "#username";
    private static final String PASSWORD_INPUT = "#password";
    private static final String LOGIN_BUTTON = "//button[text()='Đăng nhập']";
    private static final String POPUP_CANCEL_BUTTON = "onesignal-slidedown-cancel-button";
    private static final String COOKIES_CANCEL_BUTTON = "rejectCookies";

    public void moveToHeaderLoginItem() {
        driver.moveToElementByCss(Homepage.HEADER_LOGIN_ITEM);
    }

    public void clickToLoginLink() {
        driver.findByXpath(Homepage.LOGIN_LINK).click();
    }

    public void clickToLoginButton() {
        driver.findByXpath(Homepage.LOGIN_BUTTON).click();
    }

    public void setTextToUsernameInput(String value) {
        driver.findByCss(Homepage.USERNAME_INPUT).sendKeys(value);
    }

    public void setTextToPasswordInput(String value) {
        driver.findByCss(Homepage.PASSWORD_INPUT).sendKeys(value);
    }

    public void login(String username, String password) {
        moveToHeaderLoginItem();
        clickToLoginLink();
        setTextToUsernameInput(username);
        setTextToPasswordInput(password);
        clickToLoginButton();
    }

    public void cancelPopup() {
        driver.findByID(Homepage.POPUP_CANCEL_BUTTON).click();
    }

    public void cancelCookie() {
        driver.findByID(Homepage.COOKIES_CANCEL_BUTTON).click();
    }
}
