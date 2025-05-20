package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import cores.BaseTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class ExtentTestListener extends BaseTest implements ITestListener {
    private static final ExtentReports extent = ExtentManager.getInstance();

    private ExtentTest extentLog(ITestResult iTestResult) {
        return extentTestMap.get(iTestResult.getTestClass().getName());
    }

    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        extent.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        extentLog(iTestResult).info(MarkupHelper.createLabel("ON TEST START", ExtentColor.YELLOW));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        extentLog(iTestResult).pass(MarkupHelper.createLabel("ON TEST PASS", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        extentLog(iTestResult).fail(MarkupHelper.createLabel("ON TEST FAIL", ExtentColor.RED));
        extentLog(iTestResult).fail(iTestResult.getThrowable(),attachScreenshot());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        extentLog(iTestResult).skip(MarkupHelper.createLabel("ON TEST SKIPPED", ExtentColor.ORANGE));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        extentLog(iTestResult).skip(MarkupHelper.createLabel("ON TEST SKIPPED", ExtentColor.ORANGE));
    }
}
