package SeleniumTests;

import java.util.HashMap;

import SeleniumPages.HomePage;
import SeleniumPages.LoginPage;
import SeleniumPages.PageUrls;
import org.openqa.selenium.WebDriver;
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
      HashMap<String, String> userInfo = createUserInfo("Krsta ", "Krstic", "krle" + getCurrentTimeAsUniqueId(),
                                                        "krle"+getCurrentTimeAsUniqueId()+"@email.com", password, "123456789");
      signUp(driver, userInfo);
      HomePage homePage = new HomePage(driver);
      LoginPage loginPage = homePage.clickLoginLink();
      loginPage.enterValidCredentialsAndLogin(userInfo.get("userName"), password);
      loginPage.verifyPageUrl(PageUrls.login);
      driver.quit();
    }finally {
      quitDriver(driver);
    }

  }





}
