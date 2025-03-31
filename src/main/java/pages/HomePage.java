package pages;

import cores.BasePage;
import cores.Browser;
import cores.WebsiteDriver;

public class HomePage extends BasePage {

    public HomePage(WebsiteDriver driver) {
        super(driver);
    }

    private static final String HEADER_LOGIN_ITEM = "div.item_header.item_login";
    private static final String LOGIN_LINK = "//a[text()='Đăng nhập' and @id='hskLoginButton']";
    private static final String USERNAME_INPUT = "#username";
    private static final String PASSWORD_INPUT = "#password";
    private static final String LOGIN_BUTTON = "//button[text()='Đăng nhập']";
    private static final String POPUP_CANCEL_BUTTON = "onesignal-slidedown-cancel-button";
    private static final String COOKIES_CANCEL_BUTTON = "rejectCookies";
    private static final String CATEGORY_HAMBER_MENU = "hamber_menu";
    private static final String LIST_CATEGORY_ITEM = "//a[@class='parent_menu' and contains(text(),'%s')]";
    private static final String PRODUCT_TYPE = "//div[@class='col_hover_submenu ']//a[text()='%s']";
    private static final String IN_CART_QUANTITY = "span.counter_number";
    private static final String CART_BUTTON = "span.counter_number";
    private static final String HOMEPAGE_LINK = "//a[@aria-label='Homepage']";

    /**
     * - This method is checking the cart quantity <b style='color:yellow'>AFTER</b> log in to the page, for refresh test setup
     * <p>
     * - The <b style='color:yellow'>CURRENT</b> user position is in HomePage
     * <p>
     * - If the quantity is bigger than 0, delete all products in the cart
     */
    public void checkCartQuantity() {
        if (!getCurrentCartQuantity().equals("0")) {
            clickToCart();
            while (true) {
                Integer size = driver.findElements("//tbody/tr").size();
                if (size != 0) {
                    driver.waitToBeClickable("//tbody/tr//button[text()='Xóa']").click();
                    driver.waitToBeInvisibleBy("div.animate-spin");
                } else break;
            }
            navigateToHomepage();
        }
    }


    public void navigateToHomepage() {
        driver.click(HOMEPAGE_LINK);
    }

    public String getCurrentCartQuantity() {
        return driver.getText(IN_CART_QUANTITY);
    }

    public void clickToCart() {
        driver.click(CART_BUTTON);
    }

    public void moveToCategoryMenu() {
        driver.moveToElement(CATEGORY_HAMBER_MENU);
    }

    public void moveToCategoryItem(String categoryName) {
        driver.moveToElement(LIST_CATEGORY_ITEM, categoryName);
    }

    public void clickToProductType(String productType) {
        driver.click(PRODUCT_TYPE, productType);
    }

    public void chooseProductType(String categoryName, String productType) {
        moveToCategoryMenu();
        moveToCategoryItem(categoryName);
        clickToProductType(productType);
    }

    public void moveToHeaderLoginItem() {
        driver.moveToElement(HEADER_LOGIN_ITEM);
    }

    public void clickToLoginLink() {
        driver.click(LOGIN_LINK);
    }

    public void clickToLoginButton() {
        driver.click(LOGIN_BUTTON);
    }

    public void setTextToUsernameInput(String value) {
        driver.setText(USERNAME_INPUT, value);
    }

    public void setTextToPasswordInput(String value) {
        driver.setText(PASSWORD_INPUT, value);
    }

    public void login(String username, String password) {
        moveToHeaderLoginItem();
        clickToLoginLink();
        setTextToUsernameInput(username);
        setTextToPasswordInput(password);
        clickToLoginButton();
    }

    public void cancelPopup() {
        driver.click(POPUP_CANCEL_BUTTON);
    }

    public void cancelCookie() {
        driver.click(COOKIES_CANCEL_BUTTON);
    }
}
