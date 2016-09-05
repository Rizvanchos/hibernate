package ua.nure.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.nure.hibernate.entity.User;

import java.io.IOException;

public class Demo {

    public static void main(String[] args) throws IOException {

        // *** Transient object ***
        User user = new User();
        user.setUsername("User");

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // *** Persistent object (all updates will be triggered) ***
        session.save(user);

        user.setUsername("First name change");
        user.setUsername("Second name change"); // Only this update will go to database (hibernate takes latest state before commit)

        session.getTransaction().commit();
        session.close();

        // *** Detached object (hibernate doesn't trigger changes) ***
        user.setUsername("Another User");
    }
}
