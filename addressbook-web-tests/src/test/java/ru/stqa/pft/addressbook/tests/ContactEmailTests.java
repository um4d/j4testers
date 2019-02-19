package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactEmailTests extends TestBase {
    @BeforeMethod
    public void ensurePreconditions() {
        if (app.contacts().count() == 0) {
            app.goTo().HomePage();
            if (app.contacts().count() == 0) {
                app.contacts().create(new ContactData()
                        .withName("William").withLname("Blake").withGroup("test_group_name")
                        .withPhoneHome("111").withPhoneMobile("222").withPhoneWork("333")
                        .withEmail1("email@mail.com").withEmail2("444411@mmail.int")
                        .withEmail3("pr@").withAddress("Улица Пушкина, дом Колотушкина-2а"));
            }
        }
    }

    @Test
    public void testContactEmail() {
        app.goTo().HomePage();
        ContactData contact = app.contacts().all().iterator().next();
        ContactData info = app.contacts().infoFromEditForm(contact);
        assertThat(contact.getAllEmails(), equalTo(mergeEmails(info)));
    }

    private String mergeEmails(ContactData c) {
        return Arrays.asList(c.getEmail_1(), c.getEmail_2(), c.getEmail_3())
                .stream().filter(s -> !s.equals("")).collect(Collectors.joining("\n"));
    }
}
