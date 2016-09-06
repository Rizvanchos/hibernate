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

        Query query = session.createQuery("from User");
        query.setFirstResult(5);
        query.setMaxResults(5);

        List<User> users = query.list();

        for (User user : users) {
            System.out.println(user.getUsername());
        }

        // **********************************************************

        query = session.createQuery("from User where id > 5");
        users = query.list();

        for (User user : users) {
            System.out.println(user.getUsername());
        }

        // **********************************************************

        query = session.createQuery("select username from User");
        List<String> names = query.list();

        names.forEach(System.out::println);

        // **********************************************************

        query = session.createQuery("select id, username from User");
        List<Object[]> results = query.list();

        for (Object[] result : results) {
            System.out.println(result[0] + " " + result[1]);
        }

        // **********************************************************

        session.getTransaction().commit();
        session.close();
    }
}
