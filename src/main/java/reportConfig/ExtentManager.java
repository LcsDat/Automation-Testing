package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import cores.Global;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports init() {
        ExtentSparkReporter reporter = new ExtentSparkReporter(Global.PROJECTPATH + "/extentV5/Hasaki.html");
        reporter.config().setDocumentTitle("Hasaki E2E Testingggggggggggggggggggggggg");
        reporter.config().setReportName("Hasaki Test Report");
        reporter.config().setTimelineEnabled(true);
        reporter.config().setEncoding("utf-8");
        reporter.config().setTheme(Theme.DARK);

        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Company", "Hideyashy");
        extentReports.setSystemInfo("Project", "Hasaki");
        extentReports.setSystemInfo("Team", "Hideyashy Team");
        extentReports.setSystemInfo("Contact", "datle.testing01@gmail.com");
        extentReports.setSystemInfo("JDK version", Global.JAVA_VERSION);
        return extentReports;
    }
}
