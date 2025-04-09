package pages;

import cores.BasePage;
import cores.WebsiteDriver;
import org.jetbrains.annotations.NotNull;

public class ProductDetailAccessiblePages extends BasePage {
    public ProductDetailAccessiblePages(WebsiteDriver driver) {
        super(driver);
    }

    private static final String COMMON_ITEM_HEADER = "//parent::div[contains(@class,'item_header')]//a[normalize-space()='%s']";
    private static final String USERNAME_INPUT = "#username";
    private static final String PASSWORD_INPUT = "#password";
    private static final String LOGIN_BUTTON = "//button[text()='Đăng nhập']";
    private static final String CATEGORY_HAMBER_MENU = "hamber_menu";
    private static final String LIST_CATEGORY_ITEM = "//a[@class='parent_menu' and normalize-space()='%s']";
    private static final String PRODUCT_TYPE = "//div[@class='col_hover_submenu ']//a[normalize-space()='%s']";

    public void moveToHeaderItem(@NotNull String headerName) {
        driver.moveToElement(COMMON_ITEM_HEADER, headerName);
    }

    public void clickToHeaderItem(@NotNull String headerName) {
        if (headerName.equals("Hệ thống cửa hàng")) headerName = "Hệ thốngcửa hàng";
        driver.click(COMMON_ITEM_HEADER, headerName);
    }

    public void setTextToUsernameInput(String value) {
        driver.setText(USERNAME_INPUT, value);
    }

    public void setTextToPasswordInput(String value) {
        driver.setText(PASSWORD_INPUT, value);
    }

    public void clickToLoginButton() {
        driver.click(LOGIN_BUTTON);
    }

    public void login(String username, String password) {
        clickToHeaderItem("Đăng nhập");
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

    public void chooseProductType(String categoryName, String productType) {
        moveToCategoryMenu();
        moveToCategoryItem(categoryName);
        clickToProductType(productType);
    }
}
