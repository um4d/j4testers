package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

    @Test
    public void testGroupModification() throws Exception {

        app.getNavigationHelper().gotoGroupPage();


        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData(
                    "test_group_name",
                    "test_group_header",
                    "test_group_footer"));
        }
        int before = app.getGroupHelper().getGroupCount();

        app.getGroupHelper().selectGroup(before - 1);
        app.getGroupHelper().initGroupModification();
        app.getGroupHelper().fillGroupForm(new GroupData(
                "test_group_name",
                "test_group_header",
                "test_group_footer"));
        app.getGroupHelper().submitGroupModification();
        app.getGroupHelper().returnToGroupPage();

        int after = app.getGroupHelper().getGroupCount();

        Assert.assertEquals(after, before);


    }

}
