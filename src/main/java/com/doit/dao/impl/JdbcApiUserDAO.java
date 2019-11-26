package com.doit.dao.impl;

import com.doit.dao.UserDAO;

import com.doit.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import com.doit.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;





@Component
public class JdbcApiUserDAO implements UserDAO {
    private static Connection conn;



    static {
        String url = null;
        String username = null;
        String password = null;
        try (InputStream in = JdbcApiUserDAO.class.getClassLoader().getResourceAsStream("persistenc.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println(url);
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAll() throws SQLException {
        List<User> users = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement("select * from users");
              ResultSet rs = ps.executeQuery();
        while (rs.next()){
            User user = new User();
            user.setName(rs.getString(1));
            user.setSurname(rs.getString(2));
            user.setEmail(rs.getString(3));
            users.add(user);


        }
        return users;
    }



  /*  public User getOne(String email) throws SQLException {
        PreparedStatement prepSat = conn.prepareStatement("select * from users where email=?");
        prepSat.setString(1, email);
        ResultSet resultSet = prepSat.executeQuery();
        if (resultSet.next()) {
            User user = new User();
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setEmail(resultSet.getString("email"));
            return user;

        }
        return null;
    }*/

    public User getOne(String email) throws SQLException {

      PreparedStatement prepSat = conn.prepareStatement("select * from users where email=?");
        prepSat.setString(1, email);
        ResultSet resultSet = prepSat.executeQuery();
        if (resultSet.next()) {
            User user = new User();
            user.setName(resultSet.getString("name"));
            user.setSurname(resultSet.getString("surname"));
            user.setEmail(resultSet.getString("email"));
            return user;

        }
        return null;
    }

      public void add(User user) throws SQLException {
           PreparedStatement preparedStatement = conn.prepareStatement("insert into users value (?,?,?)");
           preparedStatement.setString(1, user.getName());
           preparedStatement.setString(2, user.getSurname());
           preparedStatement.setString(3, user.getEmail());
           preparedStatement.execute();

       }

}
