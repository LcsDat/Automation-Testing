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
        driver.moveToElement(Homepage.CATEGORY_HAMBER_MENU);
    }

    public void moveToCategoryItem(String categoryName){
        driver.moveToElement(Homepage.LIST_CATEGORY_ITEM, categoryName);
    }

    public void clickToProductType(String productType){
        driver.click(Homepage.PRODUCT_TYPE, productType);
    }

    public void chooseProductType(String categoryName, String productType){
        moveToCategoryMenu();
        moveToCategoryItem(categoryName);
        clickToProductType(productType);
    }

    public void moveToHeaderLoginItem() {
        driver.moveToElement(Homepage.HEADER_LOGIN_ITEM);
    }

    public void clickToLoginLink() {
        driver.click(Homepage.LOGIN_LINK);
    }

    public void clickToLoginButton() {
        driver.click(Homepage.LOGIN_BUTTON);
    }

    public void setTextToUsernameInput(String value) {
        driver.setText(Homepage.USERNAME_INPUT, value);
    }

    public void setTextToPasswordInput(String value) {
        driver.setText(Homepage.PASSWORD_INPUT, value);
    }

    public void login(String username, String password) {
//        driver.waitToBeClickable(HEADER_LOGIN_ITEM);
        moveToHeaderLoginItem();

//        driver.click(HEADER_LOGIN_ITEM);
        clickToLoginLink();
        setTextToUsernameInput(username);
        setTextToPasswordInput(password);
        clickToLoginButton();
    }

    public void cancelPopup() {
        driver.click(Homepage.POPUP_CANCEL_BUTTON);
    }

    public void cancelCookie() {
        driver.click(Homepage.COOKIES_CANCEL_BUTTON);
    }
}
