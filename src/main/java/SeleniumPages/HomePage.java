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

  public HomePage(WebDriver driver) {
    super(driver);
    verifyPageUrl(PageUrls.homeUrl);
    this.driver = driver;
  }

  //Methods
  public HomePage enterUserName(String username) {
    usernameField.clear();
    usernameField.sendKeys(username);
    return this;
  }

  public HomePage enterPassword(String password) {
    passwordField.clear();
    passwordField.sendKeys(password);
    return this;
  }

  public MessagePage clickLoginButton() {
    loginButton.click();
    return new MessagePage(driver);
  }

  public LoginPage clickLoginLink() {
    clickOnElement(logInLink);
    return new LoginPage(driver);
  }

  public HomePage clickLoginButtonFailure() {
    loginButton.click();
    return this;
  }

  public HomePage loginWithCredentials(String username, String password) {
    enterUserName(username);
    enterPassword(password);
    return clickLoginButtonFailure();
  }

  public SignUpPage clickSignUpLink() {
    clickOnElement(signUpLink);
    return new SignUpPage(driver);
  }


}
