package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class RegistrationTests extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException, InterruptedException {
        String email = String.format("user%s@localhost", new Random().nextInt(9999));
        String user = String.format("user%s", new Random().nextInt(9999));
        String password = "password";
        app.registration().start(user, email);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String link = findConfirmationLink(mailMessages, email);
        app.registration().finish(link, user, password);
        Thread.sleep(1000);
        Assert.assertTrue(app.newSession().login(user, password));
    }

    private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
        MailMessage message = mailMessages.stream().filter((m)
                -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex()
                                .find("http://").nonSpace().oneOrMore().build();
        return regex.getText(message.text);
    }

    @AfterMethod
    public void stoptMailServer() {
        app.mail().stop();
    }


}



