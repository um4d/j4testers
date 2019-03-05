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
        if (app.contacts().count() == 0) {
            app.contacts().create(new ContactData()
                    .withName("William").withLname("Blake").withGroup("test_group_name")
                    .withPhoneHome("111").withPhoneMobile("222").withPhoneWork("333")
                    .withEmail1("email@mail.com").withEmail2("444411@mmail.int")
                    .withEmail3("pr@").withAddress("Улица Пушкина, дом Колотушкина-2а"));
        }
    }

    @Test
    public void testContactDeletion(){
        app.goTo().HomePage();
        Contacts before = app.db().contacts();
        ContactData deletedContact = before.iterator().next();
        app.contacts().delete(deletedContact);
        before.remove(deletedContact);
        assertThat(app.contacts().count(), equalTo(before.size()));
        Contacts after  = app.db().contacts();
        assertThat(after.size(), equalTo(before.without(deletedContact).size()));
        assertThat(after, equalTo(before.without(deletedContact)));
    }

}
