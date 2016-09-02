package ua.nure.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.nure.hibernate.entity.User;

import java.util.Date;

public class Demo {

    public static void main(String[] args) {
        User user = new User();
        user.setId(1);
        user.setUsername("User");
        user.setBirthday(new Date());

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
        session.close();
    }
}
