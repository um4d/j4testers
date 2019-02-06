package ru.stqa.pft.addressbook.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;


public class GroupDeletionTests extends TestBase {

    @Test
    public void testGroupDeletion() throws Exception {

        app.getNavigationHelper().gotoGroupPage();

        if (! app.getGroupHelper().isThereAGroup()) {
            app.getGroupHelper().createGroup(new GroupData(
                    "test_name",
                    "test_header",
                    "test_footer"));
        }

        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size() - 1);
        app.getGroupHelper().deleteGroup();
        int deletedId = before.get(before.size() - 1).getId();
        before.remove(before.size() - 1);
        app.getGroupHelper().returnToGroupPage();
        List<GroupData> after = app.getGroupHelper().getGroupList();

        Assert.assertEquals(after.size(), before.size());
        for (GroupData group : after) {
            Assert.assertNotEquals(group.getId(), deletedId);
        }
        Assert.assertEquals(new HashSet<>(after), new HashSet<>(before));

    }

}

