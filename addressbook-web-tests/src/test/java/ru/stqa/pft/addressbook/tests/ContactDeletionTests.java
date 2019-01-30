package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.concurrent.TimeUnit;

public class ContactDeletionTests extends TestBase {

    @Test
    public void testContactDeletion(){

        app.getNavigationHelper().gotoHome();

        if (! app.getContactHelper().isThereContact()) {
            app.getContactHelper().createContact(new ContactData("test_contact_name", "T. C.", "Test_contact_lname", "test_nick", "test_title",
                    "test_company", "Test Address", "+2323232", "+932323111100", "333444", "999888", "testemail@test.com"
                    , "testemail2@test.com", "testemail3@test.com", "testhomepage.com", "10", "January", "2000", "5",
                    "April", "2001", "test_group_name", "Rlyeh", "33344555", "So much fields"));
        }

        app.getNavigationHelper().gotoHome();

        int before = app.getContactHelper().getContactCount();
        app.getContactHelper().selectContact(0);
        app.getContactHelper().deleteContact();
        app.getNavigationHelper().gotoHome();

    //        try {
    //            TimeUnit.SECONDS.sleep(1);
    //        } catch (InterruptedException e) {
    //            e.printStackTrace();
    //        }

        int after = app.getContactHelper().getContactCount();
        Assert.assertEquals(after, before - 1);


    }

}
