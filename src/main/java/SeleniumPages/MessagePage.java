package SeleniumPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MessagePage extends BasePage {

  //locators
  @FindBy(xpath = "//h3")
  WebElement messageText;

  public MessagePage(WebDriver driver) {
    super(driver);
  }

  public String getMessage() {
    return messageText.getText();
  }

  public HomePage clickHomeLink() {
    clickOnElement(homeLink);
    return new HomePage(driver);
  }
}
