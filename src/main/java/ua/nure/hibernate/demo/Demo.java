package ua.nure.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.nure.hibernate.entity.User;

import java.util.Date;

public class Demo {

    public static void main(String[] args) {
        User firstUser = new User();
        firstUser.setUsername("First user");
        firstUser.setBirthday(new Date());
        firstUser.setDescription("First user description");

        User secondUser = new User();
        secondUser.setUsername("Second user");
        secondUser.setBirthday(new Date());
        secondUser.setDescription("Second user description");

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(firstUser);
        session.save(secondUser);

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        
        User retrievedUser = (User) session.get(User.class, 1);
        System.out.println(retrievedUser.getUsername());
    }
}
