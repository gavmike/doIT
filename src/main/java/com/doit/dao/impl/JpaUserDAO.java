package com.doit.dao.impl;

import com.doit.dao.UserDAO;
import com.doit.model.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.sql.SQLException;
import java.util.List;
import java.util.Queue;

@Component
@Transactional(readOnly = true)
public class JpaUserDAO implements UserDAO {
    @PersistenceContext(unitName = "entityManagerFactory")
    private EntityManager entityManager;
    @Override
    public List<User> getAll() throws SQLException {
        return entityManager.createQuery("select u from User u",User.class).getResultList();
    }

    @Override
    @Transactional
    public void add(User user) throws SQLException {
        entityManager.persist(user);

    }

    @Override
    public User getOne(String email) throws SQLException {
        TypedQuery<User>q= entityManager.createQuery("select u from User u where u.email=:email",User.class);
        q.setParameter("email",email);
        return q.getResultList().stream().findAny().orElse(null);
    }
}
