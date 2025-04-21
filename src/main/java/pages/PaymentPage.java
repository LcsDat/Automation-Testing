package pages;

import cores.BasePage;
import cores.PageFactory;
import cores.WebsiteDriver;
import org.openqa.selenium.Keys;

public class PaymentPage extends BasePage {
    public PaymentPage(WebsiteDriver driver) {
        super(driver);
    }

    private static final String COMMON_BUTTON = "//h2[text()='%s']/following-sibling::div//button[text()='%s']";
    private static final String COMMON_ADD_NEW_ADDRESS_INPUT = "//input[@placeholder='%s']";
    private static final String COMMON_VALIDATION_MESSAGE_INPUT = COMMON_ADD_NEW_ADDRESS_INPUT + "/parent::div/following-sibling::p";
    private static final String COMMON_ADD_NEW_ADDRESS_DROPDOWN = "//button[text()='%s']";
    private static final String COMMON_VALIDATION_MESSAGE_DROPDOWN = COMMON_ADD_NEW_ADDRESS_DROPDOWN + "/following-sibling::p";
    private static final String CHANGE_ADDRESS_BUTTON = "//span[text()='Thêm địa chỉ mới']";
    private static final String CONTINUE_BUTTON = "//h2[text()='Thêm địa chỉ mới']/following-sibling::form//button[text()='Tiếp tục']";
    private static final String DROPDOWN_SEARCH_INPUT = "//input[contains(@placeholder, 'Tìm kiếm')]";
    private static final String DROPDOWN_SEARCH_INPUT_OPTION = "//div[contains(text(), '%s')]";
    private static final String STREET_NUMBER_INPUT = "button[name='address']";
    private static final String STREET_NUMBER_CONTINUE_BUTTON = "//span[text()='Sửa vị trí trên bản đồ']/parent::div//following-sibling::div//button[text()='Tiếp tục']";

    public void chooseEdit(String section, String buttonName) {
        driver.click(COMMON_BUTTON, section, buttonName);
    }

    public void clickAddNewAddress() {
        driver.click(CHANGE_ADDRESS_BUTTON);
    }


    public void setTextToNewAddressFields(String fieldName, String value) {
        driver.setText(COMMON_ADD_NEW_ADDRESS_INPUT, value, fieldName);
    }

    public void clickContinue() {
        driver.click(CONTINUE_BUTTON);
    }

    public String getCommonValidationMessageInput(String inputName) {
        return driver.getText(COMMON_VALIDATION_MESSAGE_INPUT, inputName);
    }

    public String getCommonValidationMessageDropdown(String dropdownName) {
        return driver.getText(COMMON_VALIDATION_MESSAGE_DROPDOWN, dropdownName);
    }

    private void clickDropdown(String dropdownName) {
        driver.click(COMMON_ADD_NEW_ADDRESS_DROPDOWN, dropdownName);
    }

    private void setTextDropdownSearchField(String value) {
        driver.setText(DROPDOWN_SEARCH_INPUT, value);
    }

    public void chooseCity(String cityName) {
        clickDropdown("Chọn Tỉnh/ TP, Quận/ Huyện");
        setTextDropdownSearchField(cityName);
        driver.click(DROPDOWN_SEARCH_INPUT_OPTION, cityName);
    }

    public void chooseWard(String wardName) {
        clickDropdown("Chọn Phường/ Xã");
        setTextDropdownSearchField(wardName);
        driver.click(DROPDOWN_SEARCH_INPUT_OPTION, wardName);
    }

    public void clickStreetField() {
        driver.click(STREET_NUMBER_INPUT);
    }

    public void setTextStreetField(String value) {
        driver.clear(COMMON_ADD_NEW_ADDRESS_INPUT, "Nhập vị trí của bạn");
        driver.setText(COMMON_ADD_NEW_ADDRESS_INPUT, value, "Nhập vị trí của bạn");
        driver.sendKeys(COMMON_ADD_NEW_ADDRESS_INPUT, Keys.ENTER, "Nhập vị trí của bạn");
    }

    public void clickContinueStreetNumberButton() {
        driver.click(STREET_NUMBER_CONTINUE_BUTTON);
    }

    public String getStreetNumberInputValue(String value){
        return driver.getDomAttribute(COMMON_ADD_NEW_ADDRESS_INPUT, value, "Nhập vị trí của bạn");
    }
}
