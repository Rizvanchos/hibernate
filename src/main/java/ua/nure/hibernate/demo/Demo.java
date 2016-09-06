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

        User user = (User) session.get(User.class, 1);
        session.getTransaction().commit();
        session.close();

        user.setUsername("Updated name");

        session = sessionFactory.openSession();
        session.beginTransaction();

        session.update(user); // update query goes only when data in database is different

        session.getTransaction().commit();
        session.close();
    }
}
