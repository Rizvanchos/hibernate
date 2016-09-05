package ua.nure.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.nure.hibernate.entity.Address;
import ua.nure.hibernate.entity.User;

import java.util.Date;

public class Demo {

    public static void main(String[] args) {
        User user = new User();
        user.setUsername("User");
        user.setBirthday(new Date());
        user.setDescription("User description");

        Address homeAddress = new Address();
        homeAddress.setCity("Kharkiv");
        homeAddress.setStreet("Sumskay");

        Address officeAddress = new Address();
        officeAddress.setCity("Kiev");
        officeAddress.setStreet("Main");

        user.getAddresses().add(homeAddress);
        user.getAddresses().add(officeAddress);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();

        User retrievedUser = (User) session.get(User.class, 1);
        System.out.println(retrievedUser.getUsername());
    }
}
