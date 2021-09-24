package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.orm.hibernate5.HibernateTransactionManager;


import javax.persistence.Query;
import java.util.List;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }

    @Override
    public User findUserByCar(String model, int series) {
        return userDao.findUserByCar(model, series);
    }

    @Override
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }


}
