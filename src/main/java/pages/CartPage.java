package pages;

import cores.WebsiteDriver;

public class CartPage {
    private WebsiteDriver driver;

    public CartPage(WebsiteDriver driver) {
        this.driver = driver;
    }

    private static final String INCREASE_QTY_BUTTON = "button[aria-label='Increase btn']";
    private static final String DECREASE_QTY_BUTTON = "button[aria-label='Descrease btn']";
    private static final String ADD_TO_CART_BUTTON = "//div[text()='Giỏ hàng']";
    private static final String CART_BUTTON = "nav[aria-label='Main'] button.p-0";
    private static final String CART_QUANTITY = "nav[aria-label='Main'] button.p-0 a span:nth-child(3)";

    /**
     * Hard method to increase qty one by one.
     */
    public void increaseProductQty(){
        driver.click(CartPage.INCREASE_QTY_BUTTON);
    }

    /**
     * Hard method to decrease qty one by one.
     */
    public void decreaseProductQty(){
        driver.click(CartPage.DECREASE_QTY_BUTTON);
    }

    public void addProductToCart(){
        driver.click(CartPage.ADD_TO_CART_BUTTON);
    }
    public void clickToCart(){
        driver.click(CartPage.CART_BUTTON);
    }

    public void checkCartQuantity(){
        if (!(driver.getText(CART_QUANTITY).equals("0"))) {
            driver.click(CART_BUTTON);

            while (true) {
                Integer size = driver.findElements("//tbody/tr").size();
                if (size != 0) {
                    driver.waitToBeClickable("//tbody/tr//button[text()='Xóa']").click();
                    driver.waitToBeInvisibleBy("div.animate-spin");
                } else break;
            }
        }
    }
}
