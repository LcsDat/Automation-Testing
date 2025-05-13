package reportConfig;

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
    @Override
    public void onStart(ITestContext iTestContext) {
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        System.out.println("onTestStart: " + iTestResult.getTestClass().getName());
        ExtentTestManager.init().getTest(iTestResult.getTestClass().getName()).log(Status.INFO, MarkupHelper.createLabel("EXECUTION START " + iTestResult.getName().toUpperCase(), ExtentColor.ORANGE));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        System.out.println("onTestSuccess: " + iTestResult.getTestClass().getName());
        ExtentTestManager.init().getTest(iTestResult.getTestClass().getName()).log(Status.PASS, MarkupHelper.createLabel(iTestResult.getName().toUpperCase() + "------ PASSED ------", ExtentColor.GREEN));

    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();

        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot)((BaseTest) testClass).getWebDriver().getDriver()).getScreenshotAs(OutputType.BASE64);
        ExtentTestManager.init().getTest(iTestResult.getTestClass().getName()).log(Status.FAIL, "Screenshot and Exception", iTestResult.getThrowable(), ExtentTestManager.init().getTest(iTestResult.getTestClass().getName()).addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
        ExtentTestManager.init().getTest(iTestResult.getTestClass().getName()).log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName().toUpperCase() + " of " + iTestResult.getTestClass().getName() + "------ FAILED ------", ExtentColor.RED));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();

        System.out.println("onTestSkipped: " + iTestResult.getTestClass().getName());
        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot)((BaseTest) testClass).getWebDriver().getDriver()).getScreenshotAs(OutputType.BASE64);
        ExtentTestManager.init().getTest(iTestResult.getTestClass().getName()).log(Status.SKIP, "Screenshot and Exception", iTestResult.getThrowable(), ExtentTestManager.init().getTest(iTestResult.getTestClass().getName()).addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
        ExtentTestManager.init().getTest(iTestResult.getTestClass().getName()).log(Status.SKIP, MarkupHelper.createLabel(iTestResult.getName().toUpperCase() + "------ SKIPPED ------", ExtentColor.ORANGE));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        ExtentTestManager.init().getTest(iTestResult.getTestClass().getName()).log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName() + "------ FAILED WITH PERCENTAGE ------", ExtentColor.RED));
    }
}
