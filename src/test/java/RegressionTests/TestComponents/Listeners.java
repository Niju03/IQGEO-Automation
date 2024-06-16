package RegressionTests.TestComponents;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Listeners implements ITestListener {

    private static final Logger logger = LoggerFactory.getLogger(Listeners.class);
    private ExtentReports extent;
    private ExtentTest test;

    @Override
    public void onStart(ITestContext context) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportName = "Test-Report-" + timeStamp + ".html";
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/Extent Reports" + reportName);

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Host Name", "Automation Testing");
        extent.setSystemInfo("Environment", "UAT");
        extent.setSystemInfo("User Name", "Niranjan N S");
    }

    @Override
    public void onTestStart(ITestResult result) {
        logger.info("Starting test: " + result.getName());
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        logger.info("Test passed: " + result.getName());
        test.log(Status.PASS, "Test passed");
        extent.flush();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        logger.error("Test failed: " + result.getName());
        logger.error("Failure details: " + result.getThrowable().getMessage());

        test = extent.createTest(result.getName());
        test.log(Status.FAIL, "Test failed");
        test.log(Status.FAIL, result.getThrowable());

        // Capture screenshot on failure
        Object currentClass = result.getInstance();
        if (currentClass instanceof BaseTest) {
            BaseTest baseTest = (BaseTest) currentClass;
            String screenshotPath = baseTest.captureScreenshot(result.getMethod().getMethodName());
            if (screenshotPath != null) {
                test.addScreenCaptureFromPath(screenshotPath);
            }
        }

        extent.flush();
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        logger.warn("Test skipped: " + result.getName());
        test = extent.createTest(result.getName());
        test.log(Status.SKIP, "Test skipped");
        extent.flush();
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
