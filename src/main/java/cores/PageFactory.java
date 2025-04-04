package cores;

import pages.CartPage;
import pages.HomePage;
import pages.ProductDetailsPage;
import pages.ProductsPage;

public class PageFactory {

    public static HomePage generateHomePage(WebsiteDriver driver) {
        return new HomePage(driver);
    }

    public static CartPage generateCartPage(WebsiteDriver driver) {
        return new CartPage(driver);
    }

    public static ProductsPage generateProductsPage(WebsiteDriver driver) {
        return new ProductsPage(driver);
    }

    public static ProductDetailsPage generateProductDetailsPage(WebsiteDriver  driver){
        return new ProductDetailsPage(driver);
    }
}
