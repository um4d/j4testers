package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import org.testng.internal.BaseTestMethod;
import ru.stqa.pft.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

    @Test
    public void testContactModification() throws Exception {
        app.getContactHelper().initContactModification();
        app.getContactHelper().editContactData(new ContactData("test_contact_name", "T. C.", "Test_contact_lname", "test_nick", "test_title",
                "test_company", "Test Address", "+2323232", "+932323111100", "333444", "999888", "testemail@test.com"
                , "testemail2@test.com", "testemail3@test.com", "testhomepage.com", "10", "January", "2000", "5",
                "April", "2001", "test_group_name", "Rlyeh", "33344555", "So much fields"));
        app.getContactHelper().submitContactUpdate();
        app.getNavigationHelper().gotoHome();
    }

}
