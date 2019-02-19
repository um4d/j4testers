package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

    @BeforeMethod
    public void ensurePreconditions(){
        app.goTo().groupPage();
        if (app.group().count() == 0) {
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
                .withGroup("test_group_name")
                .withPhoneHome("111").withPhoneMobile("222").withPhoneWork("333")
                .withEmail1("email@mail.com").withEmail2("444411@mmail.int")
                .withEmail3("pr@").withAddress("Улица Пушкина, дом Колотушкина-2а");
        app.contacts().create(contact);
        assertThat(app.contacts().count(), equalTo(before.size() + 1));
        Contacts after = app.contacts().all();
        assertThat(after.size(), equalTo(before.withAdded(contact.withId(after
                .stream().mapToInt((g) -> g.getId()).max().getAsInt())).size()));
        assertThat(after, equalTo(before.withAdded(contact)));
    }
}
