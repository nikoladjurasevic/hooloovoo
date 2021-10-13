package SeleniumPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignUpPage extends BasePage {

  //Locators
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

}
