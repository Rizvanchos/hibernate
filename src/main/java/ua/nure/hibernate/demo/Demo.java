package ua.nure.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.nure.hibernate.entity.User;
import ua.nure.hibernate.entity.Vehicle;

import java.io.IOException;
import java.util.Date;

public class Demo {

    public static void main(String[] args) throws IOException {
        User user = new User();
        user.setUsername("User");
        user.setBirthday(new Date());
        user.setDescription("User description");

        Vehicle vehicle = new Vehicle();
        vehicle.setName("Car");

        user.setVehicle(vehicle);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);
        session.save(vehicle);

        session.getTransaction().commit();
        session.close();
    }
}
