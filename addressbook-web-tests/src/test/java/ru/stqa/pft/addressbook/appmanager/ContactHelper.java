package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

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
        type(By.name("home"), contactData.getPhoneHome());
        type(By.name("mobile"), contactData.getPhoneMobile());
        type(By.name("work"), contactData.getPhoneWork());
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

    public void selectByOrder(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    public void submitDelete() {
        click(By.xpath("//input[@value='Delete']"));
        closeAlert();
    }

    public void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='"+ id + "']")).click();
    }

    public boolean isThereContact() {
        return isElementPresent(By.name("selected[]"));
    }

    public void create(ContactData contact) {
        initContactCreation();
        fillContactData(contact, true);
        submitContactCreation();
        contactsCache = null;
        returnHome();
    }

    public int count() {
        if (isThereContact()) {
            return wd.findElements(By.name("selected[]")).size();
        }
        return 0;
    }

    public void delete (ContactData contact){
        selectById(contact.getId());
        submitDelete();
        contactsCache = null;
        returnHome();
    }

    public void modify(ContactData contact) {
        initModifyingById(contact.getId());
        fillContactData(contact, false);
        submitContactUpdate();
        contactsCache = null;
        returnHome();
    }

    public void initModifyingById(int id) {
        wd.findElement(By.xpath(String.format("//a[@href='edit.php?id=%s']", id)))
                .click();
    }

    private Contacts contactsCache = null;

    public Contacts all() {
        if (contactsCache != null) {
            return new Contacts(contactsCache);
        }
        returnHome();
        Contacts contactsCache = new Contacts();
        List<WebElement> rows = wd.findElements(By.xpath("//tr[@name='entry']"));
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            String name = cells.get(2).getText();
            String lname = cells.get(1).getText();
            int id = Integer.parseInt(row.findElement(By.tagName("input"))
                    .getAttribute("value"));
            String address = cells.get(3).getText();
            String[] emails = cells.get(4).getText().split("\n");
            String phones = cells.get(5).getText();


            ContactData contact = new ContactData()
                    .withId(id)
                    .withName(name)
                    .withLname(lname)
                    .withAddress(address)
                    .withAllPhones(phones)
                    .withEmail1(emails[0])
                    .withEmail2(emails[1])
                    .withEmail3(emails[2]);
            contactsCache.add(contact);
        }
        return contactsCache;
    }

    public ContactData infoFromEditForm(ContactData contact) {
        initModifyingById(contact.getId());
        String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
        String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
        String homePhone = wd.findElement(By.name("home")).getAttribute("value");
        String mobilePhome = wd.findElement(By.name("mobile")).getAttribute("value");
        String workPhome = wd.findElement(By.name("work")).getAttribute("value");
        String address = wd.findElement(By.name("address")).getAttribute("value");
        String email1 = wd.findElement(By.name("email")).getAttribute("value");
        String email2 = wd.findElement(By.name("email2")).getAttribute("value");
        String email3 = wd.findElement(By.name("email3")).getAttribute("value");
        wd.navigate().back();
        return new ContactData().withName(firstname).withLname(lastname)
                .withPhoneHome(homePhone).withPhoneMobile(mobilePhome).withPhoneWork(workPhome)
                .withAddress(address).withEmail1(email1).withEmail2(email2).withEmail3(email3);
    }


}

