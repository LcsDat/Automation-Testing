import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import cores.Browser;
import cores.DriverFactory;
import cores.PageFactory;
import cores.WebsiteDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import reportConfig.ExtentManager;
import reportConfig.ExtentTestManager;

public class Demo2 {
    WebsiteDriver driver;
    HomePage homePage;
    protected  static ExtentTest extentTest;
    protected static Logger logger;

    @BeforeClass
    void beforeTest() {
        extentTest = ExtentTestManager.startTest(Demo2.class.getName().split("\\.")[0].replace("_", " "), "demo 2");
        System.out.println(extentTest);
        logger = LogManager.getLogger(Demo1.class);

        System.out.println("Current Thread: " + Thread.currentThread().getId());

        extentTest.log(Status.INFO, MarkupHelper.createLabel("demo1 extent log", ExtentColor.GREY));
        logger.info("demo1 extent log4j2 log");
        driver = DriverFactory.initWebsiteDriver(Browser.HEADLESSEDGE);
        driver.navigate("https://hasaki.vn/");
        homePage = PageFactory.generateHomePage(driver);
        homePage.cancelPopup();
        homePage.cancelCookie();
    }

    @Test
    void test(){
        homePage.setTextToSearch("hello world");
        Assert.assertEquals(homePage.getPageTitle(), "Hasaki.vn | Mỹ Phẩm & Clinic");
    }

    @AfterTest
    void afterTest(){
        driver.quit();
    }
}
