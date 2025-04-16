import core.BaseTest;
import cores.Browser;
import cores.WebsiteDriver;
import hasaki.HomePage.HomePageTCs;
import hasaki.OrderFlow.Scenario01;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Documented;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class Demo {


    @Documented
    @interface Infor {
        String author();

        String date();

        int[] member();
    }


    @Infor(author = "Dat", date = "2004", member = {1, 2})
    @Test
    public void test() throws InterruptedException {
        WebsiteDriver driver = new WebsiteDriver(Browser.CHROME);
//        driver.setImplicitWait(Duration.ofSeconds(10));
        driver.navigate("https://hasaki.vn");
        driver.click("onesignal-slidedown-cancel-button");
        driver.click("rejectCookies");
        driver.click("//div[@class='item_header']");
        Thread.sleep(2000);
        driver.switchWindow("Hỗ trợ khách hàng");
        driver.click("//div[text()='Tài khoản']/following-sibling::a");
    }

    @Test
    void test01() throws NoSuchMethodException, ClassNotFoundException {

            Assert.assertEquals(1, 2);

    }

    public static void main(String[] args) throws ClassNotFoundException {
        String ansiReset = "\u001B[0m";
        String ansiCyan = "\u001B[36m";
        String ansiGreen = "\u001B[32m";
        String ansiRed = "\u001B[31m";
        String wordOrange = "\033[38:5:208m";
        String defaultTrue = ansiCyan + "[Verification True ]" + ansiReset;
        String defaultFalse = ansiCyan + "[Verification False]" + ansiReset;
        String defaultEqual = ansiCyan + "[Verification Equal]" + ansiReset;
        String pass = " " + ansiGreen + "PASS" + ansiReset + " ";
        String fail = " " + ansiRed + "FAIL" + ansiReset + " ";

        System.out.printf("\033[38:5:208m%s\033[m\n","Hello world");
    }

    public Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
}
