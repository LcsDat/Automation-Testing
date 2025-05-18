package reportConfig;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.model.Media;

import java.util.HashMap;
import java.util.Map;

public class ExtentTestManager {

    private static ExtentTestManager extentTestManager;
    private static final Map<String, ExtentTest> extentTestMap = new HashMap<>();
    ExtentTest extentTest;
    private String testClass;

    public static ExtentTestManager init(){
            extentTestManager = new ExtentTestManager();
        return extentTestManager;
    }

    public ExtentTest getExtentTest() {
        return extentTestMap.get(testClass);
    }

    public ExtentTest getExtentTest(String testClass) {
        return extentTestMap.get(testClass);
    }

    public Map<String, ExtentTest> getMap(){
        return extentTestMap;
    }

    public void startTest(String testName, String desc) {
        testClass = testName;
        extentTest = ExtentManager.init().createTest(testName, desc);
        System.out.println("ET object: " + extentTest);
        extentTestMap.put(testName, extentTest);
        System.out.println("map value: " + extentTestMap);
//        return extentTest;
    }

    public void logInfo(String description){
        extentTest.info(MarkupHelper.createLabel(description, ExtentColor.GREY));
    }

    public void logInfo(String description, ExtentColor logColor){
        extentTest.info(MarkupHelper.createLabel(description, logColor));
    }

    public void logInfo(String description, boolean enableCapture, Media media){
        if (enableCapture) extentTest.log(Status.INFO ,MarkupHelper.createLabel(description, ExtentColor.TEAL), media);
    }


}
