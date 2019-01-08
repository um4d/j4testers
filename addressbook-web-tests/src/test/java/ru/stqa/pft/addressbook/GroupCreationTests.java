package ru.stqa.pft.addressbook;

import java.util.concurrent.TimeUnit;

import org.testng.annotations.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

public class GroupCreationTests extends TestBase{

    @Test
    public void testGroupCreation() throws Exception {

        gotoGroupPage();
        initGroupCreation();
        fillGroupForm(new GroupData(
                "test_group_name",
                "test_group_header",
                "test_group_footer"));
        submitGroupCreation();
        returnToGroupPage();

    }

}

