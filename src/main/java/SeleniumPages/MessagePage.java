package SeleniumPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MessagePage extends BasePage {

  //locators
  @FindBy(xpath = "//h3")
  WebElement messageText;

  @FindBy(xpath = "//a[@href='/']")
  WebElement homeLink;


  public MessagePage(WebDriver driver) {
    super(driver);
  }

  public String getMessage() {
    return messageText.getText();
  }
}
