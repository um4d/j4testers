package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactHelper {

    private FirefoxDriver wd;


    public ContactHelper(FirefoxDriver wd) {
        this.wd = wd;
    }

    

    public void fillContactData(ContactData contactData) {
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
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Birthday:'])" + "[1]/following" +
                "::option[12]")).click();
        new Select(wd.findElement(By.name("bmonth"))).selectByVisibleText(contactData.getbMonth());
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Birthday:'])" + "[1]/following" +
                "::option[35]")).click();
        wd.findElement(By.name("byear")).click();
        wd.findElement(By.name("byear")).clear();
        wd.findElement(By.name("byear")).sendKeys(contactData.getbYear());
        new Select(wd.findElement(By.name("aday"))).selectByVisibleText(contactData.getaDay());
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Anniversary:'])" + "[1" +
                "]/following::option[7]")).click();
        new Select(wd.findElement(By.name("amonth"))).selectByVisibleText(contactData.getaMonth());
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Anniversary:'])" + "[1" +
                "]/following::option[38]")).click();
        wd.findElement(By.name("ayear")).click();
        wd.findElement(By.name("ayear")).clear();
        wd.findElement(By.name("ayear")).sendKeys(contactData.getaYear());
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Group:'])" + "[1]/following" +
                "::option[2]")).click();
        wd.findElement(By.name("address2")).click();
        wd.findElement(By.name("address2")).clear();
        wd.findElement(By.name("address2")).sendKeys(contactData.getAddress_2());
        wd.findElement(By.name("phone2")).clear();
        wd.findElement(By.name("phone2")).sendKeys(contactData.getPhone_2());
        wd.findElement(By.name("notes")).clear();
        wd.findElement(By.name("notes")).sendKeys(contactData.getNotes());
    }

    public void submitContactCreation() {
        wd.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Notes:'])" + "[1]/following" +
                "::input[1]")).click();
    }

    public void initContactCreation() {
        wd.findElement(By.linkText("add new")).click();
    }
}
