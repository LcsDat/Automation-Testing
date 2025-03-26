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
    private static final String CATEGORY_HAMBER_MENU = "hamber_menu";
    private static final String LIST_CATEGORY_ITEM = "//a[@class='parent_menu' and contains(text(),'%s')]";
    private static final String PRODUCT_TYPE = "//div[@class='col_hover_submenu ']//a[text()='%s']";

    public void moveToCategoryMenu(){
        driver.moveToElementByID(Homepage.CATEGORY_HAMBER_MENU);
    }

    public void moveToCategoryItem(String categoryName){
        driver.moveToElementByXpath(Homepage.LIST_CATEGORY_ITEM, categoryName);
    }

    public void clickToProductType(String productType){
        driver.clickByXpath(Homepage.PRODUCT_TYPE, productType);
    }

    public void chooseProductType(String categoryName, String productType){
        moveToCategoryMenu();
        moveToCategoryItem(categoryName);
        clickToProductType(productType);
    }

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
