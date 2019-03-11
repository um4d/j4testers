package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.appmanager.HttpSession;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PasswordChangeTests extends TestBase{

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testPasswordChange() throws IOException, SQLException, MessagingException {
        HttpSession session = app.newSession();
        app.pass().login("administrator", "root");
        UserData user = app.pass().getUserFromDb();
        app.pass().resetUserPassword(user);
        List<MailMessage> messages = app.mail().waitForMail(1, 10000);
        String link = findConfirmationLink(messages, user.getMail());
        String newPassword = "newpassword2";
        app.pass().finish(link, user.getUsername(), newPassword);
        Assert.assertTrue(session.login(user.getUsername(), newPassword));
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
