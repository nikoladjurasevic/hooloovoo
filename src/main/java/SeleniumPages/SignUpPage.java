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

  @FindBy(className = "cancelbtn")
  WebElement cancelButton;
  public SignUpPage(WebDriver driver) {
    super(driver);
  }

  //Methods

  /**
   * Method for entering text in last name field
   * @param text
   */
   public void enterTextInLastNameField(String text) {
    enterTextInfField(lastNameField,text);
   }

  /**
   * Method for entering text in email field
   * @param text
   */
  public void enterTextInEmailNField(String text) {
    enterTextInfField(emailField,text);
  }

  /**
   * Method for entering text in password field
   * @param text
   */
  public void enterTextInPasswordField(String text) {
    enterTextInfField(passwordField,text);
  }
  /**
   * Method for entering text in mobile field
   * @param text
   */
  public void enterTextInMobileField(String text) {
    enterTextInfField(mobileField,text);
  }
  /**
   * Method for entering text in first first name field
   * @param text
   */
  public void enterTextInFIRSTFirstNameField(String text) {
    enterTextInfField(firstFirstNameField,text);
  }
  /**
   * Method for entering text in second first name field
   * @param text
   */
  public void enterTextInSECONDFirstNameField(String text) {
    enterTextInfField(secondFirstNameField,text);
  }

  /**
   * Method for filling up Sign up form
   * @param userInfo
   * @return
   */
  public SignUpPage fillInSignUpForm(HashMap<String, String> userInfo) {
    enterTextInFIRSTFirstNameField(userInfo.get("firstName1"));
    enterTextInSECONDFirstNameField(userInfo.get("firstName2"));
    enterTextInLastNameField(userInfo.get("userName"));
    enterTextInEmailNField(userInfo.get("email"));
    enterTextInPasswordField(userInfo.get("password"));
    enterTextInMobileField(userInfo.get("mobile"));
    return this;
  }

  /**
   * Method for clicking Sign up link
   * @return
   */
  public MessagePage clickSignUpButton() {
    log.debug("[TEST] clickSignUpButton()");
    clickOnElement(signUpButton);
    return new MessagePage(driver);
  }

  /**
   * Method for clicking Sign up link but expecting error and staying on same page
   * @return
   */
  public SignUpPage clickSignUpButtonButStayOnPage() {
    log.debug("[TEST] clickSignUpButtonButStayOnPage()");
    clickOnElement(signUpButton);
    return this;
  }

  /**
   * Method for clicking Cancel button
   * @return
   */
  public HomePage clickCancelButton() {
    log.debug("[TEST] clickCancelButton()");
    clickOnElement(cancelButton);
    log.debug("[TEST] Navigate to Home page");
    return new HomePage(driver);
  }

}
