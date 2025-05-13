package reportConfig;

import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {

    private static ExtentTestManager extentTestManager = null;
    private final Map<String, ExtentTest> extentTestMap = new HashMap<>();
    private String testClass;

    public static ExtentTestManager init(){
        if(extentTestManager == null){
            extentTestManager = new ExtentTestManager();
        }
        return extentTestManager;
    }
    public ExtentTest getTest() {
        return extentTestMap.get(testClass);
    }

    public ExtentTest getTest(String testClass) {
        return extentTestMap.get(testClass);
    }

    public Map<String, ExtentTest> getMap(){
        return extentTestMap;
    }

    public ExtentTest startTest(String testName, String desc) {
        testClass = testName;
        ExtentTest extentTest = ExtentManager.init().createTest(testName, desc);
        extentTestMap.put(testName, extentTest);
        return extentTest;
    }
}
