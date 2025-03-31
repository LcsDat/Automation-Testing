package pages;

import cores.BasePage;
import cores.Browser;
import cores.WebsiteDriver;

public class ProductPage extends BasePage {

    public ProductPage(WebsiteDriver driver) {
        super(driver);
    }

    private static final String PRODUCT_NAME = "//h1[contains(text(),'Tẩy Trang Mặt')]" +
            "/parent::div//following-sibling::div[@class='ProductGrid__grid width_common']" +
            "//div[text()='%s']";

    public void chooseProduct(String productName){
        driver.click(ProductPage.PRODUCT_NAME, productName);
    }
}
