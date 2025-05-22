package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import cores.BaseTest;
import logConfig.Log4j2Manager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.status.StatusLogger;
import org.testng.IExecutionListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class ExtentTestListener extends BaseTest implements ITestListener, IExecutionListener {
    private static final ExtentReports extent = ExtentManager.getInstance();
    private static Logger logger;

    private ExtentTest extentLog(ITestResult iTestResult) {
        return extentTestMap.get(iTestResult.getTestClass().getName());
    }

    @Override
    public void onExecutionStart() {
        // Force Log4j2 to initialize completely
        LoggerContext context = (LoggerContext) LogManager.getContext(false);

        // Wait for initialization to complete
        if (!context.isStarted()) {
            // Force re-initialization
            context.reconfigure();

            // Wait for configuration to be applied
            int attempts = 0;
            while (!context.isStarted() && attempts < 10) {
                try {
                    Thread.sleep(100);
                    attempts++;
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
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
        extentLog(iTestResult).info(MarkupHelper.createLabel("TEST CASE START: " + iTestResult.getTestClass() + "---" + iTestResult.getName(), ExtentColor.BLUE));
//        logger = Log4j2Manager.getLogger(iTestResult.getTestClass()).getAssertionPassLogger();
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        var logger = Log4j2Manager.getLogger(iTestResult.getTestClass()).getAssertionPassLogger();
        extentLog(iTestResult).pass(MarkupHelper.createLabel(iTestResult.getTestClass() + "---" + iTestResult.getName() + " ====> PASSED", ExtentColor.GREEN));
        logger.info("{}.{} ====> PASSED", iTestResult.getTestClass().getName(), iTestResult.getName());
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        var logger = Log4j2Manager.getLogger(iTestResult.getTestClass()).getAssertionFailLogger();
        extentLog(iTestResult).fail(MarkupHelper.createLabel(iTestResult.getTestClass() + "---" + iTestResult.getName() + " ====> FAILED", ExtentColor.RED));
        extentLog(iTestResult).fail(iTestResult.getThrowable(), attachScreenshot());
        logger.error("{}.{} ====> FAILED", iTestResult.getTestClass().getName(), iTestResult.getName());
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        var logger = Log4j2Manager.getLogger(iTestResult.getTestClass()).getAssertionFailLogger();
        extentLog(iTestResult).skip(MarkupHelper.createLabel(iTestResult.getTestClass() + "---" + iTestResult.getName() + " ====> SKIPPED", ExtentColor.ORANGE));
        extentLog(iTestResult).skip(iTestResult.getThrowable(), attachScreenshot());
        logger.error("{}.{} ====> SKIPPED", iTestResult.getTestClass().getName(), iTestResult.getName());
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        var logger = Log4j2Manager.getLogger(iTestResult.getTestClass()).getAssertionFailLogger();
        extentLog(iTestResult).skip(MarkupHelper.createLabel(iTestResult.getTestClass() + "---" + iTestResult.getName() + " ====> SKIPPED WITH PERCENT", ExtentColor.ORANGE));
        extentLog(iTestResult).skip(iTestResult.getThrowable(), attachScreenshot());
        logger.error("{}.{} ====> SKIPPED WITH PERCENT", iTestResult.getTestClass().getName(), iTestResult.getName());
    }
}
