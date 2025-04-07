package utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
public class ScreenshotUtil {

    private static final Logger logger = LogManager.getLogger(ScreenshotUtil.class);

    public static String captureScreenshot(WebDriver driver, String testName) {
        try {
            // Generating timestamped screenshot name
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = testName + "_" + timestamp + ".png";

            // Get the project root directory
            String projectRoot = System.getProperty("user.dir");

            // Define the path where the screenshot will be saved in the project root
            Path screenshotPath = Paths.get(projectRoot, "screenshots", screenshotName);

            // Log the target path to verify if the path is correct
            logger.info("Screenshot will be saved at: " + screenshotPath.toString());

            // Capture the screenshot as a File object
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Create directories if they don't exist
            Files.createDirectories(screenshotPath.getParent());

            // Copy the screenshot to the defined path
            Files.copy(srcFile.toPath(), screenshotPath);

            logger.info("Screenshot captured successfully for test: " + testName);

            // Return the path for attaching to Extent or for further use
            return screenshotPath.toString();
        } catch (Exception e) {
            logger.error("Failed to capture screenshot for test: " + testName, e);
            return null;
        }
    }
}
