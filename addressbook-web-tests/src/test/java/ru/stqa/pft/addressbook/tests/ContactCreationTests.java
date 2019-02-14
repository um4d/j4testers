package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().all().size() == 0) {
            app.group().create(new GroupData()
                    .withName("test_group_name")
                    .withHeader("test_group_header")
                    .withFooter("test_group_footer"));
        }
    }

    @Test
    public void testContactCreation() throws Exception {
        app.goTo().HomePage();
        Contacts before = app.contacts().all();
        ContactData contact = new ContactData().withName("William").withLname("Blake")
                .withGroup("test_group_name");
        app.contacts().create(contact);
        Contacts after = app.contacts().all();
        assertThat(after.size(), equalTo(before.withAdded(contact.withId(after
                .stream().mapToInt((g) -> g.getId()).max().getAsInt())).size()));
        assertThat(after, equalTo(before.withAdded(contact)));
    }
}
