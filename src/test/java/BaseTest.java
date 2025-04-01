import cores.Browser;
import cores.DriverFactory;
import cores.PageFactory;
import cores.WebsiteDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

public class BaseTest {
    protected WebsiteDriver webDriver;
    protected HomePage homepage;
    protected ProductPage productPage;
    protected CartPage cartPage;

    @BeforeTest
    void setup() throws InterruptedException {
        webDriver = DriverFactory.initWebsiteDriver(Browser.CHROME);
        homepage = PageFactory.generateHomepage(webDriver);
        productPage = PageFactory.generateProductpage(webDriver);
        cartPage = PageFactory.generateCartpage(webDriver);

        webDriver.navigate("https://hasaki.vn/");

        homepage.cancelPopup();
        homepage.cancelCookie();
//        homepage.login("0345864246", "#Onimusha00");
//        homepage.checkCartQuantity();
    }

    @AfterMethod
    void afterMethod() {
        if (webDriver.getPageTitle().startsWith("Hasaki.vn")) webDriver.click("div.logo_site");
        else webDriver.click("a[aria-label='Homepage']");
    }

    @AfterClass()
    void afterClass() {
        cartPage.checkCartQuantity();
        webDriver.moveToElement("//nav[@aria-label='Main']//li[1]");
        webDriver.findElement("//span[text()='Thoát']").click();
        webDriver.quit();
    }

    @AfterTest(alwaysRun = true)
    void afterTest() {
        webDriver.killDriverProcess();
    }

    protected static void sleepInSecond(long time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
