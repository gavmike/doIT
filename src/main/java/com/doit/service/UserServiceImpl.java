package com.doit.service;

import com.doit.model.User;
import com.doit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<User> getAll() throws SQLException {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void add(User user) throws SQLException {
        userRepository.save(user);

    }

    @Override
    public User getOne(String email) throws SQLException {
        return userRepository.findByEmail(email);
    }
}
