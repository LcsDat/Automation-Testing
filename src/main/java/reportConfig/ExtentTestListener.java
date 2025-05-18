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

    private ExtentTest extentLog(String testClass) {
        return extentTestManager.getExtentTest(testClass);
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
        extentLog(testClass).log(Status.INFO, MarkupHelper.createLabel("EXECUTION START " + iTestResult.getName().toUpperCase(), ExtentColor.ORANGE));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        var testClass = iTestResult.getTestClass().getName();
        extentTestManager.getExtentTest(testClass).log(Status.PASS, MarkupHelper.createLabel(iTestResult.getName().toUpperCase() + "------ PASSED ------", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        var testClassName = iTestResult.getTestClass().getName();
        Object testClass = iTestResult.getInstance();
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) ((BaseTest) testClass).getWebDriver().getDriver()).getScreenshotAs(OutputType.BASE64);
        extentLog(testClassName).log(Status.FAIL, "Screenshot and Exception", iTestResult.getThrowable(), extentLog(testClassName).addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(BaseTest.getScreenNo()));
        extentLog(testClassName).log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName().toUpperCase() + " of " + iTestResult.getTestClass().getName() + " ------ FAILED ------", ExtentColor.RED));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        var testClassName = iTestResult.getTestClass().getName();
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) ((BaseTest) testClass).getWebDriver().getDriver()).getScreenshotAs(OutputType.BASE64);
        extentLog(testClassName).log(Status.SKIP, MarkupHelper.createLabel(iTestResult.getName().toUpperCase() + " ------ SKIPPED ------", ExtentColor.ORANGE));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        var testClass = iTestResult.getTestClass().getName();
        extentLog(testClass).log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName() + " ------ FAILED WITH PERCENTAGE ------", ExtentColor.RED));
    }
}
