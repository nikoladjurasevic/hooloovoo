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
    HomePage homePage = new HomePage(driver);
    sleepSeconds(1);
    homePage.enterUserName("dadfa");
    homePage.enterPassword("adfadf");
    homePage.clickLoginButton();
    sleepSeconds(1);
    driver.quit();
  }

  @Test
  public void signUpTestPositiveScenario() {
    WebDriver driver = openChromeDriver();
    HomePage homePage = new HomePage(driver);
    SignUpPage signUpPage = homePage.clickSignUpLink();
    signUpPage.enterTextInFIRSTFirstNameField("prviFirstName");
    signUpPage.enterTextInSECONDFirstNameField("drugiFirstName");
    Date date = new Date();
    long currentTime = date.getTime();
    signUpPage.enterTextInLastNameField("lastName" + currentTime);
    signUpPage.enterTextInEmailNField("email" + currentTime + "@email.com");
    signUpPage.enterTextInPasswordField("password");
    signUpPage.enterTextInMobileField("1234567890");
    MessagePage messagePage = signUpPage.clickSignUpLink();
    messagePage.verifyPageUrl(PageUrls.adduser);
    String actualMessage = messagePage.getMessage();
    assert actualMessage.equals(Strings.userAddedSuccess) : "Wrong message. Expected: " + Strings.userAddedSuccess + " . Actual: " + actualMessage;
    driver.quit();
  }

  public void signUpTestSameEmailUsed() {
    
  }





}
