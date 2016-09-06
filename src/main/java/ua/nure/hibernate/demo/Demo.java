package ua.nure.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
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

        List<User> users = session.createCriteria(User.class)
                .add(Restrictions.or(Restrictions.between("id", 0, 3), Restrictions.between("id", 5, 8)))
                .addOrder(Order.desc("id"))
                .list();

        for (User user : users) {
            System.out.println(user.getUsername());
        }

        // **********************************************************

        List<Integer> maxId = session.createCriteria(User.class).setProjection(Projections.max("id")).list();

        for (Integer max : maxId) {
            System.out.println(max);
        }

        // **********************************************************

        User userExample = new User();
        userExample.setId(5); // hibernate ignores primary keys and null properties
        userExample.setUsername("User 5");

        Example example = Example.create(userExample);

        users = session.createCriteria(User.class).add(example).list();

        for (User user : users) {
            System.out.println(user.getUsername());
        }

        // **********************************************************

        userExample.setUsername("User%");

        example = Example.create(userExample).enableLike();

        users = session.createCriteria(User.class).add(example).list();

        for (User user : users) {
            System.out.println(user.getUsername());
        }

        // **********************************************************

        session.getTransaction().commit();
        session.close();
    }
}
