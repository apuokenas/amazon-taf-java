package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Created by Mantas Tumėnas on 2017-04-21.
 */
public class DriverUtils {

  public static WebDriver driver;

  public static WebDriver getDriver() {
    if (driver == null) {
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--kiosk"); // UNIX
      options.addArguments("--start-maximized"); // Windows
      driver = new ChromeDriver(options);
    }
    return driver;
  }

  @BeforeSuite(alwaysRun = true)
  public void setUp() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--kiosk"); // UNIX
    options.addArguments("--start-maximized"); // Windows
    driver = new ChromeDriver(options);
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.close();
    driver.quit();
  }

}
