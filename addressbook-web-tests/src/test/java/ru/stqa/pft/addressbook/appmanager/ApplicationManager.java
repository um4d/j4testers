package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    private Properties properties;
    WebDriver wd;

    private NavigationHelper navigationHelper;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private String browser;
    private GroupHelper groupHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties = new Properties();
        properties.load(new FileReader(new File(String
                .format("src/test/resources/%s.properties", target))));

        if (browser.equals(BrowserType.FIREFOX)) {
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)) {
            wd = new ChromeDriver();
        }


        wd.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        wd.get(properties.getProperty("web.baseUrl"));

        sessionHelper = new SessionHelper(wd);
        groupHelper = new GroupHelper(wd);
        navigationHelper = new NavigationHelper(wd);
        contactHelper = new ContactHelper(wd);

        sessionHelper.login(properties.getProperty("web.adminLogin"),
                properties.getProperty("web.adminPassword"));

    }



    public void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    public void stop() {
        logout();
        wd.quit();
    }


    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public ContactHelper contacts() {
        return contactHelper;
    }


}
