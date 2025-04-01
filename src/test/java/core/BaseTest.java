package core;

import cores.Browser;
import cores.DriverFactory;
import cores.PageFactory;
import cores.WebsiteDriver;
import org.testng.annotations.*;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

public class BaseTest {
    protected static WebsiteDriver webDriver;
    protected static HomePage homepage;
    protected static ProductPage productPage;
    protected static CartPage cartPage;

    protected static void sleepInSecond(long time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
