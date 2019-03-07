package ru.stqa.pft.addressbook.appmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

public class DbHelper {

    private final SessionFactory sessionFactory;

    public DbHelper() {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    }

    public Groups groups() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<GroupData> result = session.createQuery(
                "from GroupData").list();
        session.getTransaction().commit();
        session.close();
        return new Groups(result);
    }

    public Contacts contacts() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery(
                "from ContactData where deprecated = '0000-00-00'").list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result);
    }
    public ContactData contactByParameter(String parameter, String value) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery(
                String.format("from ContactData where %s = '%s' and deprecated = '0000-00-00'"
                , parameter, value)).list();
        session.getTransaction().commit();
        session.close();
        return new Contacts(result).iterator().next();
    }


    public ContactData getContactWithoutGroup() {
        Contacts contacts = contacts();
        for (ContactData c : contacts) {
            if (c.getGroups() == null) {
                return c;
            }
        }
        return null;
    }

    public ContactData getContactWithGroup(GroupData group) {
        Contacts contacts = contacts();
        for (ContactData c : contacts) {
                for (GroupData g : c.getGroups()) {
                    if (g.equals(group)) {
                        return c;
                    }
                }
        }
        return null;
    }
}
