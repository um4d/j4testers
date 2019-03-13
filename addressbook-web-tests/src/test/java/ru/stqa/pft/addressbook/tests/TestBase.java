package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

    protected static final ApplicationManager app = new ApplicationManager(
            System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    public void verifyGroupListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Groups dbGroups = app.db().groups();
            Groups UiGroups = app.group().all();
            assertThat(dbGroups.stream().map((g) -> new GroupData().withId(g.getId())
                            .withName(g.getName())).collect(Collectors.toSet()),
                    CoreMatchers.equalTo(UiGroups));
        }
    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts UiContacts = app.contacts().all();
            assertThat(dbContacts.stream().map((c) -> new ContactData()
                            .withId(c.getId())
                            .withName(c.getName())
                            .withLname(c.getLastName())
                            .withAddress(c.getAddress())
                            .withAllEmails(c.getEmail_1() + "\n" + c.getEmail_2() + "\n" + c.getEmail_3())
                            .withAllPhones(c.getPhoneHome() + "\n" + c.getPhoneMobile() + "\n" + c.getPhoneWork()))
                            .collect(Collectors.toSet()),
                    CoreMatchers.equalTo(UiContacts));
        }
    }


}
