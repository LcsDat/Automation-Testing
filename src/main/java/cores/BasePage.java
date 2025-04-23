package cores;

public class BasePage {
    protected WebsiteDriver driver;

    public BasePage(WebsiteDriver driver) {
        this.driver = driver;
    }

    private static final String LOGIN_DIALOG_CLOSE_BUTTON = "button[aria-label='Close notify form']";

    public Boolean waitToBeInvisible(String locator) {
        return driver.waitToBeInvisible(locator);
    }

    public Boolean waitToBeInvisible(String locator, String... varargs) {
        return driver.waitToBeInvisible(locator, varargs);
    }

    public WebsiteElement waitToBeClickable(String locator) {
        return driver.waitToBeClickable(locator);
    }

    public WebsiteElement waitToBeClickable(String locator, String... varargs) {
        return driver.waitToBeClickable(locator, varargs);
    }

    public WebsiteElement waitToBeVisible(String locator) {
        return driver.waitToBeVisible(locator);
    }

    public WebsiteElement waitToBeVisible(String locator, String... varargs) {
        return driver.waitToBeVisible(locator,varargs);
    }

    public void closeLoginDialog() {
        driver.click(LOGIN_DIALOG_CLOSE_BUTTON);
    }

    public void switchWindow(String titleContains) {
        driver.switchWindow(titleContains);
    }

    public String getPageTitle(){
        return driver.getPageTitle();
    }

    protected static void sleepInSecond(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
