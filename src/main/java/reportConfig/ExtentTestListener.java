package reportConfig;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import cores.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class ExtentTestListener extends BaseTest implements ITestListener {


    private ExtentTest extentLog() {
//        System.out.println("test class in listener: " + testClass);
//        System.out.println("ETM in Testlistener " + extentTestManager);
//        System.out.println("map in listener: " + extentTestManager.getMap());
//        System.out.println("Get ET by map: " + extentTestManager.getMap().get(testClass));
//        System.out.println("ET in Testlistener " + extentTestManager.getExtentTest());
        var a = extentTestManager.getTestClass();
        System.out.println("testClass from ETM: " + a);
        return extentTestManager.getMap().get(a);
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        var testClass = iTestResult.getTestClass().getName();
        System.out.println("bien extent test gian tiep: " + extentLog());
        extentLog().log(Status.INFO, MarkupHelper.createLabel("EXECUTION START " + iTestResult.getName().toUpperCase(), ExtentColor.ORANGE));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        var testClass = iTestResult.getTestClass().getName();
        extentLog().log(Status.PASS, MarkupHelper.createLabel(iTestResult.getName().toUpperCase() + "------ PASSED ------", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        var testClassName = iTestResult.getTestClass().getName();
        System.out.println("test Failure: " + testClassName);
        Object testClass = iTestResult.getInstance();
//        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) ((BaseTest) testClass).getWebDriver().getDriver()).getScreenshotAs(OutputType.BASE64);
//        extentLog().log(Status.FAIL, "Screenshot and Exception", iTestResult.getThrowable(), attachScreenshot(((BaseTest) testClass).getWebDriver().getDriver()));
        extentLog().log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName().toUpperCase() + " of " + iTestResult.getTestClass().getName() + " ------ FAILED ------", ExtentColor.RED));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        var testClassName = iTestResult.getTestClass().getName();
//        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) ((BaseTest) testClass).getWebDriver().getDriver()).getScreenshotAs(OutputType.BASE64);
//        extentLog().log(Status.SKIP, "Screenshot and Exception", iTestResult.getThrowable(), attachScreenshot(((BaseTest) testClass).getWebDriver().getDriver()));
        extentLog().log(Status.SKIP, MarkupHelper.createLabel(iTestResult.getName().toUpperCase() + " ------ SKIPPED ------", ExtentColor.ORANGE));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        var testClass = iTestResult.getTestClass().getName();
        extentLog().log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName() + " ------ FAILED WITH PERCENTAGE ------", ExtentColor.RED));
    }
}
