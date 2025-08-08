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
        return driver.waitToBeVisible(locator, varargs);
    }

    public void closeLoginDialog() {
        driver.click(LOGIN_DIALOG_CLOSE_BUTTON);
    }

    public void switchWindowByTitle(String titleContains) {
        driver.switchWindowByTitle(titleContains);
    }

    public void refreshPage(){
        driver.refreshPage();
    }

    public String getPageTitle() {
        return driver.getPageTitle();
    }

    protected static void sleepInSecond(long time) {
        try {
            Thread.sleep(time * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isDisplayed(String locator) {
        return driver.isDisplayed(locator);
    }

    public boolean isDisplayed(String locator, String... varargs) {
        return driver.isDisplayed(locator, varargs);
    }

    public void waitForPageLoad() {
        driver.waitForPageLoad();
    }
}
