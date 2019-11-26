package com.doit.util;

import com.doit.dao.UserDAO;

import com.doit.model.User;
import com.doit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.sql.SQLException;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;

        try {
            if(userService.getOne(user.getEmail())!=null) errors.rejectValue("email","","email alredy had");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
