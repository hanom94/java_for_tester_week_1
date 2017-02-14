package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by hanom on 26.01.2017.
 */
public class ApplicationManager {

  WebDriver wd;

  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupContactHelperBase groupContactHelperBase;
  private String browser;

  public ApplicationManager(String browser) {
    this.browser = browser;
  }

  public void init() {
    if(Objects.equals(browser, BrowserType.FIREFOX)){
      wd = new FirefoxDriver();
    } else if(Objects.equals(browser, BrowserType.CHROME)){
      wd = new ChromeDriver();
    } else if(Objects.equals(browser, BrowserType.IE)){
      wd = new InternetExplorerDriver();
    }
    wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/group.php");
    groupContactHelperBase = new GroupContactHelperBase(wd);
    navigationHelper = new NavigationHelper(wd);
    sessionHelper = new SessionHelper(wd);
    sessionHelper.login("admin", "secret");
  }

  public void stop() {
    wd.quit();
  }

  public GroupContactHelperBase groupContact() {
    return groupContactHelperBase;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }
}
