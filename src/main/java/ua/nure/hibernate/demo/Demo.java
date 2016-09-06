package ua.nure.hibernate.demo;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import ua.nure.hibernate.entity.User;

import java.io.IOException;
import java.util.List;

public class Demo {

    public static void main(String[] args) throws IOException {

        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // **********************************************************

        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("username", "User 10"));

        List<User> users = criteria.list();

        for (User user : users) {
            System.out.println(user.getUsername());
        }

        // **********************************************************

        session.getTransaction().commit();
        session.close();
    }
}
