package testCases;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.RegistrationPage;
import testBase.BaseTest;
import utility.ConfigReader;
import utility.DriverFactory;


import java.time.Duration;

public class RegistrationTest extends BaseTest {

    public WebDriver driver;

    @BeforeMethod

    public void setup(){

        String browser = ConfigReader.get("browser");
        driver = DriverFactory.initializeDriver(browser);

        driver.get(ConfigReader.get("base.url"));
        System.out.println("Launched " + browser + " and hit the URL");

    }
    @Test(priority = 1)
    public void account_registration(){
        test = extent.createTest("Account Registration ","Register to the application  ");
        test.info("Register with Valid Credentials");

        HomePage hp=new HomePage(driver);
        hp.click_EN_button();
        hp.click_Registration_link();


        // Object creation of Registration Page
        RegistrationPage rp=new RegistrationPage(driver);

        rp.setName(ConfigReader.get("user.name"));
        rp.setPhone(ConfigReader.get("user.phone"));

        rp.set_City(ConfigReader.get("user.city"));
        rp.setEmail(ConfigReader.get("user.email"));
        rp.setPassword(ConfigReader.get("user.password"));
        rp.setConfirmPassword(ConfigReader.get("user.password"));
        rp.clickCheckbox();
        rp.clickSubmit_button();



        String actualText = rp.getOverlayMessageText();
        Assert.assertTrue(actualText.contains("Account created successfully"), "Success message not found!");

        //ASSERT that we are on login page
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.urlContains("/login"));
        Assert.assertEquals(driver.getCurrentUrl(),
                ConfigReader.get("login.url"),
                "Registration did not redirect to login page.");

        test.pass("Account Registration Successfully");



    }

    @Test(priority = 2)
    public void invalidEmailFormatTest() {
        test = extent.createTest("Account Registration with Invalid Email ", "Account Registration with Invalid Email to the application  ");
        test.info("Account Registration with Invalid Email to the application");
        HomePage hp = new HomePage(driver);
        hp.click_EN_button();
        hp.click_Registration_link();

        // Object creation of Registration Page
        RegistrationPage rp = new RegistrationPage(driver);

        rp.setName(ConfigReader.get("user.name"));
        rp.setPhone(ConfigReader.get("user.phone"));
        rp.set_City(ConfigReader.get("user.city"));

        // Set an invalid email format
        rp.setEmail(ConfigReader.get("user.invalid.email"));  // Invalid email
        rp.setPassword(ConfigReader.get("user.password"));


        String errorMessage = rp.getEmailErrorMessageText();
        Assert.assertTrue(errorMessage.contains("Invalid Email"), "Account Registration with Invalid Phone to the application.");
        test.pass("Account Registration with Invalid Email to the application and Error Validated");
    }

    @Test(priority = 3)
    public void invalidPhoneNumberFormatTest() {
        test = extent.createTest("Account Registration with Invalid Phone ","Login to the application  ");
        test.info("Login with Valid Credentials and OTP");
        HomePage hp = new HomePage(driver);
        hp.click_EN_button();
        hp.click_Registration_link();

        // Object creation of Registration Page
        RegistrationPage rp = new RegistrationPage(driver);

        rp.setName(ConfigReader.get("user.name"));
        //rp.setEmail(ConfigReader.get("user.email"));


        // Invalid phone number format
        rp.setPhone(ConfigReader.get("user.invalid.phone"));
        rp.set_City(ConfigReader.get("user.city"));


        String errorMessage = rp.getPhoneErrorMessageText();
        Assert.assertTrue(errorMessage.contains("Invalid phone number format"), "Invalid phone format not handled properly.");
        test.pass("Account Registration with invalid PhoneNumber to the application and Error Validated");
    }

    @Test(priority = 4)
    public void emptyFieldsTest() {
        test = extent.createTest("Account Registration with Empty fields ","Account Register with empty Fields");
        test.info("Login with Valid Credentials and OTP");
        HomePage hp = new HomePage(driver);
        hp.click_EN_button();
        hp.click_Registration_link();

        // Object creation of Registration Page
        RegistrationPage rp = new RegistrationPage(driver);

        rp.setName("");  // Empty name
        rp.setPhone(""); // Empty phone number
        rp.set_City(ConfigReader.get("user.city"));
        rp.setEmail("");
        rp.setPassword("");  // Empty password
        rp.setConfirmPassword("");  // Empty confirm password
        rp.clickCheckbox();
        //rp.clickSubmit_button();

        

        String errorMessageName = rp.getEmptyNameMessageText();
        Assert.assertTrue(errorMessageName.contains("Name is required"), "Empty Name not handled properly.");
        String errorMessagePhone = rp.getEmptyPhoneMessageText();
        Assert.assertTrue(errorMessagePhone.contains("Phone Number is required"), "Empty Phone not handled properly.");

        String errorMessageEmail = rp.getEmptyEmailMessageText();
        Assert.assertTrue(errorMessageEmail.contains("Email is required"), "Empty Email not handled properly.");

        String errorMessagePassword = rp.getEmptyPasswordMessageText();
        Assert.assertTrue(errorMessagePassword.contains("Password is required"), "Empty Password not handled properly.");
        test.pass("Error should for Empty fields");


    }


    @AfterMethod
    public void tearDown(){

        driver.quit();

    }



}
