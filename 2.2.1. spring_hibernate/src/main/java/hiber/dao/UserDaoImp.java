package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    public User findUserByCar(String model, int i) {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        Query query = session.createQuery("from Car where model = :Model and series = :Series");
        query.setParameter("Model", model);
        query.setParameter("Series", i);

        Car singleResult = (Car) query.getSingleResult();
        session.close();
        return singleResult.getUser();
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction tx1 = session.beginTransaction();
        try {
            session.createSQLQuery("DELETE FROM users WHERE ID > 0;").executeUpdate();
            session.createSQLQuery("DELETE FROM car WHERE ID > 0;").executeUpdate();
            tx1.commit();
        } catch (HibernateException e) {
            tx1.rollback();
        } finally {
            session.close();
        }

    }

}
