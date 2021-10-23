package SeleniumTests;

import java.util.HashMap;

import SeleniumPages.HomePage;
import SeleniumPages.LoginPage;
import SeleniumPages.MessagePage;
import SeleniumPages.PageUrls;
import SeleniumPages.Strings;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {

  /**
   * 1. Navigate to register page
   * 2. Enter valid credentials in all fields
   * 3. Sign up
   * 4. Navigate to login page
   * 5. Enter username and password from step 2. Click login button
   *
   * Expected result:
   * 3. Verify that user is registered. Success message "User has been successfully registered." is shown
   * 5. Verify that user is logged in
   */
  @Test
  public void signUpThenLoginPositiveScenario() {
    WebDriver driver = openChromeDriver();
    try {
      log.debug("[TEST] Sign up new user");
      HashMap<String, String> userInfo = createUserInfo("Krsta ", "Krstic", "krle" + DateTimeUtils.getCurrentTimeAsUniqueId(),
                                                        "krle"+DateTimeUtils.getCurrentTimeAsUniqueId()+"@email.com", password, "123456789");
      signUp(driver, userInfo);

      log.debug("[TEST] Navigate to login page");
      HomePage homePage = new HomePage(driver);
      LoginPage loginPage = homePage.clickLoginLink();

      log.debug("[TEST] Enter valid username and password. Click login button");
      MessagePage messagePage = loginPage.enterCredentialsAndLogin(userInfo.get("userName"), password);
      messagePage.verifyPageUrl(PageUrls.login);
      String currentMessage = messagePage.getMessage();
      assert currentMessage.isEmpty() : "There should be no message for valid login. But there is: " + currentMessage;
      driver.quit();
    }finally {
      quitDriver(driver);
    }

  }

  /**
   * 1. Navigate to register page
   * 2. Enter valid credentials in all fields
   * 3. Sign up
   * 4. Navigate to login page
   * 5. Enter invalid username and valid password from step 2. Click login button
   * 6. Enter valid username and invalid password from step 2. Click login button
   * Expected result:
   * 3. Verify that user is registered. Success message "User has been successfully registered." is shown
   * 5. Verify that user is NOT logged in. Error message "Invalid Email. Try again." is shown
   * 6. Verify that user is NOT logged in. Error message "Invalid Email. Try again." is shown
   */
  @Test
  public void signUpThenTryLoginWithInvalidCredentials() {
    WebDriver driver = openChromeDriver();
    String invalidUsername = "invaliduser";
    String invalidPassword = "invalidpass";
    try {
      log.debug("[TEST] Sign up new user");
      HashMap<String, String> userInfo = createRandomUserInfo();
      signUp(driver, userInfo);

      log.debug("[TEST] Navigate to login page");
      HomePage homePage = new HomePage(driver);
      LoginPage loginPage = homePage.clickLoginLink();

      log.debug("[TEST] Enter invalid username. Click login button");
      MessagePage messagePage = loginPage.enterCredentialsAndLogin(invalidUsername, password);
      messagePage.verifyPageUrl(PageUrls.login);
      String currentMessage = messagePage.getMessage();
      log.debug("[TEST] Verify error message");
      assert currentMessage.equals(Strings.invalidUserEmail) : "Wrong Message. Expected: " + Strings.invalidUserEmail + " Actual: " + currentMessage;
      loginPage = messagePage.clickLoginLink();

      log.debug("[TEST] Enter invalid password. Click login button");
      messagePage = loginPage.enterCredentialsAndLogin(userInfo.get("userName"), invalidPassword);
      messagePage.verifyPageUrl(PageUrls.login);
      log.debug("[TEST] Verify error message");
      currentMessage = messagePage.getMessage();
      assert currentMessage.equals(Strings.invalidUserEmail) : "Wrong Message. Expected: " + Strings.invalidUserEmail + " Actual: " + currentMessage;
      driver.quit();
    }finally {
      quitDriver(driver);
    }
  }


}
