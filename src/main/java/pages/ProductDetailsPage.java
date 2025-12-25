package pages;

import cores.BasePage;
import cores.WebsiteDriver;
import org.openqa.selenium.Keys;

public class ProductDetailsPage extends BasePage {

    public ProductDetailsPage(WebsiteDriver driver) {
        super(driver);
    }

    private static final String INCREASE_QTY_BUTTON = "button[aria-label='Increase btn']";
    private static final String ADD_TO_CART_BUTTON = "//div[text()='Giỏ hàng']";
    private static final String DECREASE_QTY_BUTTON = "button[aria-label='Descrease btn']";
    private static final String CART_BUTTON = "//span[text()='Cart Icon']/ancestor::button";
    private static final String QUANTITY_INPUT = "input[name='qty']";

    /**
     * Hard method to increase qty one by one.
     */
    public void increaseProductQty(){
        driver.waitToBeClickable(INCREASE_QTY_BUTTON).click();
    }

    /**
     * Dynamic method
     */
    public void increaseProductQty(int quantity) {
        /* Mặc định số lượng 1
         * Khi đổi số lượng vd 2 -> điền 2 sau số 1 -> xoá số 1
         * Với số lượng hàng chục, thêm số 0 ở phía sau
         * Khi muốn thay đổi lượng hàng chục, vd lúc đầu 10 và muốn đổi thành 20 -> điền 2 trước 1 -> xoá 1
         * Không đặt hàng số lượng quá 100 (3 con số)
         */

        String quantityS = String.valueOf(quantity);

        //Check quantity >= 100
        if(quantity >= 100 || quantity <= 0) throw new IllegalArgumentException("User input an un-acceptable amount");
        else {
            if(quantity % 10 == 0) {
                if (quantity == 10)  {
                    driver.click(QUANTITY_INPUT);
                    driver.sendKeys(QUANTITY_INPUT, Keys.CONTROL, Keys.END);
                    driver.setText(QUANTITY_INPUT,"0");
                } else {
                    int tensPlaceNumber = quantity/10;

                    driver.click(QUANTITY_INPUT);
                    driver.sendKeys(QUANTITY_INPUT, Keys.CONTROL, Keys.END);
                    driver.setText(QUANTITY_INPUT,String.valueOf(quantityS.charAt(0)));
                    driver.sendKeys(QUANTITY_INPUT, Keys.LEFT);
                    driver.sendKeys(QUANTITY_INPUT, Keys.BACK_SPACE);
                    driver.sendKeys(QUANTITY_INPUT, Keys.RIGHT);
                    driver.setText(QUANTITY_INPUT,"0");
                }
            } else {
                if (quantity == 1) System.out.println("Default quantity, no need to modify quantity");
                else if (quantity <10) {
                    driver.click(QUANTITY_INPUT);
                    driver.sendKeys(QUANTITY_INPUT, Keys.CONTROL, Keys.END);
                    driver.setText(QUANTITY_INPUT,String.valueOf(quantity));
                    driver.sendKeys(QUANTITY_INPUT, Keys.LEFT);
                    driver.sendKeys(QUANTITY_INPUT, Keys.BACK_SPACE);
                } else {
                    driver.click(QUANTITY_INPUT);
                    driver.sendKeys(QUANTITY_INPUT, Keys.CONTROL, Keys.END);
                    driver.setText(QUANTITY_INPUT,String.valueOf(quantityS.charAt(0)));
                    driver.sendKeys(QUANTITY_INPUT, Keys.LEFT);
                    driver.sendKeys(QUANTITY_INPUT, Keys.BACK_SPACE);
                    driver.sendKeys(QUANTITY_INPUT, Keys.CONTROL, Keys.END);
                    driver.setText(QUANTITY_INPUT,String.valueOf(quantityS.charAt(1)));
                }
            }
        }
    }

    //Set quantity by demand
    public void increaseProductQty(String quantity){
        // Quantity < 10
        if(Integer.parseInt(quantity) < 10) {
            driver.waitToBeClickable(QUANTITY_INPUT).click();
            driver.setText(QUANTITY_INPUT, quantity);
            driver.sendKeys(QUANTITY_INPUT, Keys.ARROW_LEFT);
            driver.sendKeys(QUANTITY_INPUT, Keys.BACK_SPACE);
        }
        // 10 <= Quantity < 100
        else if (Integer.parseInt(quantity) < 100) {
            driver.waitToBeClickable(QUANTITY_INPUT).click();
            driver.setText(QUANTITY_INPUT, "1");
            driver.sendKeys(QUANTITY_INPUT, Keys.ARROW_LEFT);
            driver.sendKeys(QUANTITY_INPUT, Keys.BACK_SPACE);
            driver.setText(QUANTITY_INPUT, String.valueOf(quantity.charAt(0)));
            driver.sendKeys(QUANTITY_INPUT, Keys.ARROW_RIGHT);
            driver.sendKeys(QUANTITY_INPUT, Keys.BACK_SPACE);
            driver.setText(QUANTITY_INPUT, String.valueOf(quantity.charAt(1)));
        } else {
            System.out.println("Quantity is exceed to maximum. Set default quantity is 11");
            driver.setText(QUANTITY_INPUT, "1");
        }
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

