import cores.Browser;
import cores.WebsiteDriver;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

public class Demo {

    @Documented
    @interface Infor {
        String author();

        String date();

        int[] member();
    }


    @Infor(author = "Dat", date = "2004", member = {1,2})
    @Test
    public void test() throws InterruptedException {
        WebsiteDriver driver = new WebsiteDriver(Browser.CHROME);
//        driver.setImplicitWait(Duration.ofSeconds(10));
        driver.navigate("https://hasaki.vn");
        driver.click("onesignal-slidedown-cancel-button");
        driver.click("rejectCookies");
        driver.click("//div[@class='item_header']");
        Thread.sleep(2000);
        driver.switchWindow("Hỗ trợ khách hàng");
        driver.click("//div[text()='Tài khoản']/following-sibling::a");
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream("D:\\Work\\Automation\\IntelliJ\\Automation-Testing\\src\\main\\resources\\config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(properties.getProperty("base.url"));
        System.out.println(properties.getProperty("browser"));
    }

}
