package pages;

import cores.BasePage;
import cores.WebsiteDriver;

/**
 * A class which keep common elements in Homepage and ProductPage
 */
public class HomeProductCommons extends BasePage {
    public HomeProductCommons(WebsiteDriver driver) {
        super(driver);
    }

    private static final String HOME_LOGIN_BY_FACEBOOK_BUTTON = "div#lg_login a.login-facebook";
    private static final String HOME_LOGIN_USERNAME_INPUT = "#username";
    private static final String HOME_LOGIN_PASSWORD_INPUT = "#password";
    private static final String HOME_LOGIN_LOGIN_BUTTON = "div#lg_login button.btn.btn_site_1";

    private static final String WELCOME_USER_LABEL = "a[class='text_1_header']";
    private static final String WARNING_LOGIN_MESSAGE = "div.alert.alert-danger";
    private static final String CATEGORY_HAMBER_MENU = "hamber_menu";
    private static final String LIST_CATEGORY_ITEM = "//a[@class='parent_menu' and normalize-space()='%s']";
    private static final String PRODUCT_TYPE = "//div[@class='col_hover_submenu ']//a[normalize-space()='%s']";

    public String getWarningMessage() {
        return driver.getText(WARNING_LOGIN_MESSAGE);
    }

    public String getWelcomeText() {
        return driver.getText(WELCOME_USER_LABEL);
    }

    public void setTextToUsernameInput(String value) {
        driver.setText(HOME_LOGIN_USERNAME_INPUT, value);
    }

    public void setTextToPasswordInput(String value) {
        driver.setText(HOME_LOGIN_PASSWORD_INPUT, value);
    }

    //This is from ProducDetailsPage, will refactor later
    private static final String LOGIN_BUTTON = "//button[text()='Đăng nhập']";

    public void clickToLoginButton() {
        driver.click(HOME_LOGIN_LOGIN_BUTTON);
    }

    public void login(String username, String password) {
        driver.waitToBeClickable("a#btn-login").click();
        setTextToUsernameInput(username);
        setTextToPasswordInput(password);
        clickToLoginButton();
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

    /**
     * Method to find a specific product type
     *
     * @param categoryName option in Category (parent) menu
     * @param productType  option in sub-menu
     */
    public void chooseProductType(String categoryName, String productType) {
        moveToCategoryMenu();
        moveToCategoryItem(categoryName);
        clickToProductType(productType);
    }

    /**
     * Method to navigate a specific category, which will show product types of a category
     *
     * @param categoryName option in Category (parent) menu
     */
    public void chooseCategory(String categoryName) {
        moveToCategoryMenu();
        driver.click(categoryName);
    }
}
