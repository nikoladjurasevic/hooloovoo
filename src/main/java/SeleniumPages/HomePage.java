package SeleniumPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;





public class HomePage extends BasePage{

  //Locators
  @FindBy(name = "username")
  WebElement usernameField;

  @FindBy(name = "password")
  WebElement passwordField;

  @FindBy(xpath = "//input[@value='Login']")
  WebElement loginButton;

  @FindBy(xpath = "//a[@href='signup']")
  WebElement signUpLink;

  @FindBy(xpath = "//a[@href='login']")
  WebElement logInLink;

  /**
   * Constructor
   */
  public HomePage(WebDriver driver) {
    super(driver);
    verifyPageUrl(PageUrls.homeUrl);
    this.driver = driver;
  }

  //Methods

  /**
   * Method for entering username
   * @param username
   * @return
   */
  public HomePage enterUserName(String username) {
    usernameField.clear();
    usernameField.sendKeys(username);
    return this;
  }

  /**
   * Method for entering password
   * @param password
   * @return
   */
  public HomePage enterPassword(String password) {
    passwordField.clear();
    passwordField.sendKeys(password);
    return this;
  }

  /**
   * Method for clicking login button
   * @return
   */
  public MessagePage clickLoginButton() {
    loginButton.click();
    return new MessagePage(driver);
  }

  /**
   * Method for clicking login link
   * @return
   */
  public LoginPage clickLoginLink() {
    clickOnElement(logInLink);
    return new LoginPage(driver);
  }

  /**
   * Method for clicking Login button but expecting error and staying on login page
   * @return
   */
  public HomePage clickLoginButtonFailure() {
    loginButton.click();
    return this;
  }

  /**
   * Method for doing a login with username and password
   * @param username
   * @param password
   * @return
   */
  public HomePage loginWithCredentials(String username, String password) {
    enterUserName(username);
    enterPassword(password);
    return clickLoginButtonFailure();
  }

  /**
   * Method for clicking Sign up link
   * @return
   */
  public SignUpPage clickSignUpLink() {
    clickOnElement(signUpLink);
    return new SignUpPage(driver);
  }


}
