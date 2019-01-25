package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;


public class ContactHelper extends HelperBase {


    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void fillContactData(ContactData contactData, boolean creation) {
        type(By.name("firstname"), contactData.getName());
        type(By.name("middlename"), contactData.getMiddleName());
        type(By.name("lastname"), contactData.getLastName());
        type(By.name("nickname"), contactData.getNick());
        type(By.name("title"), contactData.getTitle());
        type(By.name("company"), contactData.getCompany());
        type(By.name("address"), contactData.getAddress());
        type(By.name("home"), contactData.getHomePhone());
        type(By.name("mobile"), contactData.getMobilePhone());
        type(By.name("work"), contactData.getWorkPhone());
        type(By.name("fax"), contactData.getFaxPhone());
        type(By.name("email"), contactData.getEmail_1());
        type(By.name("email2"), contactData.getEmail_2());
        type(By.name("email3"), contactData.getEmail_3());
        type(By.name("homepage"), contactData.getHomepage());
        click(By.xpath("//option[12]"));
        click(By.xpath("//option[@value='January']"));
        type(By.name("byear"), contactData.getbYear());
        click(By.xpath("//select[3]/option[7]"));
        click(By.xpath("(//option[@value='April'])[2]"));
        type(By.name("ayear"), contactData.getaYear());

        if (creation) {
            click(By.xpath("(//option[@value='7'])[3]"));
        }

        type(By.name("address2"), contactData.getAddress_2());
        type(By.name("phone2"), contactData.getPhone_2());
        type(By.name("notes"), contactData.getNotes());
    }

    public void submitContactCreation() {
        click(By.xpath("(//input[@name='submit'])[2]"));
    }

    public void initContactCreation() {
        click(By.linkText("add new"));
    }

    public void initContactModification() {
        click(By.xpath("//img[@alt='Edit']"));
    }

    public void submitContactUpdate() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }


    public void deleteContact() {

        click(By.name("selected[]"));
        click(By.xpath("//input[@value='Delete']"));
        closeAlert();

    }
}
