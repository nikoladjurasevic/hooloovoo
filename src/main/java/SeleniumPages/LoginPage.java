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
    log.debug("[TEST] enterUserName(" + username + ")");
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
    log.debug("[TEST] enterPassword(" + password + ")");
    passwordField.clear();
    passwordField.sendKeys(password);
    return this;
  }

  /**
   * Method for clicking login button
   * @return
   */
  public MessagePage clickLoginButton() {
    log.debug("clickLoginButton()");
    loginButton.click();
    log.debug("[TEST] Navigate to Message Page");
    return new MessagePage(driver);
  }

  /**
   * Method for entering credentials and clicking Login
   * @param username
   * @param password
   * @return
   */
  public MessagePage enterCredentialsAndLogin(String username, String password) {
    log.debug("[TEST] enterCredentialsAndLogin()");
    enterUserName(username);
    enterPassword(password);
    MessagePage messagePage = clickLoginButton();
    return messagePage;
  }


}
