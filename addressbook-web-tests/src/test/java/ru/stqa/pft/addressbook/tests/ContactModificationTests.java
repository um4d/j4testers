package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() throws Exception {

        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData(
                    "test_group_name",
                    "test_group_header",
                    "test_group_footer"));
        }
        app.getNavigationHelper().gotoHome();
        if (! app.getContactHelper().isThereContact()) {
            app.getContactHelper().createContact(new ContactData("test_contact_name", "T. C.", "Test_contact_lname", "test_nick", "test_title",
                    "test_company", "Test Address", "+2323232", "+932323111100", "333444", "999888", "testemail@test.com"
                    , "testemail2@test.com", "testemail3@test.com", "testhomepage.com", "10", "January", "2000", "5",
                    "April", "2001", "test_group_name", "Rlyeh", "33344555", "So much fields"));
        }

        app.getNavigationHelper().gotoHome();
        ContactData contact = new ContactData(
                "cTest_contact_name",
                null,
                "aTest_contact_lname",
                null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                "test_group_name", null, null, null);


        List<ContactData> before = app.getContactHelper().getContactList();
        Comparator<? super ContactData> byId = (c1, c2) ->
                (Integer.compare(c1.getId(), c2.getId()));
        app.getContactHelper().initContactModification(before.size() - 1);
        app.getContactHelper().fillContactData(contact, false);
        app.getContactHelper().submitContactUpdate();
        int modContactId = before.get(before.size() - 1).getId();
        before.remove(before.size() - 1);
        contact.setId(modContactId);
        before.add(contact);
        before.sort(byId);
        app.getNavigationHelper().gotoHome();
        List<ContactData> after = app.getContactHelper().getContactList();
        after.sort(byId);

        Assert.assertEquals(after.size(), before.size());
        Assert.assertEquals(after, before);

    }

}
