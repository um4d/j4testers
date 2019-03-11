package ru.stqa.pft.mantis.model;

public class UserData {

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getMail() {
        return mail;
    }


    private int id;
    private String username;
    private String mail;

    public UserData withId(int id) {
        this.id = id;
        return this;
    }

    public UserData withMail(String mail) {
        this.mail = mail;
        return this;
    }

    public UserData withName(String name) {
        this.username = name;
        return this;
    }


    @Override
    public String toString() {
        return "UserData{" + "id=" + id + ", username='" + username + '\'' + '}';
    }
}
