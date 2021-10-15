package SeleniumTests;

import java.util.HashMap;
import SeleniumPages.LoginPage;
import SeleniumPages.MessagePage;
import SeleniumPages.HomePage;
import SeleniumPages.PageUrls;
import SeleniumPages.SignUpPage;
import SeleniumPages.Strings;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class SignUpTests extends BaseTest{

  @Test
  public void test1(){
    WebDriver driver =  openChromeDriver();
    try {
      HomePage homePage = new HomePage(driver);
      sleepSeconds(1);
      homePage.enterUserName("dadfa");
      homePage.enterPassword("adfadf");
      homePage.clickLoginButtonFailure();
      sleepSeconds(1);
      driver.quit();
    } finally {
      quitDriver(driver);
    }

  }

  @Test
  public void signUpTestPositiveScenario() {
    WebDriver driver = openChromeDriver();
    try {
      HashMap<String, String> userInfo = createUserInfo("Petar", "Petrovic", "pera" + currentTime,
                                                                 "pera"+currentTime+"@email.com", password, "123456789");
      signUp(driver, userInfo);
      driver.quit();
    } finally {
      quitDriver(driver);
    }
  }

  @Test
  public void signUpThenLoginPositiveScenarioRandomUserInfo() {
    WebDriver driver = openChromeDriver();
    try {
      String userName = signUpRandomUser(driver);
      HomePage homePage = new HomePage(driver);
      LoginPage loginPage = homePage.clickLoginLink();
      loginPage.enterValidCredentialsAndLogin(userName, password);
      loginPage.verifyPageUrl(PageUrls.login);
      driver.quit();
    }finally {
      quitDriver(driver);
    }
  }

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
