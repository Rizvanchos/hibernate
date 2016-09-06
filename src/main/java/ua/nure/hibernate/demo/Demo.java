package ua.nure.hibernate.demo;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.nure.hibernate.entity.User;

import java.io.IOException;
import java.util.List;

public class Demo {

    public static void main(String[] args) throws IOException {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // **********************************************************

        Query query = session.getNamedQuery("User.byId");
        query.setInteger("id", 5);

        List<User> users = query.list();

        for (User user : users) {
            System.out.println(user.getUsername());
        }

        // **********************************************************

        query = session.getNamedQuery("User.byName");
        query.setString("name", "User 10");

        users = query.list();

        for (User user : users) {
            System.out.println(user.getUsername());
        }

        // **********************************************************

        session.getTransaction().commit();
        session.close();
    }
}
