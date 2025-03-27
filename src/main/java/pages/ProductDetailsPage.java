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
        driver.findByCss(ProductDetailsPage.INCREASE_QTY_BUTTON).click();
    }

    /**
     * Hard method to decrease qty one by one.
     */
    public void decreaseProductQty(){
        driver.findByCss(ProductDetailsPage.DECREASE_QTY_BUTTON).click();
    }

    public void addProductToCart(){
        driver.waitToBeClickableByXpath(ProductDetailsPage.ADD_TO_CART_BUTTON).click();
    }
    public void clickToCart(){
        driver.findByXpath(ProductDetailsPage.CART_BUTTON).click();
    }
}
