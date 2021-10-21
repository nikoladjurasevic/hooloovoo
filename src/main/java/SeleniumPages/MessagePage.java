package SeleniumPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MessagePage extends BasePage {

  //locators
  @FindBy(xpath = "//h3")
  WebElement messageText;

  /**
   * constuctor
   * @param driver
   */
  public MessagePage(WebDriver driver) {
    super(driver);
  }

  /**
   * Method for getting the message text
   * @return
   */
  public String getMessage() {
    return messageText.getText();
  }

  /**
   * Method for clicking and navigating to Home page
   * @return
   */
  public HomePage clickHomeLink() {
    clickOnElement(homeLink);
    return new HomePage(driver);
  }
}
