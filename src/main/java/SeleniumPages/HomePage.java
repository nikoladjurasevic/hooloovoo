package SeleniumPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


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

  @FindBy(xpath = "//a[@class = 'fb btn']")
  WebElement facebookLoginButton;

  @FindBy(xpath = "//a[@class = 'twitter btn']")
  WebElement twitterLoginButton;

  @FindBy(xpath = "//a[@class = 'google btn']")
  WebElement googleplusLoginButton;
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
  public HomePage enterPassword(String password) {
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
    log.debug("[TEST] clickLoginButton");
    loginButton.click();
    return new MessagePage(driver);
  }

  /**
   * Method for clicking login link
   * @return
   */
  public LoginPage clickLoginLink() {
    log.debug("[TEST] clickLoginLink");
    clickOnElement(logInLink);
    return new LoginPage(driver);
  }

  /**
   * Method for clicking Login button but expecting error and staying on login page
   * @return
   */
  public HomePage clickLoginButtonFailure() {
    log.debug("[TEST] clickLoginButtonFailure()");
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
    log.debug("[TEST] loginWithCredentials()");
    enterUserName(username);
    enterPassword(password);
    return clickLoginButtonFailure();
  }

  /**
   * Method for clicking Sign up link
   * @return
   */
  public SignUpPage clickSignUpLink() {
    log.debug("[TEST] clickSignUpLink()");
    clickOnElement(signUpLink);
    log.debug("[TEST] Navigate to Sign Up page");
    return new SignUpPage(driver);
  }

  /**
   * Method for clicking login with facebook button
   * @return
   */
  public LoginPage clickLoginWithFacebookButton() {
    log.debug("[TEST] clickLoginWithFacebookButton()");
    clickOnElement(facebookLoginButton);
    log.debug("[TEST] Navigate to Login page");
    return new LoginPage(driver);
  }

  /**
   * Method for clicking login with twitter button
   * @return
   */
  public LoginPage clickLoginWithTwitterButton() {
    log.debug("[TEST] clickLoginWithTwitterButton()");
    clickOnElement(twitterLoginButton);
    log.debug("[TEST] Navigate to Login page");
    return new LoginPage(driver);
  }

  /**
   * Method for clicking login with google+ button
   * @return
   */
  public LoginPage clickLoginWithGooglePlustButton() {
    log.debug("[TEST] clickLoginWithGooglePlustButton()");
    clickOnElement(googleplusLoginButton);
    log.debug("[TEST] Navigate to Login page");
    return new LoginPage(driver);
  }

}
