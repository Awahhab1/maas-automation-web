package testBase;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.*;

import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
public class BaseTest {
    public static ExtentReports extent;
    public static ExtentTest test;
    public static WebDriver driver;

    // Extent Report configuration
    @BeforeSuite
    public void setUpSuite() {
        // Initialize Extent Report
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Framework", "Selenium WebDriver with TestNG");

        System.out.println("✅ Extent Report Initialized");
    }

    // After Suite: Finalize the Extent Report
    @AfterSuite
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush(); // Finalize the report
            System.out.println("✅ Extent Report Flushed");
        }
    }

    /*// This is for Test Setup, you can create driver as per your test class requirements.
    @BeforeMethod
    public void setupDriver() {
        // Initialize your WebDriver here (Chrome, Firefox, etc.)
        // Example: driver = new ChromeDriver();
    }

    @AfterMethod
    public void tearDownDriver() {

        // Quit the WebDriver after test
        if (driver != null) {
            driver.quit();
        }
    }*/


}
