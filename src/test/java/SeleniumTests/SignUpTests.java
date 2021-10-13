package SeleniumTests;

import SeleniumPages.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SignUpTests extends BaseTest{

  @Test
  public void test1(){
    WebDriver driver =  openChromeDriver();
    HomePage homePage = new HomePage(driver);
    homePage.enterUserName("dadfa");
    homePage.enterPassword("adfadf");
    homePage.clickLoginButton();
    driver.quit();
  }

}
