package testCases;

import com.aventstack.extentreports.ExtentReports;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.RegistrationPage;
import testBase.BaseTest;
import utility.ConfigReader;
import utility.DriverFactory;
import utility.otpAPI;

import java.time.Duration;
import java.util.Map;

public class LoginTest extends BaseTest {
    //

    public WebDriver driver;
    private static ExtentReports report;

    @BeforeMethod
    public void setup() {


        String browser = ConfigReader.get("browser");
        driver = DriverFactory.initializeDriver(browser);

        driver.get(ConfigReader.get("base.url"));
        System.out.println("Launched " + browser + " and hit the URL");
    }


    @Test(priority = 1)
    public void login_User(){
        test = extent.createTest("Login with Valid credentials ","Login to the application  ");
        test.info("Login with Valid Credentials and OTP");

        HomePage homePage=new HomePage(driver);
        homePage.click_EN_button();
        homePage.click_Login_link();

        LoginPage loginpage=new LoginPage(driver);
        //lp.click_EN_button();

        loginpage.Set_Email(ConfigReader.get("user.email"));

        loginpage.Set_Password(ConfigReader.get("user.password"));
        loginpage.Click_Login();

        String otp = otpAPI.getOtpCode();
        System.out.println("OTP: " + otp);
        loginpage.set_otp(otp);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginpage.click_verify_button();



        //ASSERT that we are on control-panel page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("/control-panel"));
        Assert.assertEquals(driver.getCurrentUrl(),
                ConfigReader.get("control.panel.url"),
                "Login did not redirect to Control Panel page.");

        // Get the email text from the displayed email element
        String actualEmail = loginpage.getDisplayedEmailText(); // Call the method from your Page Object Model (POM)

        // The expected email read form config file
        String expectedEmail = ConfigReader.get("user.email");

        // Assert that the displayed email matches the expected email
        Assert.assertEquals(actualEmail, expectedEmail, "Displayed email does not match the expected email.");
        test.pass("Login Case Passed and user able to Login Successfully");

    }




    @Test(priority = 2)
    public void login_InvalidEmail() {
        test = extent.createTest("Login with Invalid Email credentials ","Login to the application with Invalid Email ");
        test.info("Login with Valid Credentials and OTP");
        HomePage homePage = new HomePage(driver);
        homePage.click_EN_button();
        homePage.click_Login_link();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.Set_Email(ConfigReader.get("user.invalid.email")); // Invalid email format
        loginPage.Set_Password(ConfigReader.get("user.password"));
        loginPage.Click_Login();


        String errorMessage = loginPage.getInvalidEmailText();
        Assert.assertTrue(errorMessage.contains("Invalid Email"), "Error message for invalid email not displayed.");
        test.pass("Login Case with Invalid credentials Passed ");
    }


    @Test(priority = 3)

    public void login_EmptyPassword() {
        test = extent.createTest("Login with Empty Password ","Login to the application with Empty password");
        test.info("Login with Empty password");
        HomePage homePage = new HomePage(driver);
        homePage.click_EN_button();
        homePage.click_Login_link();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.Set_Email(ConfigReader.get("user.email"));
        loginPage.Set_Password(""); // Invalid password
        loginPage.Set_Email(ConfigReader.get("user.email"));
        //loginPage.Click_Login();


        String errorMessage = loginPage.getEmptyPasswordText();
        Assert.assertTrue(errorMessage.contains("Password is required"), "Error message for Empty password not displayed.");
        test.pass("Error message for Empty password  displayed. and Test got passed");
    }

    @Test(priority = 4)
    public void login_IncorrectOtp() {
        test = extent.createTest("Login with Incorrect OTP ","Login to the application with Incorrect OTP");
        test.info("Login with Empty password");
        HomePage homePage = new HomePage(driver);
        homePage.click_EN_button();
        homePage.click_Login_link();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.Set_Email(ConfigReader.get("user.email"));
        loginPage.Set_Password(ConfigReader.get("user.password"));
        loginPage.Click_Login();

        // Enter an incorrect OTP
        String invalidOtp = ConfigReader.get("user.invalid.otp");
        loginPage.set_otp(invalidOtp);
        loginPage.click_verify_button();

        String actualText = loginPage.getOverlayMessageText();
        Assert.assertTrue(actualText.contains("Invalid OTP"), "Expected OTP error message not displayed.");
        test.pass("Error message for Incorrect OTP displayed. and Test got passed");


    }


    @AfterMethod
    public void tearDown(){

        driver.quit();

    }
}

