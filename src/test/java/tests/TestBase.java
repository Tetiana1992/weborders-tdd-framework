package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utilities.Driver;
import utilities.SeleniumUtils;

import java.lang.reflect.Method;
import java.time.Duration;

/**
 * This class contains common configuration methods for all other test classes in this framework
 * All Test classes should extend this class to obtain the common configurations
 */

public class TestBase {

    public static ExtentReports extentReports; // report manager
    public static ExtentSparkReporter htmlReport; // generates the html report
    public static ExtentTest logger;  //logs the test steps

    @BeforeSuite(alwaysRun = true)
    public void setupSuite(){
        extentReports = new ExtentReports();
        htmlReport = new ExtentSparkReporter(System.getProperty("user.dir")+"/target/extentReport/report.html");
        extentReports.attachReporter(htmlReport);

        extentReports.setSystemInfo("Project Name", "Web Orders Test Automation");


    }
    @AfterSuite(alwaysRun = true)
    public void tearDownSuite(){
        extentReports.flush();

    }


    @BeforeMethod (alwaysRun = true)
    public void setup(Method method){
        Driver.getDriver().manage().window().maximize();
        Driver.getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger = extentReports.createTest("Test: "+ method.getName());
        logger.info("TEST STARTED: "+ method.getName());
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown(ITestResult testResult){
        if(testResult.getStatus() == ITestResult.SUCCESS){
            logger.pass("TEST PASSED: "+ testResult.getName());
        }else if(testResult.getStatus() == ITestResult.SKIP){
            logger.skip("TEST SKIPPED: "+ testResult.getName());
        }else if(testResult.getStatus() == ITestResult.FAILURE){
            logger.fail("TEST FAILED: "+ testResult.getName());
            logger.fail(testResult.getThrowable());
            //take a screenshot
            String pathOfTheScreenshotFile = SeleniumUtils.getScreenshot("failed");
            logger.addScreenCaptureFromPath(pathOfTheScreenshotFile);
        }
        logger.pass("TEST PASSED");
        logger.fail("TEST FAILED");
        logger.skip("TEST SKIPPED");
        Driver.quitDriver();
    }

}
