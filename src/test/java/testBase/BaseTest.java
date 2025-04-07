package testBase;
import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import org.testng.ITestResult;
import org.testng.annotations.*;
import utility.ConfigReader;
import utility.DriverFactory;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;


import java.io.File;
import java.io.IOException;

import java.time.Duration;

public class BaseTest {
    public static ExtentReports extent;
    public static ExtentTest test;
    public WebDriver driver;

    private static final Logger logger = LogManager.getLogger(BaseTest.class);

    @BeforeSuite
    public void setUpSuite() {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);
        extent.setSystemInfo("Framework", "Selenium WebDriver with TestNG");
        System.out.println("Extent Report Initialized");
    }

    @BeforeMethod
    public void setup() {
        String browser = ConfigReader.get("browser");
        driver = DriverFactory.initializeDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConfigReader.get("base.url"));
        System.out.println("Launched " + browser + " and hit the URL");
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (driver != null) {
            try {
                // Capture screenshot after each test method
                String screenshotPath = captureScreenshot(driver, result.getMethod().getMethodName());

                // Check if the screenshot file exists and is not empty
                File screenshotFile = new File(screenshotPath);
                if (screenshotFile.exists() && screenshotFile.length() > 0) {
                    // Add screenshot to Extent Report
                    test.info("Screenshot after test: " + result.getMethod().getMethodName(),
                            MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
                } else {
                    test.info("No screenshot captured.");
                }

            } catch (IOException ex) {
                logger.error("Failed to capture screenshot: " + ex.getMessage(), ex);
            } finally {
                driver.quit();
                logger.info("Browser closed after test: " + result.getMethod().getMethodName());
            }
        }
    }

    // Helper method to capture screenshot
    private String captureScreenshot(WebDriver driver, String methodName) throws IOException {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String screenshotPath = "screenshots/" + methodName + ".png";
        File destinationFile = new File(screenshotPath);
        org.apache.commons.io.FileUtils.copyFile(screenshot, destinationFile);
        return screenshotPath;
    }



    @AfterSuite
    public void tearDownSuite() {
        if (extent != null) {
            extent.flush();
            System.out.println("Extent Report Flushed");
        }
    }
    }





