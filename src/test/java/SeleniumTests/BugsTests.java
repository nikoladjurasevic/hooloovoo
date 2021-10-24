package SeleniumTests;

import java.util.HashMap;

import SeleniumPages.HomePage;
import SeleniumPages.LoginPage;
import SeleniumPages.MessagePage;
import SeleniumPages.PageUrls;
import SeleniumPages.SignUpPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class BugsTests extends BaseTest {

  /**
   * Login button on homepage is not working
   *
   * Priority: Highest
   * Severity: Highest
   *
   * Steps to reproduce
   * 1. Navigate to http://localhost:8080/#
   * 2. Enter valid username and password. Click "Login" button (button is first one below text fields)
   *
   * Expected result:
   * User should be logged in
   *
   * Actual result
   * Nothing happens . User is still on home page
   *
   * NOTE:
   * Workaroud: User can login via login page (page can be accessed by cllicking th "Log In" link - gray field below "Loign" button)
   */
  @Test
  public void testLoginFromHomePage() {
    WebDriver driver = openChromeDriver();
    try {
      HashMap<String, String> userInfo = createRandomUserInfo();
      signUp(driver, userInfo);
      HomePage homePage = new HomePage(driver);
      homePage.enterUserName(userInfo.get("userName"));
      homePage.enterPassword(userInfo.get("password"));
      MessagePage messagePage = homePage.clickLoginButton();
      messagePage.verifyPageUrl(PageUrls.adduser);
      driver.quit();
    } finally {
      quitDriver(driver);
    }
  }

  /**
   * Wrong error message when entering invalid username or password
   * Priority: High
   * Severity: Minor
   *
   *Steps to reproduce
   * 1. Navigate to home page
   * 2. Click on the "log in" link
   * 3. On login page enter invalid username or password or both
   *
   * Expected result
   * 3. Error message should be shown "Wrong username or password. Try again."
   *
   * Actual result:
   * 3. Misleading Error message sis shown "Invalid Email. Try again."
   */
  @Test
  public void testWrongErrorMessageWhenEnteringInvalidCredentials() {
    WebDriver driver = openChromeDriver();
    try {
      HashMap<String, String> userInfo = createRandomUserInfo();
      signUp(driver, userInfo);
      HomePage homePage = new HomePage(driver);
      LoginPage loginPage = homePage.clickLoginLink();
      loginPage.enterUserName("invalidusername");
      loginPage.enterPassword("invalidpassword");
      MessagePage messagePage = loginPage.clickLoginButton();
      String actualMessage = messagePage.getMessage();
      assert actualMessage.equals("Wrong username or password. Try again.") : "Wrong error message. Expected: 'Wrong username or password. Try again.'. Actual: " +actualMessage;;
      driver.quit();
    } finally {
      quitDriver(driver);
    }
  }

  /**
   * Social Media login buttons are not working
   * Priority: High
   * Severity: Normal
   *
   *Steps to reproduce
   * 1. Navigate to home page
   * 2. Click on the Facebook login button
   *
   * Expected result
   * 2. Login page for facebook should appear
   *
   * Actual result:
   * 2. User stays on home page, nothing happens
   */
  @Test
  public void testFacebooklLoginNotWorking() {
    WebDriver driver = openChromeDriver();
    try {
      HomePage homePage = new HomePage(driver);
      homePage.clickLoginWithFacebookButton();
      homePage.verifyPageUrl(PageUrls.login);
      driver.quit();
    } finally {
      quitDriver(driver);
    }
  }

  /**
   * Social Media login buttons are not working
   * Priority: High
   * Severity: Normal
   *
   * Steps to reproduce
   * 1. Navigate to home page
   * 2. Click on the Twitter login button
   *
   * Expected result
   * 2. Login page for Twitter should appear
   *
   * Actual result:
   * 2. User stays on home page, nothing happens
   */
  @Test
  public void testTwitterLoginNotWorking() {
    WebDriver driver = openChromeDriver();
    try {
      HomePage homePage = new HomePage(driver);
      homePage.clickLoginWithTwitterButton();
      homePage.verifyPageUrl(PageUrls.login);
      driver.quit();
    } finally {
      quitDriver(driver);
    }
  }
  /**
   * Social Media login buttons are not working
   * Priority: High
   * Severity: Normal
   *
   * Steps to reproduce
   * 1. Navigate to home page
   * 2. Click on the Google+ login button
   *
   * Expected result
   * 2. Login page for Google+ should appear
   *
   * Actual result:
   * 2. User stays on home page, nothing happens
   */
  @Test
  public void testGooglePluslLoginNotWorking() {
    WebDriver driver = openChromeDriver();
    try {
      HomePage homePage = new HomePage(driver);
      homePage.clickLoginWithGooglePlustButton();
      homePage.verifyPageUrl(PageUrls.login);
      driver.quit();
    } finally {
      quitDriver(driver);
    }
  }

  /**
   * Username and password are passed in the url when clicking login button
   *
   * Priority: Highest
   * Severity: Highest
   *
   * Steps to reproduce
   * 1. Navigate to Home page
   * 2. Enter valid username and password
   * 3. Click login button
   *
   * Actual result:
   * Url shows usarname and password as querry parameters
   *
   * Expected result:
   * Credentials should not be passed as querry parameters
   */
  @Test
  public void testCredentialsAreNotPassedAsParametersInURL() {
    WebDriver driver = openChromeDriver();
    try {
      HashMap<String, String> userInfo = createRandomUserInfo();
      signUp(driver, userInfo);
      String userName = userInfo.get("userName");
      String password = userInfo.get("password");
      HomePage homePage = new HomePage(driver);
      homePage.enterUserName(userName);
      homePage.enterPassword(password);
      homePage.clickLoginButton();
      String currentUrl = driver.getCurrentUrl();
      assert !currentUrl.contains("username="+userName) : "Username is contained in url: " + currentUrl;
      assert !currentUrl.contains("password="+password) : "Username is contained in url: " + currentUrl;
      driver.quit();
    } finally {
      quitDriver(driver);
    }
  }

  /**
   * Cancel button on Sign up page is not working
   *
   * Priority: Normal
   * Severity: Minor
   *
   * Steps to reproduce:
   * 1. From home page click Sign up link
   * 2. On Sign up page click Cancel button
   *
   * Actual result:
   * Nothing happens. User is still on the same page
   *
   * Expected result:
   * User should be redirected back to home page
   */
  @Test
  public void testCancelButtonOnSignUpPageIsNotWorking() {

    WebDriver driver = openChromeDriver();
    try {
      HashMap<String, String> userInfo = createRandomUserInfo();
      HomePage homePage = new HomePage(driver);
      SignUpPage signUpPage = homePage.clickSignUpLink();
      signUpPage.fillInSignUpForm(userInfo);
      homePage = signUpPage.clickCancelButton();
      homePage.verifyPageUrl(PageUrls.homeUrl);
      driver.quit();
    } finally {
      quitDriver(driver);
    }
  }

  /**
   * Sign up page title is 'insert title here'
   *
   * Priority: Minor
   * Severity: Minor
   *
   * Steps to reproduce:
   * 1. From home page click Sign up link
   *
   * Actual result:
   * Page title is 'Insert title here'
   *
   * Expected result:
   * page title should be something like "Sign up page title "
   */
 @Test
 public void testSignUpPageHasNoTitle() {
   WebDriver driver = openChromeDriver();
   try {
     HashMap<String, String> userInfo = createRandomUserInfo();
     HomePage homePage = new HomePage(driver);
     homePage.clickSignUpLink();
     String actualTitle = driver.getTitle();
     assert actualTitle.equals("Sign up page") : "Wrong page title. Expected: 'Sign up page'. Actual: " +actualTitle;
     driver.quit();
   } finally {
     quitDriver(driver);
   }
 }
  /**
   * None of the fields on Sign up page have data validation
   *
   * Priority: Major
   * Severity: Normal
   *
   * Steps to reproduce:
   * 1. From home page click Sign up link
   * 2. On Sign up page enter data in wrong format e.g. for first name put same numbers, email not in proper format ...
   * 3. Click sign up button
   *
   * Actual result:
   * User is registerd
   *
   * Expected result:
   * There should be a error message showing which fields do not have data in proper format
   */
  @Test
  public void testSignUpPageHasNoFieldValidation() {
    String uniqueId = String.valueOf(DateTimeUtils.getCurrentTimeAsUniqueId());
    WebDriver driver = openChromeDriver();
    try {
      HashMap<String, String> userInfo = createUserInfo(uniqueId, uniqueId, uniqueId, uniqueId, uniqueId, "abcdefg" );
      HomePage homePage = new HomePage(driver);
      SignUpPage signUpPage = homePage.clickSignUpLink();
      signUpPage.fillInSignUpForm(userInfo);
      signUpPage.clickSignUpButton();
      signUpPage.verifyPageUrl(PageUrls.signUp);
      driver.quit();
    } finally {
      quitDriver(driver);
    }
  }



}
