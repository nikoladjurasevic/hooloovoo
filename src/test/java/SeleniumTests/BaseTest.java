package SeleniumTests;

import java.io.File;
import java.util.Date;
import java.util.HashMap;

import SeleniumPages.HomePage;
import SeleniumPages.MessagePage;
import SeleniumPages.PageUrls;
import SeleniumPages.SignUpPage;
import SeleniumPages.Strings;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Reporter;

public class BaseTest {
  static final Logger log = LogManager.getLogger(BaseTest.class);

  //Generating data for user info
  Date date = new Date();
  long currentTime = date.getTime();
  static final String password = "password";

  /**
   * Creating driver instance and navigating to home page
   * @return driver
   */
  WebDriver openChromeDriver() {
    log.debug("Setting up Chrome driver");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");
    options.addArguments("--ignore-certificate-errors");
    options.addArguments("--disable-popup-blocking");
    options.addArguments("--incognito");
    String path = System.getProperty("user.dir");
    String separator = System.getProperty("file.separator");
    System.setProperty("webdriver.chrome.driver", path+separator+"chromedriver.exe");
    ChromeDriver driver = new ChromeDriver(options);
    log.debug("Opening Chrome driver");
    driver.get(PageUrls.homeUrl);
    log.debug("Navigating to " + PageUrls.homeUrl);
    return  driver;
  }

  /**
   *   HardCoded sleep
   */
  public void sleepSeconds(int iSeconds){
    try{
//      log.debug("Sleep for " + Integer.toString(iSeconds) + " seconds");
      Thread.sleep(iSeconds*1000);
    }
    catch (Exception e) {
      log.debug(e.getStackTrace().toString());
    }
  }

  /**
   * quit driver method for failing tests
   * @param driver
   */
  public void quitDriver(WebDriver driver) {
    if (driver!=null) {
      try {
        takeScreenshot(driver, getClass().toString()+currentTime);
        driver.quit();
      } catch (Throwable t) {
        log.debug(t.getMessage());
      }
    }
  }

  /**
   * take screenshot method
   * @param driver
   * @param fileName
   * @throws Exception
   */
  public void takeScreenshot(WebDriver driver, String fileName) throws Exception {
    TakesScreenshot scrShot = ((TakesScreenshot) driver);
    File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
    String separator = System.getProperty("file.separator");
    String path = System.getProperty("user.dir");
    String destFilePath = path + separator + "src" + separator + "test" + separator + "java" + separator + "Output";
    File DestFile = new File(destFilePath + separator + fileName + ".png");
    FileUtils.copyFile(SrcFile, DestFile);
    log.debug("Screenshot generated at" + DestFile.toString());
    log.info("Please open emailable-report.html using Chrome browser");
    Reporter.log("<br><img src='"+ DestFile.getAbsolutePath() +"' height='600' width='800'/><br>");
  }

  /**
   * Method for creating user info for sign up form
   * @param firstName1
   * @param firstName2
   * @param userName
   * @param email
   * @param password
   * @param mobile
   * @return
   */
  public HashMap<String, String> createUserInfo(String firstName1, String firstName2, String userName, String email, String password, String mobile) {
    HashMap<String, String> userInfo = new HashMap<String, String>();
    userInfo.put("firstName1", firstName1);
    userInfo.put("firstName2", firstName2);
    userInfo.put("userName", userName);
    userInfo.put("email", email);
    userInfo.put("password", password);
    userInfo.put("mobile", mobile);

    userInfo.entrySet().forEach(entry -> {
      log.info(entry.getKey() + " : " + entry.getValue());
    });

    return  userInfo;
  }

  /**
   * Method for creating random user
   * @return
   */
  public HashMap<String, String> createRandomUserInfo() {
    String firstName1 = "prviFirstName";
    String firstName2 ="drugiFirstName";
    String userName = "lastName" + currentTime;
    String email = "email" + currentTime + "@email.com";
    String mobile = "1234567890";
    return createUserInfo(firstName1, firstName2, userName, email, password, mobile );
  }

  /**
   * Method to sign up user and return his email
   * @param driver
   * @param userInfo
   * @return user name
   */
  public String signUp(WebDriver driver, HashMap<String, String> userInfo) {
    HomePage homePage = new HomePage(driver);
    SignUpPage signUpPage = homePage.clickSignUpLink();
    signUpPage.fillInSignUpForm(userInfo);
    MessagePage messagePage = signUpPage.clickSignUpLink();
    messagePage.verifyPageUrl(PageUrls.adduser);
    String actualMessage = messagePage.getMessage();
    assert actualMessage.equals(Strings.userAddedSuccess) : "Wrong message. Expected: " + Strings.userAddedSuccess + " . Actual: " + actualMessage;
    messagePage.clickHomeLink();
    return userInfo.get("userName");
  }

  /**
   * Method to sign up random user
   * @param driver
   * @return
   */
  public String signUpRandomUser(WebDriver driver) {
    HashMap<String, String> radomUserInfo = createRandomUserInfo();
    return signUp(driver, radomUserInfo);
  }

}
