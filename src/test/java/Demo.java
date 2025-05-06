import cores.Browser;
import cores.WebsiteDriver;
import hasaki.OrderFlow.User_Order_One_Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Documented;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Demo {


    @Documented
    @interface Infor {
        String author();

        String date();

        int[] member();
    }

    static boolean checkBalance(String input) {
        boolean flag = false;
        HashMap<Character, Character> brackets = new HashMap<>();
        brackets.put('[', ']');
        brackets.put('{', '}');
        brackets.put('(', ')');
        //Check null or odd length
        if (input == null || input.length() % 2 != 0) {
            flag = false;
        } else {

            char[] array = input.toCharArray();
            int limit = array.length / 2;
            for (int i = 0; i < limit; i++) {
                try {
                    if (brackets.get(array[i]) == array[array.length - i - 1]) flag = true;
                } catch (NullPointerException e) {
                }

            }
        }
        return flag;
    }

    @Infor(author = "Dat", date = "2004", member = {1, 2})
    @Test
    public void test() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get("https://vm-070d3e77-ab31-410b-8533-ec0b3d9ea92d-8000.in-vmprovider.projects.hrcdn.net/loanPage.html");

        driver.findElement(By.id("fname")).clear();
        driver.findElement(By.id("fname")).sendKeys("aaaa");

        driver.findElement(By.id("lname")).clear();
        driver.findElement(By.id("lname")).sendKeys("bbbb");

        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("a@1234");

        Select loanTypes = new Select(driver.findElement(
                By.cssSelector("select[id='loanType']")));
        loanTypes.selectByVisibleText("Commercial");

        driver.findElement(By.id("loanDuration")).sendKeys("6");

        driver.findElement(By.cssSelector("bubtton[type='submit']")).click();


        String secretCode = driver.findElement(By.xpath("//body")).getText();
        System.out.println(secretCode);
    }

    @Test
    void test01() throws NoSuchMethodException, ClassNotFoundException {
        System.out.printf("\033[31;1mHello\033[0m");
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
        HashMap<Character, Character> brackets = new HashMap<>();
        brackets.put('[', ']');
        brackets.put('{', '}');
        brackets.put('(', ')');
        System.out.println(checkBalance("[{]()"));
//        System.out.println(brackets.get('"'));

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
