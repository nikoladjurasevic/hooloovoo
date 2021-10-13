package SeleniumTests;

import SeleniumPages.PageUrls;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
  static final Logger log = LogManager.getLogger(BaseTest.class);

  WebDriver openChromeDriver() {
    log.debug("Setting up Chrome driver");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--start-maximized");
    options.addArguments("--ignore-certificate-errors");
    options.addArguments("--disable-popup-blocking");
    options.addArguments("--incognito");
    String path = System.getProperty("user.dir");
    String separator = System.getProperty("file.separator");
    System.setProperty("webdriver.chrome.driver", path+separator+"chromedriver.exe");
    ChromeDriver driver = new ChromeDriver(options);
    log.debug("Opening Chrome driver");
    return  driver;
  }

  //HardCoded sleep
  public void sleepSeconds(int iSeconds){
    try{
      log.debug("Sleep for " + Integer.toString(iSeconds) + " seconds");
      Thread.sleep(iSeconds*1000);
    }
    catch (Exception e) {
      log.debug(e.getStackTrace().toString());
    }
  }


}
