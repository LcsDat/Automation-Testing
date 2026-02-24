package pages;

import cores.BasePage;
import cores.WebsiteDriver;

public class AccountPage extends BasePage {
    public AccountPage(WebsiteDriver driver) {
        super(driver);
    }

    private static final String ACCOUNT_EDIT_DIRECTLY = "//a[normalize-space()='Chỉnh sửa tài khoản']";
    private static final String PROFILE_INFORMATION_ITEM = "//div[@class='menu_profile width_common']/a[normalize-space()='Thông tin tài khoản']";
    private static final String EDIT_PROFILE_BUTTON_DEFAULT = "//a[normalize-space()='Chỉnh sửa']";
    private static final String YEAR_DROPDOWN = "select#popup-year";
    private static final String UPDATE_BUTTON = "button.btn_update";

    public void clickToEditAccount() {
        driver.click(ACCOUNT_EDIT_DIRECTLY);
    }

    public void clickToEdit() {
        driver.click(EDIT_PROFILE_BUTTON_DEFAULT);
    }public void clickToAccountInformation() {
        driver.click(PROFILE_INFORMATION_ITEM);
    }

    public void updateInformation() {
        driver.click(UPDATE_BUTTON);
    }

    public void selectYearOfBirth(String yearOfBirth) {
        driver.defaultSelectByVisibleText(YEAR_DROPDOWN, yearOfBirth);
    }
}
