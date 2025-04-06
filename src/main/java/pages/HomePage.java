package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage{


    // Constructor
    public HomePage(WebDriver driver) {
        super(driver);
    }

    //locators
    @FindBy(xpath = "//button[@class='mx-2 btn lang-btn']")
    WebElement en_btn;
    @FindBy(xpath="//a[contains(@class, 'nav-link') and contains(text(), 'Register')]")
    WebElement Register_link;

    @FindBy(css = "button.btn.mattgreen-color-bg.mx-2.ng-star-inserted")
    WebElement Login_button;

    public void click_Registration_link(){

        Register_link.click();
    }

    public void click_Login_link(){

        Login_button.click();
    }

    public void click_EN_button(){

       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        //wait.until(ExpectedConditions.visibilityOf(en_btn));
        en_btn.click();
    }
}
