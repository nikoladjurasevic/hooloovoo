package SeleniumPages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

  //some common locators
  //This webelement is found on MessagePage and AddUserPage
  @FindBy(xpath = "//a[@href='/']")
  WebElement homeLink;

  static final Logger log = LogManager.getLogger(BasePage.class);

  protected WebDriver driver = null;


  //NOTE: I've added sleep so the test can be viewed when running
  /**
   * BasePage constructor
   * @param driver
   */
  public BasePage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
    log.debug("Current page: " + driver.getCurrentUrl());
    sleepSeconds(1);
  }

  /**
   * Method to compare expected and actual url
   * @param expectedUrl
   */
  public void verifyPageUrl(String expectedUrl){
    log.debug("[TEST] verifyPageUrl(" + expectedUrl + ")");
    String actualUrl = driver.getCurrentUrl();
    assert  actualUrl.equals(expectedUrl) : "Wrong URL. Expected: " + expectedUrl + " . Actual: " + actualUrl;
  }

  /**
   * Method for entering text into fields
   * @param element
   * @param text
   */
  public void enterTextInfField(WebElement element, String text) {
    log.debug("enterTextInfField(" + text + ")");
    element.click();
    element.clear();
    element.sendKeys(text);
    sleepSeconds(1);
  }

  /**
   * Method for clicking on webelement
   * @param element
   */
  public void clickOnElement(WebElement element) {
    log.debug("clickOnElement()");
    element.click();
    sleepSeconds(1);
  }

  //HardCoded sleep
  public void sleepSeconds(int iSeconds){
    try{
//      log.debug("Sleep for " + Integer.toString(iSeconds) + " seconds");
      Thread.sleep(iSeconds*1000);
    }
    catch (Exception e) {
      log.debug(e.getStackTrace().toString());
    }
  }

}
