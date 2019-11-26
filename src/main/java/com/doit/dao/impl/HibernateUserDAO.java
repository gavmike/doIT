package com.doit.dao.impl;

import com.doit.dao.UserDAO;
import com.doit.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;
@Component
public class HibernateUserDAO implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession(){
        return sessionFactory.openSession();
    }
    @Override
    public List<User> getAll() throws SQLException {
        return currentSession().createQuery("from User", User.class).list();
    }

    @Override
    public void add(User user) throws SQLException {
        currentSession().save(user);

    }

    @Override
    public User getOne(String email) throws SQLException {
        Query<User> q = currentSession().createQuery("from User where email=:email",User.class);
        q.setParameter("email",email);
        return q.stream().findAny().orElse(null);
    }
}
