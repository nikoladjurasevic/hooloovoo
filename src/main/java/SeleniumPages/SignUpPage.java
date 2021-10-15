package SeleniumPages;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {

  //Locators
  @FindBy(name = "username")
  WebElement lastNameField;

  @FindBy(name = "user_email")
  WebElement emailField;

  @FindBy(name = "user_pass")
  WebElement passwordField;

  @FindBy(name = "user_mobile")
  WebElement mobileField;

  @FindBy(xpath = "//label[@for='fname']/following::input[1]")
  WebElement firstFirstNameField;

  @FindBy(xpath = "//label[@for='fusername']/following::input[1]")
  WebElement secondFirstNameField;

  @FindBy(className = "signupbtn")
  WebElement signUpButton;

  public SignUpPage(WebDriver driver) {
    super(driver);
  }

  //Methods
   public void enterTextInLastNameField(String text) {
    enterTextInfField(lastNameField,text);
   }

  public void enterTextInEmailNField(String text) {
    enterTextInfField(emailField,text);
  }

  public void enterTextInPasswordField(String text) {
    enterTextInfField(passwordField,text);
  }

  public void enterTextInMobileField(String text) {
    enterTextInfField(mobileField,text);
  }

  public void enterTextInFIRSTFirstNameField(String text) {
    enterTextInfField(firstFirstNameField,text);
  }

  public void enterTextInSECONDFirstNameField(String text) {
    enterTextInfField(secondFirstNameField,text);
  }

  public SignUpPage fillInSignUpForm(HashMap<String, String> userInfo) {
    enterTextInFIRSTFirstNameField(userInfo.get("firstName1"));
    enterTextInSECONDFirstNameField(userInfo.get("firstName2"));
    enterTextInLastNameField(userInfo.get("userName"));
    enterTextInEmailNField(userInfo.get("email"));
    enterTextInPasswordField(userInfo.get("password"));
    enterTextInMobileField(userInfo.get("mobile"));
    return this;
  }

  public MessagePage clickSignUpLink() {
    clickOnElement(signUpButton);
    return new MessagePage(driver);
  }

  public SignUpPage clickSignUpLinkButStayOnPage() {
    clickOnElement(signUpButton);
    return this;
  }


}
