package SeleniumPages;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class BasePage {

  static final Logger log = LogManager.getLogger(BasePage.class);

  protected WebDriver driver = null;

  public BasePage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    this.driver = driver;
    sleepSeconds(1);
  }

  public void verifyPageUrl(String expectedUrl){
    String actualUrl = driver.getCurrentUrl();
    assert  actualUrl.equals(expectedUrl) : "Wrong URL. Expected: " + expectedUrl + " . Actual: " + actualUrl;
  }

  public void enterTextInfField(WebElement element, String text) {
    element.click();
    element.clear();
    element.sendKeys(text);
    sleepSeconds(1);
  }

  public void clickOnElement(WebElement element) {
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
