package SeleniumTests;

import java.util.HashMap;
import SeleniumPages.LoginPage;
import SeleniumPages.HomePage;
import SeleniumPages.PageUrls;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignUpTests extends BaseTest{

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
  public void signUpTestPositiveScenarioRandomUserInfo() {
    WebDriver driver = openChromeDriver();
    try {
      HashMap<String, String> userInfo = createRandomUserInfo();
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

  @Test(dataProvider = "testData")
  public void signUptWithData(String firstName1, String firstName2, String userName, String email, String password , String mobile) {
    WebDriver driver = openChromeDriver();
    try {
      HashMap<String, String> userInfo = createUserInfo(firstName1, firstName2, userName, email, password, mobile);
      signUp(driver, userInfo);
    }finally {
      quitDriver(driver);
    }
  }

  @DataProvider(name = "testData")
  public Object[][] tesData() {
    return new Object[][] {
            {"Petar", "Petrovic", "pera" + currentTime, "pera"+currentTime+"@email.com", password, "123456789"},
            {"Jovan", "Jovanovic", "jova" + currentTime, "jova"+currentTime+"@email.com", password, "987654321"}
    };
  }

  @DataProvider(name = "testDataFromCSV")
  public Object[][] testDataFromCSV() {
//TODO
    return new Object[][] {};
  }

}
