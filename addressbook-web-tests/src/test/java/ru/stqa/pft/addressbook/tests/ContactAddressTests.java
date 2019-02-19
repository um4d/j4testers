package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddressTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
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
    public void testContactAddress() {
        ContactData contact = app.contacts().all().iterator().next();
        ContactData info = app.contacts().infoFromEditForm(contact);
        assertThat(contact.getAddress(), equalTo(info.getAddress()));
    }

}
