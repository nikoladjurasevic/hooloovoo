package SeleniumTests;

import java.util.HashMap;

import SeleniumPages.HomePage;
import SeleniumPages.LoginPage;
import SeleniumPages.MessagePage;
import SeleniumPages.PageUrls;
import SeleniumPages.SignUpPage;
import SeleniumPages.Strings;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
  @Test
  public void signUpThenLoginPositiveScenario() {
    WebDriver driver = openChromeDriver();
    try {
      HashMap<String, String> userInfo = createUserInfo("Petar", "Petrovic", "pera" + currentTime,
                                                        "pera"+currentTime+"@email.com", password, "123456789");
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

  @Test
  public void signUpTestSameEmailUsedTwice() {
    WebDriver driver = openChromeDriver();
    try {
      HashMap<String, String> userInfo = createUserInfo("Petar", "Petrovic", "pera" + currentTime,
                                                        "pera"+currentTime+"@email.com", password, "123456789");
      signUp(driver, userInfo);
      HomePage homePage = new HomePage(driver);
      SignUpPage signUpPage = homePage.clickSignUpLink();
      signUpPage.fillInSignUpForm(userInfo);
      MessagePage messagePage = signUpPage.clickSignUpLink();
      messagePage.verifyPageUrl(PageUrls.adduser);
      String actualMessage = messagePage.getMessage();
      assert actualMessage.equals(Strings.userAddedExists) : "Wrong message. Expected: " + Strings.userAddedExists + " . Actual: " + actualMessage;
      driver.quit();

    } finally {
      quitDriver(driver);
    }

  }



}
