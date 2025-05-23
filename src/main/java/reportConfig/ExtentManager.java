package reportConfig;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import cores.GlobalVariables;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class ExtentManager {
    private static ExtentManager extentManager;
    private static ExtentReports extentReports;
    protected static final ConcurrentHashMap<String, ExtentTest> extentTestMap = new ConcurrentHashMap<>();

    /**
     * Gets the singleton instance of ExtentReports
     * Creates a new instance if one doesn't exist
     * @return ExtentReports instance
     */
    public static ExtentManager getInstance() {
        if (extentReports == null && extentManager == null) {
            init();
            extentManager = new ExtentManager();
        }
        return extentManager;
    }

    private static void init() {
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

    }

    public static ExtentReports getExtentReports() {
        return extentReports;
    }

    public void createExtentTest (String suiteName){
        var extentTest = extentReports.createTest(suiteName);
        extentTestMap.put(suiteName, extentTest);
    }


    public void createExtentTest (String suiteName, String description){
        var extentTest = extentReports.createTest(suiteName, description);
        extentTestMap.put(suiteName, extentTest);
    }

    public Map<String, ExtentTest> getExtentTestMap(){
        return extentTestMap;
    }


}
