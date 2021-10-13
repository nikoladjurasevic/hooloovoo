package SeleniumTests;

import java.util.Date;

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
      homePage.clickLoginButton();
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
      HomePage homePage = new HomePage(driver);
      SignUpPage signUpPage = homePage.clickSignUpLink();
      signUpPage.enterTextInFIRSTFirstNameField("prviFirstName");
      signUpPage.enterTextInSECONDFirstNameField("drugiFirstName");

      signUpPage.enterTextInLastNameField("lastName" + currentTime);
      signUpPage.enterTextInEmailNField("email" + currentTime + "@email.com");
      signUpPage.enterTextInPasswordField("password");
      signUpPage.enterTextInMobileField("1234567890");
      MessagePage messagePage = signUpPage.clickSignUpLink();
      messagePage.verifyPageUrl(PageUrls.adduser);
      String actualMessage = messagePage.getMessage();
      assert actualMessage.equals(Strings.userAddedExists) : "Wrong message. Expected: " + Strings.userAddedSuccess + " . Actual: " + actualMessage;
      driver.quit();
    } finally {
      quitDriver(driver);

    }


  }

  public void signUpTestSameEmailUsed() {

  }





}
