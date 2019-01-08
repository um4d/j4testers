package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class ContactCreationTests {
    private WebDriver wd;


    @BeforeClass(alwaysRun = true)
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        login("admin", "secret");

    }

    @Test
    public void testUntitledTestCase() throws Exception {
        initUserCreation();
        fillUserData(new ContactData("test_contact_name", "T. C.", "Test_contact_lname", "test_nick", "test_title",
                "test_company", "Test Address", "+2323232", "+932323111100", "333444", "999888", "testemail@test.com"
                , "testemail2@test.com", "testemail3@test.com", "testhomepage.com", "10", "January", "2000", "5",
                "April", "2001", "test_group_name", "Rlyeh", "33344555", "So much fields"));
        home();
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws Exception {
        logout();
        wd.quit();
    }

    private void logout() {
        wd.findElement(By.linkText("Logout")).click();
    }

    private void home() {
        wd.findElement(By.linkText("home")).click();
    }

    private void fillUserData(ContactData contactData) {
        wd.findElement(By.name("firstname")).click();
        wd.findElement(By.name("firstname")).clear();
        wd.findElement(By.name("firstname")).sendKeys(contactData.getName());
        wd.findElement(By.name("middlename")).clear();
        wd.findElement(By.name("middlename")).sendKeys(contactData.getMiddleName());
        wd.findElement(By.name("lastname")).click();
        wd.findElement(By.name("lastname")).clear();
        wd.findElement(By.name("lastname")).sendKeys(contactData.getLastName());
        wd.findElement(By.name("nickname")).clear();
        wd.findElement(By.name("nickname")).sendKeys(contactData.getNick());
        wd.findElement(By.name("title")).clear();
        wd.findElement(By.name("title")).sendKeys(contactData.getTitle());
        wd.findElement(By.name("company")).clear();
        wd.findElement(By.name("company")).sendKeys(contactData.getCompany());
        wd.findElement(By.name("address")).clear();
        wd.findElement(By.name("address")).sendKeys(contactData.getAddress());
        wd.findElement(By.name("home")).click();
        wd.findElement(By.name("home")).clear();
        wd.findElement(By.name("home")).sendKeys(contactData.getHomePhone());
        wd.findElement(By.name("mobile")).clear();
        wd.findElement(By.name("mobile")).sendKeys(contactData.getMobilePhone());
        wd.findElement(By.name("work")).clear();
        wd.findElement(By.name("work")).sendKeys(contactData.getWorkPhone());
        wd.findElement(By.name("fax")).clear();
        wd.findElement(By.name("fax")).sendKeys(contactData.getFaxPhone());
        wd.findElement(By.name("email")).clear();
        wd.findElement(By.name("email")).sendKeys(contactData.getEmail_1());
        wd.findElement(By.name("email2")).clear();
        wd.findElement(By.name("email2")).sendKeys(contactData.getEmail_2());
        wd.findElement(By.name("email3")).clear();
        wd.findElement(By.name("email3")).sendKeys(contactData.getEmail_3());
        wd.findElement(By.name("homepage")).click();
        wd.findElement(By.name("homepage")).clear();
        wd.findElement(By.name("homepage")).sendKeys(contactData.getHomepage());
        new Select(wd.findElement(By.name("bday"))).selectByVisibleText(contactData.getBday());
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Birthday:'])" +
                "[1]/following::option[12]")).click();
        new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getbMonth());
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Birthday:'])" +
                "[1]/following::option[35]")).click();
        wd.findElement(By.name("byear")).click();
        wd.findElement(By.name("byear")).clear();
        wd.findElement(By.name("byear")).sendKeys(contactData.getbYear());
        new Select(wd.findElement(By.name("aday"))).selectByVisibleText(contactData.getaDay());
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Anniversary:'])" +
                "[1]/following::option[7]")).click();
        new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(contactData.getaMonth());
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Anniversary:'])" +
                "[1]/following::option[38]")).click();
        wd.findElement(By.name("ayear")).click();
        wd.findElement(By.name("ayear")).clear();
        wd.findElement(By.name("ayear")).sendKeys(contactData.getaYear());
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Group:'])" +
                "[1]/following::option[2]")).click();
        wd.findElement(By.name("address2")).click();
        wd.findElement(By.name("address2")).clear();
        wd.findElement(By.name("address2")).sendKeys(contactData.getAddress_2());
        wd.findElement(By.name("phone2")).clear();
        wd.findElement(By.name("phone2")).sendKeys(contactData.getPhone_2());
        wd.findElement(By.name("notes")).clear();
        wd.findElement(By.name("notes")).sendKeys(contactData.getNotes());
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])" +
                "[1]/following::input[1]")).click();
    }


    private void initUserCreation() {
        wd.findElement(By.linkText("add new")).click();
    }

    private void login(String username, String password) {
        wd.get("http://localhost/addressbook/index.php");
        wd.findElement(By.name("user")).clear();
        wd.findElement(By.name("user")).sendKeys(username);
        wd.findElement(By.name("pass")).clear();
        wd.findElement(By.name("pass")).sendKeys(password);
        wd.findElement(By.id("LoginForm")).submit();
    }


}
