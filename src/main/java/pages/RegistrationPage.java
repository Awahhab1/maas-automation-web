package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class RegistrationPage extends BasePage{
    // Constructor
    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    @FindBy(xpath = "//button[@class='mx-2 btn lang-btn']")
    WebElement en_btn;
    @FindBy(css="input[placeholder='Name *']")
    WebElement NameInput;

    @FindBy(css="div[class='col-md-4 mb-2 input-container'] input[placeholder='Phone Number *']")
    WebElement PhoneInput;

    @FindBy(css="div[class='col-md-4 mb-2 input-container'] input[role='combobox']")
    WebElement CityDropdown;
    @FindBy (css="div[class='ng-option ng-star-inserted']")
    List<WebElement> CityList;


    @FindBy(css="input[placeholder='Email *'][type='email']")
    WebElement EmailInput;

    @FindBy(css="div[class='col-md-4 mb-2 input-container'] input[placeholder='Password *']")
    WebElement PasswordInput;

    @FindBy(css="div[class='col-md-4 mb-2 input-container'] input[placeholder='Confirm Password *']")
    WebElement ConfirmPasswordInput;

    @FindBy(className="mdc-checkbox__native-control")
    WebElement TermsCheckbox;

    @FindBy(css="div[class='d-flex justify-content-end submit-btn'] button[class='btn mattgreen-color-bg large-btn fw-bold']")
    WebElement SubmitButton;

    @FindBy(css = ".cdk-overlay-container")
    WebElement overlayContainer;

    @FindBy(xpath = "//div[@class='ng-star-inserted']//span[contains(text(), 'Invalid Email')]")
    WebElement emailErrorMessage;

    @FindBy(xpath = "//div[@class='ng-star-inserted']//span[contains(text(), 'Invalid phone number format')]")
    WebElement phoneErrorMessage;

    @FindBy(xpath = "//div[@class='ng-star-inserted']//span[contains(text(), 'Name is required')]")
    WebElement emptyNameErrorMessage;
    @FindBy(xpath = "//div[@class='ng-star-inserted']//span[contains(text(), 'Phone Number is required')]")
    WebElement emptyPhoneErrorMessage;
    @FindBy(xpath = "//div[@class='ng-star-inserted']//span[contains(text(), 'Email is required')]")
    WebElement emptyEmailErrorMessage;
    @FindBy(xpath = "//div[@class='ng-star-inserted']//span[contains(text(), 'Password is required')]")
    WebElement emptyPasswordErrorMessage;
    @FindBy(xpath = "//div[@class='ng-star-inserted']//span[contains(text(), 'Confirm password is required')]")
    WebElement emptyConfirmPasswordErrorMessage;



    public void setName(String name)
    {
      //  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       // wait.until(ExpectedConditions.visibilityOf(NameInput));
        NameInput.sendKeys(name);
    }

    public void setPhone(String phone){
        PhoneInput.sendKeys(phone);
    }

    public void set_City(String city) {
        CityDropdown.click();
        for (WebElement option : CityList) {
            if (option.getText().equals(city)) {
                option.click();
                break;
            }
        }
    }

    public void setEmail(String email){
        EmailInput.sendKeys(email);
    }

    public void setPassword(String password){
        PasswordInput.sendKeys(password);
    }

    public void setConfirmPassword(String password){
        ConfirmPasswordInput.sendKeys(password);
    }

    public void clickCheckbox(){
        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.scrollBy(0,500)");
         JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", TermsCheckbox);
        JavascriptExecutor js3 = (JavascriptExecutor) driver;
        js3.executeScript("arguments[0].click();", TermsCheckbox);

    }

    public void clickSubmit_button(){

        JavascriptExecutor js1 = (JavascriptExecutor) driver;
        js1.executeScript("window.scrollBy(0,500)");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", SubmitButton);
        JavascriptExecutor js3 = (JavascriptExecutor) driver;
        js3.executeScript("arguments[0].click();", SubmitButton);

    }

    public void click_EN_button(){
        en_btn.click();
    }

    public String getOverlayMessageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(overlayContainer));
        return overlayContainer.getText();
    }

    public String getEmailErrorMessageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(emailErrorMessage));
        return emailErrorMessage.getText();
    }




    public String getPhoneErrorMessageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(phoneErrorMessage));
        return phoneErrorMessage.getText();
    }

    public String getEmptyNameMessageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(emptyNameErrorMessage));
        return emptyNameErrorMessage.getText();
    }

    public String getEmptyPhoneMessageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(emptyPhoneErrorMessage));
        return emptyPhoneErrorMessage.getText();
    }
    public String getEmptyEmailMessageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOf(emptyEmailErrorMessage));
        return emptyEmailErrorMessage.getText();
    }

    public String getEmptyPasswordMessageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(emptyPasswordErrorMessage));
        return emptyPasswordErrorMessage.getText();
    }

    public String getEmptyConfirmPasswordMessageText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(emptyConfirmPasswordErrorMessage));
        return emptyConfirmPasswordErrorMessage.getText();
    }

}
