package ru.stqa.pft.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

    public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromJson() throws IOException {
        BufferedReader reader = new BufferedReader(
                new FileReader(new File("src/test/resources/contacts.json")));
        StringBuilder json = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            json.append(line);
            line = reader.readLine();
        }
        Gson gson = new Gson();
        List<ContactData> contacts = gson.fromJson(json.toString(),
                new TypeToken<List<ContactData>>(){}.getType());
        return contacts.stream().map((g) -> new Object[] {g})
                .collect(Collectors.toList()).iterator();
    }

    @BeforeMethod
    public void ensurePreconditions(){
        if (app.db().groups().size() == 0) {
            app.goTo().groupPage();
            app.group().create(new GroupData()
                    .withName("test_group_name")
                    .withHeader("test_group_header")
                    .withFooter("test_group_footer"));
        }
    }

    @Test(dataProvider = "validContactsFromJson")
    public void testContactCreation(ContactData contact) throws Exception {
        app.goTo().HomePage();
        Groups groups = app.db().groups();
        Contacts before = app.db().contacts();
        app.contacts().create(contact.withGroup(groups.iterator().next()));
        assertThat(app.contacts().count(), equalTo(before.size() + 1));
        Contacts after = app.db().contacts();
        assertThat(after.size(), equalTo(before.withAdded(contact.withId(after
                .stream().mapToInt((g) -> g.getId()).max().getAsInt())).size()));
        assertThat(after, equalTo(before.withAdded(contact)));
        verifyContactListInUI();
    }
}
