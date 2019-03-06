package ru.stqa.pft.addressbook.model;

import com.google.gson.annotations.Expose;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "addressbook")
public class ContactData {
    @Id
    @Column(name = "id")
    private int id;

    public Groups getGroups() {
        return new Groups(groups);
    }

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "address_in_groups", joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupData> groups = new HashSet<>();

    @Expose
    @Column(name = "firstname")
    private String name;

    @Transient
    private String middleName;

    @Expose
    @Column(name = "lastname")
    private String lastName;

    @Transient
    private String nick;
    @Transient
    private String title;
    @Transient
    private String company;

    @Expose
    @Column(name = "address")
    @Type(type = "text")
    private String address;

    @Expose
    @Column(name = "home")
    @Type(type = "text")
    private String phoneHome;

    @Expose
    @Column(name = "mobile")
    @Type(type = "text")
    private String phoneMobile;

    @Expose
    @Column(name = "work")
    @Type(type = "text")
    private String phoneWork;

    @Transient
    private String faxPhone;

    @Override
    public String toString() {
        return "ContactData{" + "id=" + id + ", name='" + name + '\'' + ", lastName='" + lastName + '\'' + ", address" +
                "='" + address + '\'' + ", allPhones='" + allPhones + '\'' + ", allEmails='" + allEmails + '\'' + '}';
    }

    @Expose
    @Column(name = "email")
    @Type(type = "text")
    private String email_1;

    @Expose
    @Column(name = "email2")
    @Type(type = "text")
    private String email_2;

    @Expose
    @Column(name = "email3")
    @Type(type = "text")
    private String email_3;

    @Transient
    private String homepage;
    @Transient
    private String bday;
    @Transient
    private String bMonth;
    @Transient
    private String bYear;
    @Transient
    private String aDay;
    @Transient
    private String aMonth;
    @Transient
    private String aYear;
    @Transient
    private String address_2;
    @Transient
    private String phone_2;
    @Transient
    private String notes;
    @Transient
    private String allPhones;
    @Transient
    private String allEmails;
    @Column
    @Type(type = "text")
    @Transient
    private String photo;

    public ContactData withPhoto(File photo) {
        this.photo = photo.getPath();
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContactData that = (ContactData) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(allPhones, that.allPhones) && Objects.equals(allEmails, that.allEmails);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, address, allPhones, allEmails);
    }

    public File getPhoto() {
        return new File(photo);
    }
    public String getAllEmails() {
        return allEmails;
    }
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

    public ContactData withGroup(GroupData group) {
        this.groups.add(group);
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

    public ContactData withAllEmails(String allEmails) {
        this.allEmails = allEmails;
        return this;
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
