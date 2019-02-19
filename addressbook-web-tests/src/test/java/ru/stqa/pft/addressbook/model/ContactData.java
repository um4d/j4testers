package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class ContactData {
    private int id;
    private String name;
    private String middleName;
    private String lastName;
    private String nick;
    private String title;
    private String company;
    private String address;
    private String phoneHome;
    private String phoneMobile;
    private String phoneWork;
    private String faxPhone;
    private String email_1;
    private String email_2;
    private String email_3;
    private String homepage;
    private String bday;
    private String bMonth;
    private String bYear;
    private String aDay;
    private String aMonth;
    private String aYear;
    private String group;
    private String address_2;
    private String phone_2;
    private String notes;
    private String allPhones;


    public String getAllPhones() {
        return allPhones;
    }


    public int getId() {
        return id;
    }

    public ContactData withId(int id) {
        this.id = id;
        return this;
    }

    public ContactData withName(String name) {
        this.name = name;
        return this;
    }

    public ContactData withLname(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ContactData withGroup(String group) {
        this.group = group;
        return this;
    }

    public ContactData withPhoneHome(String phone) {
        this.phoneHome = phone;
        return this;
    }

    public ContactData withPhoneMobile(String phone) {
        this.phoneMobile = phone;
        return this;
    }

    public ContactData withPhoneWork(String phone) {
        this.phoneWork = phone;
        return this;
    }

    public ContactData withEmail1(String email) {
        this.email_1 = email;
        return this;
    }

    public ContactData withEmail2(String email) {
        this.email_2 = email;
        return this;
    }

    public ContactData withEmail3(String email) {
        this.email_3 = email;
        return this;
    }

    public ContactData withAddress(String address) {
        this.address = address;
        return this;
    }

    public ContactData withAllPhones(String allPhones) {
        this.allPhones = allPhones;
        return this;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName);
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

    public String getPhoneHome() {
        return phoneHome;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public String getPhoneWork() {
        return phoneWork;
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

    @Override
    public String toString() {
        return "ContactData{" + "id=" + id + ", name='" + name + '\'' + ", lastName='" + lastName + '\'' + ", address" +
                "='" + address + '\'' + ", phoneHome='" + phoneHome + '\'' + ", phoneMobile='" + phoneMobile + '\'' + ", phoneWork='" + phoneWork + '\'' + ", email_1='" + email_1 + '\'' + ", email_2='" + email_2 + '\'' + ", email_3='" + email_3 + '\'' + '}';
    }
}
