package SeleniumTests;

import java.io.File;
import java.util.Date;

import SeleniumPages.PageUrls;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class BaseTest {
  static final Logger log = LogManager.getLogger(BaseTest.class);
  Date date = new Date();
  long currentTime = date.getTime();

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
//      log.debug("Sleep for " + Integer.toString(iSeconds) + " seconds");
      Thread.sleep(iSeconds*1000);
    }
    catch (Exception e) {
      log.debug(e.getStackTrace().toString());
    }
  }

  public void quitDriver(WebDriver driver) {
    if (driver!=null) {
      try {
        takeScreenshot(driver, getClass().toString()+currentTime);
        driver.quit();
      } catch (Throwable t) {
        log.debug(t.getMessage());
      }
    }
  }

  public void takeScreenshot(WebDriver driver, String fileName) throws Exception {
    TakesScreenshot scrShot = ((TakesScreenshot) driver);
    File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
    String separator = System.getProperty("file.separator");
    String path = System.getProperty("user.dir");
    String destFilePath = path + separator + "src" + separator + "test" + separator + "java" + separator + "Output";
    File DestFile = new File(destFilePath + separator + fileName + ".png");
    FileUtils.copyFile(SrcFile, DestFile);
    log.debug("Screenshot generated at" + DestFile.toString());
  }

}
