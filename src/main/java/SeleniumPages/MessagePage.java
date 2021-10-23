package SeleniumPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MessagePage extends BasePage {

  //locators
  @FindBy(xpath = "//h3")
  WebElement messageText;

  @FindBy(xpath = "//a[@href='/login/']")
  WebElement loginLink;

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
    log.debug("[TEST] clickHomeLink()");
    clickOnElement(homeLink);
    log.debug("[TEST] Navigate to Home Page");
    return new HomePage(driver);
  }

  /**
   * Method for clicking and navigating to Home page
   * @return
   */
  public LoginPage clickLoginLink() {
    log.debug("[TEST] clickLoginLink()");
    clickOnElement(loginLink);
    log.debug("[TEST] Navigate to Login Page");
    return new LoginPage(driver);
  }

}
