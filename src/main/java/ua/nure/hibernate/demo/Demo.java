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

        Vehicle car = new Vehicle();
        car.setName("Car");
        Vehicle bus = new Vehicle();
        bus.setName("Bus");

        user.getVehicles().add(car);
        user.getVehicles().add(bus);

        car.getUsers().add(user);
        bus.getUsers().add(user);

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(user);
        session.save(car);
        session.save(bus);

        session.getTransaction().commit();
        session.close();

        session = sessionFactory.openSession();
        Vehicle c= (Vehicle) session.get(Vehicle.class, 1);
        for (User user1 : c.getUsers()) {
            System.out.println(user1.getUsername());
        }
    }
}
