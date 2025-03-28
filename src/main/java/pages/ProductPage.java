package pages;

import cores.WebsiteDriver;

public class ProductPage {
    private WebsiteDriver driver;

    public ProductPage(WebsiteDriver driver) {
        this.driver = driver;
    }

    private static final String PRODUCT_NAME = "//h1[contains(text(),'Tẩy Trang Mặt')]" +
            "/parent::div//following-sibling::div[@class='ProductGrid__grid width_common']" +
            "//div[text()='%s']";

    public void chooseProduct(String productName){
        driver.click(ProductPage.PRODUCT_NAME, productName);
    }
}
