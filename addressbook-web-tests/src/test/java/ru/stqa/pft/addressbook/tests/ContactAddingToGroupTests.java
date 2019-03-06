package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactAddingToGroupTests extends TestBase{

    protected static ContactData contactBefore;
    protected static GroupData group;

    @BeforeMethod
    public static void ensurePreconditions() {
        if(app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData().withName("test_group_name"));
        }
        contactBefore = app.db().getContactWithoutGroup();
        System.out.println(contactBefore);
        if (contactBefore == null) {
            contactBefore = new ContactData().withName("William")
                    .withLname("Blake").withPhoneHome("111").withPhoneMobile("222")
                    .withPhoneWork("333").withEmail1("email@mail.com").withEmail2("444411@mmail.int")
                    .withEmail3("pr@").withAddress("Улица Пушкина, дом Колотушкина-2а");
            app.contacts().createWithoutGroup(contactBefore);
            contactBefore = contactBefore.withId(app.db().contacts().stream().max((o1, o2) -> ))
        }

    }


    @Test
    public static void addContactToGroupTest() {
        GroupData group = app.db().groups().iterator().next();
        app.contacts().selectById(contactBefore.getId());
        app.contacts().selectToGroup(group);
        app.contacts().submitAddingToGroup();
        ContactData contactAfter = app.db().contactByParameter(
                "id", Integer.toString(contactBefore.getId()));
        System.out.println(group.getId());
        System.out.println("----------------");
        System.out.println(contactAfter.getGroups());
        System.out.println("----------------");
        System.out.println(contactBefore.getGroups());
        assertThat(contactAfter.getGroups(), equalTo(contactAfter.withGroup(group).getGroups()));
    }
}
