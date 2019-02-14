package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.appmanager.NavigationHelper;

import java.util.ArrayList;
import java.util.List;


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
            selectGroupForCreation(contactData.getGroup());
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

    public void modifyByOrder(int index) {
        wd.findElements(By.xpath("//img[@alt='Edit']")).get(index).click();
    }

    public void submitContactUpdate() {
        click(By.xpath("(//input[@name='update'])[2]"));
    }

    public void returnHome() {
        click(By.linkText("home"));
    }

    public void selectGroupForCreation(String groupName) {
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(groupName);
    }

    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void submitDelete() {
        click(By.xpath("//input[@value='Delete']"));
        closeAlert();
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactData(contact, true);
        submitContactCreation();
        returnHome();
    }

    public boolean isThereContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public int getContactCount() {
        if (isThereContact()) {
            return wd.findElements(By.name("selected[]")).size();
        }
        return 0;
    }

    public Contacts all() {
        returnHome();
        Contacts contacts = new Contacts();
        List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement elem : elements) {
            String name = elem.findElements(By.tagName("td")).get(2).getText();
            String lname = elem.findElements(By.tagName("td")).get(1).getText();
            int id = Integer.parseInt(elem.findElement(By.tagName("input"))
                                .getAttribute("value"));
            ContactData contact = new ContactData()
                    .withId(id)
                    .withName(name)
                    .withLname(lname);
            contacts.add(contact);
        }
        return contacts;
    }

    public void delete (ContactData contact){
        selectById(contact.getId());
        submitDelete();
        returnHome();
    }


    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='"+ id + "']")).click();
    }

    public void modify(ContactData contact) {
        wd.findElement(By.xpath("//a[@href='edit.php?id="+ contact.getId() + "']")).click();
        fillContactData(contact, false);
        submitContactUpdate();
        returnHome();
    }
}