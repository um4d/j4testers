package ru.stqa.pft.addressbook.sandbox;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class HbConnectionTest {

    private SessionFactory sessionFactory;

    @BeforeMethod
    protected void setUp() throws Exception {
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testHbConnection() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<ContactData> result = session.createQuery(
                "from ContactData where deprecated = '0000-00-00'" ).list();
        List<GroupData> result2 = session.createQuery(
                "from GroupData" ).list();
        session.getTransaction().commit();
        session.close();
        for ( GroupData contact : result2 ) {
            System.out.println(contact);
            System.out.println("-------------------");
            System.out.println(contact.getContacts());
        }

    }

}

