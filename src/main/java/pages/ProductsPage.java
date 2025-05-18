package pages;

import cores.BasePage;
import cores.WebsiteDriver;

public class ProductsPage extends ProductDetailAccessiblePages {

    public ProductsPage(WebsiteDriver driver) {
        super(driver);
    }

    private static final String PRODUCT_NAME = "//div[text()=\"%s\"]//ancestor::div[@class='ProductGridItem__itemOuter']";

    public void chooseProduct(String productName){
        driver.click(ProductsPage.PRODUCT_NAME, productName);
    }

    public void chooseProductOnFirefox(String productName){
        driver.doubleClickByActions(ProductsPage.PRODUCT_NAME, productName);
    }

}
