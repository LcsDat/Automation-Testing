package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import cores.GlobalVariables;

import java.util.Optional;

public class ExtentManager {
    private static ExtentReports extentReports;

    /**
     * Gets the singleton instance of ExtentReports
     * Creates a new instance if one doesn't exist
     * @return ExtentReports instance
     */
    public static ExtentReports getInstance() {
        if (extentReports == null) {
            init();
        }
        return extentReports;
    }


    private static ExtentReports init() {
        // Initialize the reporter
        ExtentSparkReporter reporter = new ExtentSparkReporter(GlobalVariables.PROJECTPATH + "/extentV5/Hasaki.html");

        // Configure the reporter appearance
        reporter.config().setReportName("Hasaki Test Report");
        reporter.config().setTimelineEnabled(true);
        reporter.config().setEncoding("utf-8");
        reporter.config().setTheme(Theme.DARK);
        reporter.config().setTimeStampFormat("MMM dd, HH:mm:ss a");

        // Initialize ExtentReports and attach the reporter
        extentReports = new ExtentReports();
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Company", "Hideyashy");
        extentReports.setSystemInfo("Project", "Hasaki");
        extentReports.setSystemInfo("Team", "Hideyashy Team");
        extentReports.setSystemInfo("Contact", "datle.testing01@gmail.com");
        extentReports.setSystemInfo("JDK version", GlobalVariables.JAVA_VERSION);

        return extentReports;
    }
}
