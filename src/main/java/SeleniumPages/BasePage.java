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
  }

  public void enterTextInfField(WebElement element, String text) {
    element.clear();
    element.sendKeys(text);
  }

}
