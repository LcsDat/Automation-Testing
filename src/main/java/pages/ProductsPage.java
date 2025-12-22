package pages;

import cores.WebsiteDriver;
import org.openqa.selenium.NoSuchElementException;

public class ProductsPage extends HomeProductCommons {

    public ProductsPage(WebsiteDriver driver) {
        super(driver);
    }

    private static final String PRODUCT_NAME = "//div[text()=\"%s\"]//ancestor::div[@class='ProductGridItem__itemOuter']";
    private static final String PRODUCT_NAME_2nd = "//h2[text()=\"%s\"]";

    public void chooseProduct(String productName){
        try {
            driver.click(ProductsPage.PRODUCT_NAME, productName);
        } catch (NoSuchElementException e){
            System.out.printf("\n First locator " + PRODUCT_NAME + " is fail. Try the second \n", productName);
            driver.click(PRODUCT_NAME_2nd, productName);
        }
    }

    public void chooseProductOnFirefox(String productName){
        driver.doubleClickByActions(ProductsPage.PRODUCT_NAME, productName);
    }

}
