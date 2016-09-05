package ua.nure.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.nure.hibernate.entity.FourWheeler;
import ua.nure.hibernate.entity.TwoWheeler;
import ua.nure.hibernate.entity.Vehicle;

import java.io.IOException;

public class Demo {

    public static void main(String[] args) throws IOException {
        Vehicle car = new Vehicle();
        car.setName("Car");

        TwoWheeler bike = new TwoWheeler();
        bike.setName("Bike");
        bike.setSteeringHandle("Bike Steering handle");

        FourWheeler porsche = new FourWheeler();
        porsche.setName("Porsche");
        porsche.setSteeringWheel("Porsche Steering wheel");

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        session.save(car);
        session.save(bike);
        session.save(porsche);

        session.getTransaction().commit();
        session.close();
    }
}
