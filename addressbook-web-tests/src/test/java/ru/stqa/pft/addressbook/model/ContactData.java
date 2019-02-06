package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;

    private final String name;
    private final String middleName;
    private final String lastName;
    private final String nick;
    private final String title;
    private final String company;
    private final String address;
    private final String homePhone;
    private final String mobilePhone;
    private final String workPhone;
    private final String faxPhone;
    private final String email_1;
    private final String email_2;
    private final String email_3;
    private final String homepage;
    private final String bday;
    private final String bMonth;
    private final String bYear;
    private final String aDay;
    private final String aMonth;
    private final String aYear;
    private final String group;
    private final String address_2;
    private final String phone_2;
    private final String notes;

    public ContactData(String name, String middleName, String lastName, String nick, String title, String company, String address, String homePhone, String mobilePhone, String workPhone, String faxPhone, String email_1, String email_2, String email_3, String homepage, String bday, String bMonth, String bYear, String aDay, String aMonth, String aYear, String group, String address_2, String phone_2, String notes) {
        this.id = Integer.MAX_VALUE;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nick = nick;
        this.title = title;
        this.company = company;
        this.address = address;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.faxPhone = faxPhone;
        this.email_1 = email_1;
        this.email_2 = email_2;
        this.email_3 = email_3;
        this.homepage = homepage;
        this.bday = bday;
        this.bMonth = bMonth;
        this.bYear = bYear;
        this.aDay = aDay;
        this.aMonth = aMonth;
        this.aYear = aYear;
        this.group = group;
        this.address_2 = address_2;
        this.phone_2 = phone_2;
        this.notes = notes;
    }

    public ContactData(int id, String name, String middleName, String lastName, String nick, String title, String company, String address, String homePhone, String mobilePhone, String workPhone, String faxPhone, String email_1, String email_2, String email_3, String homepage, String bday, String bMonth, String bYear, String aDay, String aMonth, String aYear, String group, String address_2, String phone_2, String notes) {
        this.id = id;
        this.name = name;
        this.middleName = middleName;
        this.lastName = lastName;
        this.nick = nick;
        this.title = title;
        this.company = company;
        this.address = address;
        this.homePhone = homePhone;
        this.mobilePhone = mobilePhone;
        this.workPhone = workPhone;
        this.faxPhone = faxPhone;
        this.email_1 = email_1;
        this.email_2 = email_2;
        this.email_3 = email_3;
        this.homepage = homepage;
        this.bday = bday;
        this.bMonth = bMonth;
        this.bYear = bYear;
        this.aDay = aDay;
        this.aMonth = aMonth;
        this.aYear = aYear;
        this.group = group;
        this.address_2 = address_2;
        this.phone_2 = phone_2;
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "ContactData{" + "id=" + id + ", name='" + name + '\'' + ", lastName='" + lastName + '\'' + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName);
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNick() {
        return nick;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getAddress() {
        return address;
    }

    public String getHomePhone() {
        return homePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getWorkPhone() {
        return workPhone;
    }

    public String getFaxPhone() {
        return faxPhone;
    }

    public String getEmail_1() {
        return email_1;
    }

    public String getEmail_2() {
        return email_2;
    }

    public String getEmail_3() {
        return email_3;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getBday() {
        return bday;
    }

    public String getbMonth() {
        return bMonth;
    }

    public String getbYear() {
        return bYear;
    }

    public String getaDay() {
        return aDay;
    }

    public String getaMonth() {
        return aMonth;
    }

    public String getaYear() {
        return aYear;
    }

    public String getGroup() {
        return group;
    }

    public String getAddress_2() {
        return address_2;
    }

    public String getPhone_2() {
        return phone_2;
    }

    public String getNotes() {
        return notes;
    }

}
