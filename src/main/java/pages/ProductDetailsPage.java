package pages;

import cores.WebsiteDriver;

public class ProductDetailsPage {
    private WebsiteDriver driver;

    public ProductDetailsPage(WebsiteDriver driver) {
        this.driver = driver;
    }

    private static final String INCREASE_QTY_BUTTON = "button[aria-label='Increase btn']";
    private static final String DECREASE_QTY_BUTTON = "button[aria-label='Descrease btn']";
    private static final String ADD_TO_CART_BUTTON = "//div[text()='Giỏ hàng']";
    private static final String CART_BUTTON = "//span[text()='Cart Icon']//ancestor::button";

    /**
     * Hard method to increase qty one by one.
     */
    public void increaseProductQty(){
        driver.click(ProductDetailsPage.INCREASE_QTY_BUTTON);
    }

    /**
     * Hard method to decrease qty one by one.
     */
    public void decreaseProductQty(){
        driver.click(ProductDetailsPage.DECREASE_QTY_BUTTON);
    }

    public void addProductToCart(){
        driver.click(ProductDetailsPage.ADD_TO_CART_BUTTON);
    }
    public void clickToCart(){
        driver.click(ProductDetailsPage.CART_BUTTON);
    }
}
