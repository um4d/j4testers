package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactRemovingFromGroup extends TestBase {

    protected static ContactData contactBefore;
    protected static GroupData group;

    @BeforeMethod
    public void ensurePreconditions() {
        group = app.db().groups().iterator().next();
        if (group == null) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test_group_name"));
        }
        contactBefore = app.db().getContactWithGroup(group);
        System.out.println(contactBefore.getId());
        if (contactBefore == null) {
            app.goTo().HomePage();
            contactBefore = new ContactData().withName("William")
                    .withLname("Blake").withPhoneHome("111").withPhoneMobile("222")
                    .withPhoneWork("333").withEmail1("email@mail.com").withEmail2("444411@mmail.int")
                    .withEmail3("pr@").withAddress("Улица Пушкина, дом Колотушкина-2а")
                    .withGroup(group);
            app.contacts().create(contactBefore);
        }

    }

    @Test
    public void testContactRemovingFromGroup() {
        app.contacts().selectGroupForViewContacts(group);
        app.contacts().selectById(contactBefore.getId());
        app.contacts().submitRemovingFromGroup();
        ContactData contactAfter = app.db().contactByParameter("id",
                Integer.toString(contactBefore.getId()));
        assertThat(contactBefore.withoutGroup(group), equalTo(contactAfter));
    }


}
