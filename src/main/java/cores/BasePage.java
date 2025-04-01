package cores;

public class BasePage {
    protected WebsiteDriver driver;

    public BasePage(WebsiteDriver driver) {
        this.driver = driver;
    }

    private static final String LOGIN_DIALOG_CLOSE_BUTTON = "button[aria-label='Close notify form']";

    public void closeLoginDialog(){
        driver.click(LOGIN_DIALOG_CLOSE_BUTTON);
    }
}
