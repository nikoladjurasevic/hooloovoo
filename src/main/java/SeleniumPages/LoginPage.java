package SeleniumPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

  //Locators
  @FindBy(name = "username")
  WebElement usernameField;

  @FindBy(name = "password")
  WebElement passwordField;

  @FindBy(xpath = "//button")
  WebElement loginButton;



  public LoginPage(WebDriver driver) {
    super(driver);
  }

  //Methods
  public LoginPage enterUserName(String username) {
    usernameField.clear();
    usernameField.sendKeys(username);
    return this;
  }

  public LoginPage enterPassword(String password) {
    passwordField.clear();
    passwordField.sendKeys(password);
    return this;
  }

  public LoginPage clickLoginButton() {
    loginButton.click();
    return this;
  }

  public LoginPage enterValidCredentialsAndLogin(String username, String password) {
    enterUserName(username);
    enterPassword(password);
    clickLoginButton();
    return this;
  }


}
