package SeleniumTests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import SeleniumPages.LoginPage;
import SeleniumPages.HomePage;
import SeleniumPages.MessagePage;
import SeleniumPages.PageUrls;
import SeleniumPages.SignUpPage;
import SeleniumPages.Strings;
import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SignUpTests extends BaseTest{

  /**
   * Sign up new user with valid data
   *
   * 1. Navigate to home page
   * 2. From Home Page click on SignUp Link
   * 3. Enter valid data into fields and click Sign Up button
   *
   * Expected result:
   * 2. Sign up page is shown
   * 3. Verify that success message is shown "User has been successfully registered."
   */
  @Test
  public void signUpTestPositiveScenario() {
    WebDriver driver = openChromeDriver();
    try {
      log.debug("[TEST] Sign up new user");
      HashMap<String, String> userInfo = createUserInfo("Petar", "Petrovic", "pera" + DateTimeUtils.getCurrentTimeAsUniqueId(),
                                                                 "pera"+DateTimeUtils.getCurrentTimeAsUniqueId()+"@email.com", password, "123456789");
      signUp(driver, userInfo);
      driver.quit();
    } finally {
      quitDriver(driver);
    }
  }

  /**
   * Test for creating user with randomized username and email
   */
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

  /**
   * Sign up new user and then login
   *
   * 1. Navigate to Home page
   * 2. Click on the SIgn up link
   * 3. Enter valid data into fields and click Sign up
   * 4. CLick on HomePage link
   * 5. Click on the Log in link
   * 6. Enter username and password from step 3
   *
   * Expected results:
   * 3. Verify that user is create and success message is shown
   * 4. Verify that you are navigated back to Home page
   * 5. Verify that you are naviagate to Login page
   * 6. Verify that user is logged in
   */
  @Test
  public void signUpThenLoginPositiveScenarioRandomUserInfo() {
    WebDriver driver = openChromeDriver();
    try {
      String userName = signUpRandomUser(driver);
      HomePage homePage = new HomePage(driver);
      LoginPage loginPage = homePage.clickLoginLink();
      loginPage.enterCredentialsAndLogin(userName, password);
      loginPage.verifyPageUrl(PageUrls.login);
      driver.quit();
    }finally {
      quitDriver(driver);
    }
  }

  /**
   * Try to sign up user with same credentials again
   *
   * 1. Sign up user with valid data
   * 2. Try to sign up user with same data from step 1.
   *
   * Expected result:
   * 2. Error message is shown "Oops! There is already a user registered with the email provided."
   */
  @Test
  public void signUpTestSameEmailUsedTwice() {
    WebDriver driver = openChromeDriver();
    try {
      HashMap<String, String> userInfo = createUserInfo("Milan", "Milanovic", "mile" + DateTimeUtils.getCurrentTimeAsUniqueId(),
                                                        "mile"+DateTimeUtils.getCurrentTimeAsUniqueId()+"@email.com", password, "123456789");
      signUp(driver, userInfo);
      HomePage homePage = new HomePage(driver);
      SignUpPage signUpPage = homePage.clickSignUpLink();
      signUpPage.fillInSignUpForm(userInfo);
      MessagePage messagePage = signUpPage.clickSignUpButton();
      messagePage.verifyPageUrl(PageUrls.adduser);
      String actualMessage = messagePage.getMessage();
      assert actualMessage.equals(Strings.userAddedExists) : "Wrong message. Expected: " + Strings.userAddedExists + " . Actual: " + actualMessage;
      driver.quit();

    } finally {
      quitDriver(driver);
    }
  }

  /**
   * Test to sign up new users with provided data
   * @param firstName1
   * @param firstName2
   * @param userName
   * @param email
   * @param password
   * @param mobile
   */

  @Test(dataProvider = "testData")
  public void signUptWithData(String firstName1, String firstName2, String userName, String email, String password , String mobile) {
    WebDriver driver = openChromeDriver();
    try {
      HashMap<String, String> userInfo = createUserInfo(firstName1, firstName2, userName, email, password, mobile);
      signUp(driver, userInfo);
      driver.quit();
    }finally {
      quitDriver(driver);
    }
  }

  /**
   * test Data
   * @return
   */
  @DataProvider(name = "testData")
  public Object[][] testData() {
    return new Object[][] {
            {"Petar", "Petrovic", "pera" + DateTimeUtils.getCurrentTimeAsUniqueId(), "pera"+DateTimeUtils.getCurrentTimeAsUniqueId()+"@email.com", password, "123456789"},
            {"Jovan", "Jovanovic", "jova" + DateTimeUtils.getCurrentTimeAsUniqueId(), "jova"+DateTimeUtils.getCurrentTimeAsUniqueId()+"@email.com", password, "987654321"}
    };
  }

  /**
   * Test with data provided from CSV file
   * @param firstName1
   * @param firstName2
   * @param userName
   * @param email
   * @param password
   * @param mobile
   */
  @Test(dataProvider = "testDataFromCSV")
  public void signUptWithCSVData(String firstName1, String firstName2, String userName, String email, String password , String mobile) {
    WebDriver driver = openChromeDriver();
    try {
      HashMap<String, String> userInfo = createUserInfo(firstName1, firstName2, userName, email, password, mobile);
      signUp(driver, userInfo);
      driver.quit();
    }finally {
      quitDriver(driver);
    }
  }

  /**
   * Test data from CSV file
   * @return
   * @throws IOException
   * @throws CsvValidationException
   */
  @DataProvider(name = "testDataFromCSV")
  public Object[][] testDataFromCSV() throws IOException, CsvValidationException {

    File csvFile = new File("src/test/java/SeleniumTests/data.csv");
    int rows = 0;
    CSVReader csvReader = new CSVReader(Files.newReader(csvFile,Charsets.UTF_8));
    while((csvReader.readNext()) != null) {
      rows++;
    }
    csvReader.close();

    Object object[][] = new Object[rows][5];
    csvReader = new CSVReader(Files.newReader(csvFile,Charsets.UTF_8));
    for(int i=0; i<rows; i++) {
      String[] row = csvReader.readNext();
      object[i] = row;
    }
    csvReader.close();
    return object;
  }

  /**
   * Try to sign up user with missing data on Sign up page
   *
   * 1. From Home page navigate to Sign up Page
   * 2. On Sign up Page enter some data (not all)
   * 3. Click Sign up button
   *
   * Expected result:
   * 3. Verify that user is not created and you are still on register user page
   *
   * @param firstName1
   * @param firstName2
   * @param userName
   * @param email
   * @param password
   * @param mobile
   */
  @Test(dataProvider = "missingData")
  public void signUptWithMissingData(String firstName1, String firstName2, String userName, String email, String password , String mobile) {
    WebDriver driver = openChromeDriver();
    try {
      HashMap<String, String> userInfo = createUserInfo(firstName1, firstName2, userName, email, password, mobile);
      HomePage homePage = new HomePage(driver);
      SignUpPage signUpPage = homePage.clickSignUpLink();
      signUpPage.fillInSignUpForm(userInfo);
      signUpPage.clickSignUpButtonButStayOnPage();
      assert driver.getCurrentUrl().equals(PageUrls.signUp) : "Wrong page. Expected" + PageUrls.signUp + " . Actual: " + driver.getCurrentUrl();
      driver.quit();
    }finally {
      quitDriver(driver);
    }
  }

  @DataProvider(name = "missingData")
  public Object[][] missingData() {
    return new Object[][] {
            {"", "Petrovic", "pera" + DateTimeUtils.getCurrentTimeAsUniqueId(), "pera"+DateTimeUtils.getCurrentTimeAsUniqueId()+"@email.com", password, "123456789"},
            {"Petar", "", "pera" + DateTimeUtils.getCurrentTimeAsUniqueId(), "pera"+DateTimeUtils.getCurrentTimeAsUniqueId()+"@email.com", password, "123456789"},
            {"Petar", "Petrovic", "", "pera"+DateTimeUtils.getCurrentTimeAsUniqueId()+"@email.com", password, "123456789"},
            {"Petar", "Petrovic", "pera" + DateTimeUtils.getCurrentTimeAsUniqueId(), "", password, "123456789"},
            {"Petar", "Petrovic", "pera" + DateTimeUtils.getCurrentTimeAsUniqueId(), "pera"+DateTimeUtils.getCurrentTimeAsUniqueId()+"@email.com", "", "123456789"},
            {"Petar", "Petrovic", "pera" + DateTimeUtils.getCurrentTimeAsUniqueId(), "pera"+DateTimeUtils.getCurrentTimeAsUniqueId()+"@email.com", password, ""},
            //and all of other combinations

    };
  }

}
