import com.DeathByCaptcha.Captcha;
import com.DeathByCaptcha.Client;
import com.DeathByCaptcha.HttpClient;
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

import java.io.IOException;
import java.util.Base64;

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
    void test() throws Exception{
        // Put your DBC username & password or authtoken here:
        String username = "hideyashy";
        String password = "Onimusha:00";

        String filename = "C:\\Users\\GIGABYTE\\Desktop\\facebook2.png";

        // DeathByCaptcha Socket Client
        // Client client = (Client) (new SocketClient(username, password));
        // DeathByCaptcha http Client
        Client client = new HttpClient(username,password);
        client.isVerbose = true;

        try {
            try {
                System.out.println("Balance:" + client.getBalance() + " US cents");
            } catch (IOException e) {
                System.out.println("Failed fetching balance: " + e.toString());
                return;
            }
            System.out.println("1.Client: " + client.toString());
            Captcha captcha = null;
            try {
                // Upload a CAPTCHA and poll for its status
                // the captcha have 120 seconds timeout to solve.
                // refer to each captcha type example
                captcha = client.decode(filename);
                System.out.println("11: " + captcha.id);
                System.out.println("22: " + captcha.text);
                System.out.println("22: " + captcha.text);
            } catch (IOException e) {
                // uploading the captcha fails
                System.out.println("Failed uploading CAPTCHA");
                return;
            }
            if (null != captcha) {
                System.out.println(captcha.id + " solved: " + captcha.text);

                // Report incorrectly solved CAPTCHA if necessary.
                // Make sure you've checked if the CAPTCHA was in fact incorrectly
                // solved, or else you might get banned as abuser.
                //  try {
                //      if (client.report(captcha)) {
                //          System.out.println("Reported as incorrectly solved");
                //      } else {
                //          System.out.println("Reporting incorrectly solved");
                //      }
                //  } catch (IOException e) {
                //      System.out.println("Failed reporting : " + e.toString());
                //  }
            } else {
                // solving the captcha fails
                System.out.println("Failed solving CAPTCHA");
            }
        } catch (com.DeathByCaptcha.Exception e) {
            System.out.println(e);
        }

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
