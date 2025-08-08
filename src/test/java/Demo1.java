import cores.Browser;
import cores.DriverFactory;
import cores.WebsiteDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.stream.Collectors;

public class Demo1 {

    public static void main(String[] args) throws InterruptedException {
        var driver = DriverFactory.initWebsiteDriver(Browser.CHROME);
        driver.navigate("https://10fastfingers.com/typing-test/vietnamese");
        var texts = driver.findElements("//div[@id='row1']//span")
                .stream()
                .map(e -> e.getAttribute("innerText"))
                .toList();

        String string1 = String.join(" ", texts.subList(0, 50)) + " ";
        String string2 = String.join(" ", texts.subList(50, 100)) + " ";
        String string3 = String.join(" ", texts.subList(100, 150)) + " ";
        String string4 = String.join(" ", texts.subList(150, 200)) + " ";

        driver.setText("inputfield", string1);
        System.out.println("doan 1:" + string1);
        Thread.sleep(3000);
        driver.setText("inputfield", string2);
        System.out.println("doan 2:" + string2);
        Thread.sleep(3000);
        driver.setText("inputfield", string3);
        System.out.println("doan 3:" + string3);
        Thread.sleep(3000);
        driver.setText("inputfield", string4);
        System.out.println("doan 3:" + string3);

    }

}
