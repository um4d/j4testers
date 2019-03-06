package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase{


    @BeforeMethod
    public void ensurePrecondition() {
        app.goTo().HomePage();
        if (app.contacts().count() == 0) {
            app.contacts().create(new ContactData()
                    .withName("William").withLname("Blake")
                    .withPhoneHome("111").withPhoneMobile("222").withPhoneWork("333")
                    .withEmail1("email@mail.com").withEmail2("444411@mmail.int")
                    .withEmail3("pr@").withAddress("Улица Пушкина, дом Колотушкина-2а"));
        }
    }

    @Test
    public void testContactPhones() {
        app.goTo().HomePage();
        ContactData contact = app.contacts().all().iterator().next();
        ContactData info = app.contacts().infoFromEditForm(contact);
        assertThat(contact.getAllPhones(), equalTo(mergePhones(info)));
    }

    private String  mergePhones(ContactData contact) {
        return Arrays.asList(contact.getPhoneHome(), contact.getPhoneMobile(), contact.getPhoneWork())
                .stream().filter(s -> !s.equals(""))
                .map(ContactPhoneTests::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String phone) {
        return phone.replaceAll("[^0-9+]", "");
    }

}
