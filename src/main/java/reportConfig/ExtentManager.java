package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import cores.GlobalVariables;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports init() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(GlobalVariables.PROJECTPATH + "/extentV5/Hasaki.html");
        reporter.config().setDocumentTitle("HASAKI TESTING");
        reporter.config().setReportName("Hasaki Test Report");
        reporter.config().setTimelineEnabled(true);
        reporter.config().setEncoding("utf-8");
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setTimeStampFormat("MMM dd, HH:mm:ss a");

        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Company", "Hideyashy");
        extentReports.setSystemInfo("Project", "Hasaki");
        extentReports.setSystemInfo("Team", "Hideyashy Team");
        extentReports.setSystemInfo("Contact", "datle.testing01@gmail.com");
        extentReports.setSystemInfo("JDK version", GlobalVariables.JAVA_VERSION);
        return extentReports;
    }
}
