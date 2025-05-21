import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import cores.*;
import org.apache.logging.log4j.LogManager;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import reportConfig.ExtentTestManager;

public class Demo1 extends DemoParent {
    WebsiteDriver driver;
    HomePage homePage;
    static ExtentTest extentTest;

//    static {
//        star(Demo1.class.getName() + " Test Suite",
//                "hello 1");
//    }

//    @BeforeClass
//    void beforeTest() {
//        System.out.println("extent variable: " + extentTest.toString());
//        logger = LogManager.getLogger(Demo1.class);
//
//        System.out.println("Current Thread: " + Thread.currentThread().getId());
//
//        extentTest.log(Status.INFO, MarkupHelper.createLabel("demo1 extent log", ExtentColor.GREY));
//        logger.info("demo1 extent log4j2 log");
//        driver = DriverFactory.initWebsiteDriver(Browser.HEADLESSCHROME);
//        driver.navigate("https://hasaki.vn/");
//        homePage = PageFactory.generateHomePage(driver);
//        homePage.cancelPopup();
//        homePage.cancelCookie();
//    }

    @Test
    void test() {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter(GlobalVariables.PROJECTPATH + "/extentV5/Hasaki2.html");
        extent.attachReporter(spark);
        ExtentTest test = extent.createTest(Feature.class, "hello")
                .log(Status.PASS, "This is a logging event for MyFirstTest, and it passed!");
        test.createNode("hello 1").pass("pass");
        test.createNode("hello 2")
                .fail("fail");
        test.createNode("hello 3")
                .info("info");
        System.out.println(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
        test.fail(MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
        extent.flush();

    }

    public static void main(String[] args) {
        String icon = "  /\\_/\\\n" +
                " ( o.o )\n" +
                "  > ^ <";
        System.out.println((char) 14);
    }
//    @AfterTest
//    void afterTest() {
//        driver.quit();
//    }
}
