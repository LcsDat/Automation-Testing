package pages;

import cores.BasePage;
import cores.WebsiteDriver;

public class ProductDetailsPage extends BasePage {

    public ProductDetailsPage(WebsiteDriver driver) {
        super(driver);
    }

    private static final String INCREASE_QTY_BUTTON = "button[aria-label='Increase btn']";
    private static final String ADD_TO_CART_BUTTON = "//div[text()='Giỏ hàng']";
    private static final String DECREASE_QTY_BUTTON = "button[aria-label='Descrease btn']";
    private static final String CART_BUTTON = "//span[text()='Cart Icon']/ancestor::button";

    /**
     * Hard method to increase qty one by one.
     */
    public void increaseProductQty(){
        driver.waitToBeClickable(INCREASE_QTY_BUTTON).click();
    }

    public void addProductToCart(){
        driver.click(ADD_TO_CART_BUTTON);
    }

    /**
     * Hard method to decrease qty one by one.
     */
    public void decreaseProductQty(){
        driver.click(DECREASE_QTY_BUTTON);
    }

    public void clickToCart(){
        driver.click(CART_BUTTON);
    }
}

