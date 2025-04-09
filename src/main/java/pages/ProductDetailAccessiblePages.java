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

    public void moveToHeaderItem(@NotNull String headerName) {
        driver.moveToElement(COMMON_ITEM_HEADER, headerName);
    }

    public void clickToHeaderItem(@NotNull String headerName){
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
//        moveToHeaderItem("Tài khoản");
        clickToHeaderItem("Đăng nhập");
        setTextToUsernameInput(username);
        setTextToPasswordInput(password);
        clickToLoginButton();
    }
}
