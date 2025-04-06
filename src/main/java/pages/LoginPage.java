package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class LoginPage extends BasePage{




    // Constructor
    public LoginPage(WebDriver driver) {
        super(driver);
    }
    // Locators
    @FindBy(xpath = "//button[@class='mx-2 btn lang-btn']")
    WebElement en_btn;
    @FindBy(css="input[placeholder='Email *']")
    WebElement Email;
    @FindBy(css="input[placeholder='Password *']")
    WebElement Password;

    @FindBy(xpath = "//button[@class='login--button mattgreen-color-bg p-2 btn-round btn-block btn']")
    WebElement Login;

    @FindBy(xpath="//input[starts-with(@id, 'otp_0') and contains(@class, 'otp-input')]")
    WebElement otp_input_1;
    @FindBy(xpath="//input[starts-with(@id, 'otp_1') and contains(@class, 'otp-input')]")
    WebElement otp_input_2;
    @FindBy(xpath="//input[starts-with(@id, 'otp_2') and contains(@class, 'otp-input')]")
    WebElement otp_input_3;
    @FindBy(xpath="//input[starts-with(@id, 'otp_3') and contains(@class, 'otp-input')]")
    WebElement otp_input_4;

    @FindAll({
            @FindBy(xpath = "//input[starts-with(@id, 'otp_') and contains(@class, 'otp-input')]")
    })
    List<WebElement> otpInputs;

    @FindBy(id="submit-login-otp")
    WebElement click_verify;

    // Email Assertion check web Element
    @FindBy(xpath="//span[contains(text(), '@gmail.com')]")
    WebElement displayedEmailElement;

    @FindBy(xpath = "//div[@class='ng-star-inserted']//span[contains(text(), 'Invalid Email')]")
    WebElement emailErrorElement;
    @FindBy(xpath = "//div[@class='ng-star-inserted']//span[contains(text(), ' Password is required ')]")
    WebElement emptyPasswordErrorElement;
    @FindBy(css = ".cdk-overlay-container")
    WebElement overlayContainer;



    public void Set_Email(String email){
       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      //  wait.until(ExpectedConditions.visibilityOf(Email));
        Email.sendKeys(email);

    }

    public void Set_Password(String password){
        Password.sendKeys(password);
    }

    public void Click_Login(){

       // JavascriptExecutor js1 = (JavascriptExecutor) driver;
       // js1.executeScript("window.scrollBy(0,500)");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", Login);
        JavascriptExecutor js3 = (JavascriptExecutor) driver;
        js3.executeScript("arguments[0].click();", Login);
        System.out.println("click on Login button");


    }
    public void click_EN_button()  {
        en_btn.click();


    }

    public void set_otp(String otp){

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfAllElements(otpInputs));
        char[] digits = otp.toCharArray();
        for (int i = 0; i < digits.length; i++){
            System.out.println(i);
        }

        System.out.println("Total OTP input fields found: " + otpInputs.size());

        for (WebElement input : otpInputs) {
            System.out.println("Input field - ID: " + input.getDomAttribute("id") +
                    ", Class: " + input.getDomAttribute("class"));
        }





        for (int i = 0; i < digits.length; i++) {
            otpInputs.get(i).sendKeys(String.valueOf(String.valueOf(digits[i])));
        }
    }

    public void click_verify_button() {
        click_verify.click();
    }

    public String getDisplayedEmailText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(displayedEmailElement));
        return displayedEmailElement.getText();
    }

    public String getInvalidEmailText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(emailErrorElement));
        return emailErrorElement.getText();
    }

    public String getEmptyPasswordText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(emptyPasswordErrorElement));
        return emptyPasswordErrorElement.getText();
    }

    public String getOverlayMessageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(overlayContainer));
        return overlayContainer.getText();
    }




}
