import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import cores.GlobalVariables;

import java.util.Arrays;

public class Demo1 {
    public static void main(String[] args) {

//        ExtentSparkReporter reporter = new ExtentSparkReporter(GlobalVariables.PROJECTPATH + "/extentV5/Hasaki-Demo.html");
//        ExtentReports extent = new ExtentReports();
//
//
//        reporter.config().setReportName("Hasaki Test Report");
//        reporter.config().setDocumentTitle("Test Results");
//        reporter.config().setTimelineEnabled(true);
//        reporter.config().setEncoding("utf-8");
//        reporter.config().setTheme(Theme.DARK);
//        reporter.config().setTimeStampFormat("MMM dd, HH:mm:ss a");
//        reporter.config().setJs("document.addEventListener('DOMContentLoaded', function() {" +
//                "setTimeout(function() {" +
//                "var stepHeaders = document.querySelectorAll('h6.card-title');" +
//                "stepHeaders.forEach(function(header) {" +
//                "if(header.textContent.trim() === 'Steps') {" +
//                "header.textContent = 'Test Cases';" +
//                "}" +
//                "});" +
//                "}, 500);" +
//                "});");
//
//        extent.attachReporter(reporter);
//        extent.setSystemInfo("Company", "Hideyashy");
//        extent.setSystemInfo("Project", "Hasaki");
//        extent.setSystemInfo("Team", "Hideyashy Team");
//        extent.setSystemInfo("Contact", "datle.testing01@gmail.com");
//        extent.setSystemInfo("JDK version", GlobalVariables.JAVA_VERSION);
//        extent.setAnalysisStrategy(AnalysisStrategy.SUITE);
//
//        ExtentTest suite1 = extent.createTest("Login Module Test Suite");
//        ExtentTest suite2 = extent.createTest("Dashboard Module Test Suite");
//
//        ExtentTest testCase1 = suite1.createNode("Valid Login Test");
//        ExtentTest testCase2 = suite2.createNode("Admin Dashboard Test");
//        ExtentTest testCase3 = suite2.createNode("Admin Dashboard Test");
//        ExtentTest testCase4 = suite1.createNode("Admin Dashboard Test");
//
////        testCase1.log(Status.INFO, "Navigating to login page");
//        testCase1.log(Status.PASS, "Login successful");
//
////        testCase2.log(Status.INFO, "Navigating to login page");
//        testCase2.log(Status.FAIL, "Login successful");
//
////        testCase3.log(Status.INFO, "Navigating to dashboard page");
//        testCase3.log(Status.PASS, "Login successful");
//
//        testCase4.log(Status.INFO, "Login successful");
//        testCase4.log(Status.PASS, "Login successful");
//
//
//        extent.flush();
        System.out.println(Arrays.toString(Thread.currentThread().getStackTrace()));
    }
    public void hel(){}
}
