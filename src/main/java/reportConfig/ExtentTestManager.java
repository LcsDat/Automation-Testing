package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import cores.BaseTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExtentTestManager extends BaseTest {

    static Map<String, ExtentTest> extentTestMap = new HashMap<>();
//    static ExtentReports extent;
    static int increment = 0;
    static int currentThread = (int) Thread.currentThread().getId();
    static ExtentTest test;
    static List<String> testClasses;

    static String testClass;

    public static synchronized ExtentTest getTest() {
        return extentTestMap.get(testClass);
    }

    public synchronized static Map<String, ExtentTest> getMap(){
        return extentTestMap;
    }

    public static synchronized ExtentTest startTest(String testName, String desc) {
        testClass = testName;
        test = ExtentManager.init().createTest(testName, desc);
        extentTestMap.put(testName, test);
        System.out.println("map value: " + extentTestMap);
        increment++;
        return getMap().get(testName);
    }
    public static synchronized ExtentTest startTest(String testName, String desc, int currentThread) {
        testClass = testName;
        test = ExtentManager.init().createTest(testName, desc);
        extentTestMap.put(testName, test);
        increment++;
        System.out.println("map value: " + extentTestMap);
        return getMap().get(testName);
    }
}
