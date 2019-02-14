package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
public class ContactModificationTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData()
                            .withName("test_group_name")
                            .withHeader("test_group_header")
                            .withFooter("test_group_footer"));
        }
        app.goTo().HomePage();
        if (app.contacts().all().size() == 0) {
            app.contacts().create(new ContactData()
                            .withName("Precondition")
                            .withLname("Petrov")
                            .withGroup("test_group_name"));
         }
    }

    @Test
    public void testContactModification() throws Exception {
        app.goTo().HomePage();
        ContactData contact = new ContactData()
                            .withName("Modified")
                            .withLname("Smith")
                            .withGroup("test_group_name");
        Contacts before = app.contacts().all();
        ContactData modifiedContact = before.iterator().next();
        app.contacts().modify(contact.withId(modifiedContact.getId()));
        Contacts after = app.contacts().all();
        assertThat(after.size(), equalTo(before
                            .without(modifiedContact).withAdded(contact).size()));
        assertThat(after, equalTo(before
                             .without(modifiedContact).withAdded(contact)));

    }

}
