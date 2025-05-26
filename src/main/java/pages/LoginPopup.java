package pages;

import cores.BasePage;
import cores.WebsiteDriver;

public class LoginPopup extends BasePage {
    public LoginPopup(WebsiteDriver driver) {
        super(driver);
    }
    /*
    2 different Login popup in this web
    ---> Homepage, Prduct Page use same login popup
    ---> Product detail page use other login popup
    This class should extend Base Page
     */

    private static final String HOME_LOGIN_BY_FACEBOOK_BUTTON = "div#lg_login a.login-facebook";
    private static final String HOME_LOGIN_USERNAME_INPUT = "#username";
    private static final String HOME_LOGIN_PASSWORD_INPUT = "#password";
    private static final String HOME_LOGIN_LOGIN_BUTTON = "div#lg_login button.btn.btn_site_1";

    private static final String PDETAILS_LOGIN_BY_FACEBOOK_BUTTON = "//img[@alt='FB login image']/parent::button";
    private static final String PDETAILS_USERNAME_INPUT = "#input[name='username']";
    private static final String PDETAILS_PASSWORD_INPUT = "input[name='password']";
    private static final String PDETAILS_LOGIN_BUTTON = "div#lg_login button.btn.btn_site_1";
}
