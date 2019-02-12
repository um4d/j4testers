package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (! app.group().isThereAGroup()) {
            app.group().create(new GroupData()
                    .withName("test_group_name")
                    .withHeader("test_group_header")
                    .withFooter("test_group_footer"));
        }
    }


    @Test
    public void testContactCreation() throws Exception {

        app.goTo().HomePage();

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
        app.goTo().HomePage();

        List<ContactData> after = app.getContactHelper().getContactList();

        Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());

        before.add(contact);

        Assert.assertEquals(after.size(), before.size());

        after.sort(byId);
        before.sort(byId);
        Assert.assertEquals(after, before);

    }


}
