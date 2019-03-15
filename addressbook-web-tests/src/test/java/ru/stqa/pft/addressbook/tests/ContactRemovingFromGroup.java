package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemovingFromGroup extends TestBase {

    protected static ContactData contact;
    protected static GroupData group;

    @BeforeMethod
    public void ensurePreconditions() {

        group = app.db().groups().iterator().next();
        if (group == null) {
            app.goTo().groupPage();
            group = new GroupData().withName("test_group_name");
            app.group().create(group);
            group.withId(app.db().groups().stream().max(Comparator.comparingInt(GroupData::getId))
                    .get().getId());
        }

        contact = app.db().getContactWithGroup(group);

        if (contact == null || contact.getGroups().equals(app.db().groups())) {
            app.goTo().HomePage();
            contact = new ContactData().withName("William")
                    .withLname("Blake").withPhoneHome("111").withPhoneMobile("222")
                    .withPhoneWork("333").withEmail1("email@mail.com").withEmail2("444411@mmail.int")
                    .withEmail3("pr@").withAddress("Улица Пушкина, дом Колотушкина-2а")
                    .withGroup(group);
            app.contacts().create(contact);
            contact.withId(app.db().contacts().stream().max(Comparator.comparingInt(ContactData::getId))
                    .get().getId());
        }

    }

    @Test
    public void testContactRemovingFromGroup() throws InterruptedException {
        app.goTo().HomePage();
        app.contacts().selectGroupForViewContacts(group);
        app.contacts().selectById(contact.getId());
        app.contacts().submitRemovingFromGroup();
        ContactData contactAfter = app.db().contactByParameter("id",
                Integer.toString(contact.getId()));
        assertThat(contact, equalTo(contactAfter));
        assertThat(contact.withoutGroup(group).getGroups(), equalTo(contactAfter.getGroups()));
    }

}
