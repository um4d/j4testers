package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeletionTests extends TestBase {

    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().HomePage();
        if (app.contacts().all().size() == 0) {
            app.contacts().create(new ContactData()
                    .withName("Modifyied")
                    .withLname("Smith")
                    .withGroup("test_group_name"));
        }
    }

    @Test
    public void testContactDeletion(){
        app.goTo().HomePage();
        Contacts before = app.contacts().all();
        ContactData deletedContact = before.iterator().next();
        app.contacts().delete(deletedContact);
        before.remove(deletedContact);
        Contacts after  = app.contacts().all();
        assertThat(after.size(), equalTo(before.without(deletedContact).size()));
        assertThat(after, equalTo(before.without(deletedContact)));
    }

}
