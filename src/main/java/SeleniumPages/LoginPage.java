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

  /**
   * Method for entering username
   * @param username
   * @return
   */
  public LoginPage enterUserName(String username) {
    usernameField.clear();
    usernameField.sendKeys(username);
    return this;
  }

  /**
   * Method for entering password
   * @param password
   * @return
   */
  public LoginPage enterPassword(String password) {
    passwordField.clear();
    passwordField.sendKeys(password);
    return this;
  }

  /**
   * Method for clicking login button
   * @return
   */
  public LoginPage clickLoginButton() {
    loginButton.click();
    return this;
  }

  /**
   * Method for entering credentials and clicking Login
   * @param username
   * @param password
   * @return
   */
  public LoginPage enterValidCredentialsAndLogin(String username, String password) {
    enterUserName(username);
    enterPassword(password);
    clickLoginButton();
    return this;
  }


}
