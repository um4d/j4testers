package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

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

        int before = app.getContactHelper().getContactCount();

        app.getContactHelper().initContactCreation();
        app.getContactHelper().fillContactData(new ContactData("test_contact_name", "T. C.", "Test_contact_lname", "test_nick", "test_title",
                "test_company", "Test Address", "+2323232", "+932323111100", "333444", "999888", "testemail@test.com"
                , "testemail2@test.com", "testemail3@test.com", "testhomepage.com", "10", "January", "2000", "5",
                "April", "2001", "test_group_name", "Rlyeh", "33344555", "So much fields"), true);
        app.getContactHelper().submitContactCreation();
        app.getNavigationHelper().gotoHome();

        int after = app.getContactHelper().getContactCount();

        Assert.assertEquals(after, before + 1);

    }


}
