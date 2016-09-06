package ua.nure.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.nure.hibernate.entity.User;

import java.io.IOException;

public class Demo {

    public static void main(String[] args) throws IOException {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        User firstUser = (User) session.get(User.class, 1);

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        session.beginTransaction();

        User secondUser = (User)session.get(User.class, 1); // select executes (first level cache works only during session)

        session.getTransaction().commit();
        session.close();
    }
}
