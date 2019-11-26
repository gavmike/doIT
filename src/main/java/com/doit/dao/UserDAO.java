package com.doit.dao;

import com.doit.model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    List<User> getAll() throws SQLException;
    void add(User user) throws SQLException;
    User getOne(String email) throws SQLException;
}
