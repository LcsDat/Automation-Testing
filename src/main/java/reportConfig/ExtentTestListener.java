package reportConfig;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import cores.BaseTest;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import static reportConfig.ExtentTestManager.getTest;

public class ExtentTestListener implements ITestListener {
    @Override
    public void onStart(ITestContext iTestContext) {
        getTest().log(Status.INFO, MarkupHelper.createLabel(iTestContext.getName() + " ------> START",ExtentColor.ORANGE));
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        getTest().log(Status.INFO, MarkupHelper.createLabel(iTestContext.getName() + " ------> FINISH",ExtentColor.GREEN));
        ExtentManager.extentReports.flush();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        getTest().log(Status.INFO, MarkupHelper.createLabel("EXECUTION START " + iTestResult.getName().toUpperCase(), ExtentColor.ORANGE));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        getTest().log(Status.PASS, MarkupHelper.createLabel(iTestResult.getName().toUpperCase() + "------ PASSED ------", ExtentColor.GREEN));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Object testClass = iTestResult.getInstance();
        WebDriver driver = ((BaseTest) testClass).getWebDriver().getDriver();

        String base64Screenshot = "data:image/png;base64," + ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
        getTest().log(Status.FAIL, "Screenshot and Exception", iTestResult.getThrowable(), getTest().addScreenCaptureFromBase64String(base64Screenshot).getModel().getMedia().get(0));
        getTest().log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName().toUpperCase() + "------ FAILED ------", ExtentColor.RED));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        getTest().log(Status.SKIP, MarkupHelper.createLabel(iTestResult.getName().toUpperCase() + "------ SKIPPED ------", ExtentColor.ORANGE));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        getTest().log(Status.FAIL, MarkupHelper.createLabel(iTestResult.getName() + "------ FAILED WITH PERCENTAGE ------", ExtentColor.RED));
    }
}
