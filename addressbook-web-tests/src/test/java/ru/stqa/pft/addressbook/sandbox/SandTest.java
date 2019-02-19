package ru.stqa.pft.addressbook.sandbox;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.tests.TestBase;
import ru.stqa.pft.addressbook.*;

public class SandTest extends TestBase {

    @Test
    public void sandtest() {
        app.goTo().HomePage();
        System.out.println(app.contacts().all());
    }

}
