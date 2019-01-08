package ru.stqa.pft.addressbook;


import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupDeletionTests extends TestBase{

    @Test
    public void testGroupDeletionTests() throws Exception {
        wd.get("http://localhost/addressbook/index.php");
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys("admin");
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys("secret");
        wd.findElement(By.id("LoginForm")).submit();
        wd.findElement(By.linkText("groups")).click();
        wd.findElement(By.name("selected[]")).click();
        wd.findElement(By.name("delete")).click();
        wd.findElement(By.linkText("group page")).click();
        wd.findElement(By.linkText("Logout")).click();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws Exception {
        wd.quit();
    }


}

