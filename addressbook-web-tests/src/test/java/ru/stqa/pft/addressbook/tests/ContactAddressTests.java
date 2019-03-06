package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        if (app.db().contacts().size() == 0) {
            app.goTo().HomePage();
            app.contacts().create(new ContactData()
                    .withName("William").withLname("Blake")
                    .withPhoneHome("111").withPhoneMobile("222").withPhoneWork("333")
                    .withEmail1("email@mail.com").withEmail2("444411@mmail.int")
                    .withEmail3("pr@").withAddress("Улица Пушкина, дом Колотушкина-2а"));
        }
    }

    @Test
    public void testContactAddress() {
        ContactData contact = app.contacts().all().iterator().next();
        ContactData info = app.contacts().infoFromEditForm(contact);
        Contacts dbContacts = app.db().contacts();
        ContactData dbContact = null;
        for (ContactData con : dbContacts) {
            if (con.getId() == contact.getId()) {
                dbContact = con;
            }
        }
        assertThat(contact.getAddress(), equalTo(info.getAddress()));
        assertThat(contact.getAddress(), equalTo(dbContact.getAddress()));
    }

}
