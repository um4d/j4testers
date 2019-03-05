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
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                            .withName("test_group_name")
                            .withHeader("test_group_header")
                            .withFooter("test_group_footer"));
        }
        app.goTo().HomePage();
        if (app.db().contacts().size() == 0) {
            app.contacts().create(new ContactData()
                    .withName("William").withLname("Blake").withGroup("test_group_name")
                    .withPhoneHome("111").withPhoneMobile("222").withPhoneWork("333")
                    .withEmail1("email@mail.com").withEmail2("444411@mmail.int")
                    .withEmail3("pr@").withAddress("Улица Пушкина, дом Колотушкина-2а"));
         }
    }

    @Test
    public void testContactModification() throws Exception {
        app.goTo().HomePage();
        ContactData contact = new ContactData()
                .withName("William").withLname("Blake").withGroup("test_group_name")
                .withPhoneHome("111").withPhoneMobile("222").withPhoneWork("333")
                .withEmail1("email@mail.com").withEmail2("444411@mmail.int")
                .withEmail3("pr@").withAddress("Улица Пушкина, дом Колотушкина-2а");
        Contacts before = app.db().contacts();
        ContactData modifiedContact = before.iterator().next();
        app.contacts().modify(contact.withId(modifiedContact.getId()));
        assertThat(app.db().contacts().size(), equalTo(before.size()));
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before
                            .without(modifiedContact).withAdded(contact).size()));
        assertThat(after, equalTo(before
                             .without(modifiedContact).withAdded(contact)));

    }

}
