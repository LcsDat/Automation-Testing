package cores;

import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

public class PageFactory {

    public static HomePage generateHomepage(WebsiteDriver driver) {
        return new HomePage(driver);
    }

    public static CartPage generateCartpage(WebsiteDriver driver) {
        return new CartPage(driver);
    }

    public static ProductPage generateProductpage(WebsiteDriver driver) {
        return new ProductPage(driver);
    }
}
