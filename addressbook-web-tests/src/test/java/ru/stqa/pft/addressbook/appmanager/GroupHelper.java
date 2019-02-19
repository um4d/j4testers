package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import java.util.List;

public class GroupHelper extends HelperBase{

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void initCreation() {
        click(By.name("new"));
    }

    public void fillForms(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void submitDelete() {
        click(By.name("delete"));
    }

    public void select(int index) {
        wd.findElements(By.name("selected[]")).get(index).click();
    }

    private void selectById(int id) {
        wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
    }

    public void delete(GroupData group) {
        selectById(group.getId());
        submitDelete();
        groupCache = null;
        returnToGroupPage();
    }

    public void initModification() {
        click(By.name("edit"));
    }

    public void submitModification() {
        click(By.name("update"));
    }

    public void create(GroupData group) {
        initCreation();
        fillForms(group);
        submitGroupCreation();
        groupCache = null;
        returnToGroupPage();
    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public void modify(int index, GroupData group) {
        select(index);
        initModification();
        fillForms(group);
        submitModification();
        groupCache = null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectById(group.getId());
        initModification();
        fillForms(group);
        submitModification();
        groupCache = null;
        returnToGroupPage();
    }

    public int count() {
        return wd.findElements(By.name("selected[]")).size();
    }

    private Groups groupCache = null;

    public Groups all() {
        if (groupCache != null) {
            return new Groups(groupCache);
        }
        groupCache = new Groups();
        List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
        for (WebElement element : elements) {
            String name = element.getText();
            int id = Integer.parseInt(element.findElement(By.tagName("input"))
                    .getAttribute("value"));
            GroupData group = new GroupData().withName(name).withId(id);
            groupCache.add(group);
        }
        return new Groups(groupCache);
    }


}
