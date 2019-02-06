package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {



    @Test
    public void testContactCreation() throws Exception {

        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData(
                    "test_group_name",
                    "test_group_header",
                    "test_group_footer"));
        }

        app.getNavigationHelper().gotoHome();

        List<ContactData> before = app.getContactHelper().getContactList();

        app.getContactHelper().initContactCreation();
        ContactData contact = new ContactData(
                "aTest_contact_name",
                null,
                "Test_contact_lname",
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                null,
                "test_group_name",
                null,
                null,
                null);

        app.getContactHelper().fillContactData(contact, true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHome();

        List<ContactData> after = app.getContactHelper().getContactList();

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());

        before.add(contact);

        Assert.assertEquals(after.size(), before.size());

        after.sort(byId);
        before.sort(byId);
        Assert.assertEquals(after, before);

    }


}
