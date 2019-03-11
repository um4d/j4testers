package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.tests.TestBase;

import java.sql.*;

public class PasswordHelper extends HelperBase {

    public PasswordHelper(ApplicationManager app) {
        super(app);
    }

    public void login(String username, String password) {
        wd.get("http://localhost/mantisbt/");
        type((By.name("username")), username);
        click(By.xpath("//input[@type='submit']"));
        type((By.name("password")), password);
        click(By.xpath("//input[@type='submit']"));
    }

    public void resetUserPassword(UserData user) {
        wd.get("http://localhost/mantisbt/manage_user_page.php");
        click(By.xpath(String.format
                ("//a[@href='manage_user_edit_page.php?user_id=%s']",
                        user.getId())));
        click(By.xpath("//input[@value='Сбросить пароль']"));
    }

    public UserData getUserFromDb () throws SQLException {
        UserData user = new UserData();
        String url = "jdbc:mysql://localhost:3306/bugtracker?useUnicode=true&useJDBCCompliantTimezoneShift=true"
                + "&useLegacyDatetimeCode=false&serverTimezone=UTC&user=root&password=";
        Connection connection = DriverManager.getConnection(url);
        Statement statement = connection.createStatement();
        String query = "select id, username, email from mantis_user_table where username!='administrator' limit 1";
        ResultSet result = statement.executeQuery(query);
        if (result.next()) {
            user = new UserData().withId(result.getInt("id"))
                    .withName(result.getString("username"))
                    .withMail(result.getString("email"));
        }
        connection.close();
        System.out.println(user);
        return user;
    }

    public void finish(String link, String name, String password) {
        wd.get(link);
        type(By.name("realname"), name);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.xpath("//button[@type='submit']"));
    }
}
