package com.doit.controller;

import com.doit.dao.UserDAO;

import com.doit.model.User;
import com.doit.service.UserService;
import com.doit.service.UserServiceImpl;
import com.doit.util.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    private UserValidator userValidator;



    @Autowired
private UserService userService;



    @GetMapping("/view/{name}")
    public  String view(@PathVariable("name")String name, Model model){
        model.addAttribute("msg","Hillo"+name);
        return "index";
    }
    @GetMapping("/raw")
    @ResponseBody
    public String raw(){

        return "raw";
    }
    @GetMapping("/users")
    public String getUser(Model model) throws SQLException {
        model.addAttribute("users",userService.getAll());
        return "/users";
    }
    @GetMapping("/users/new")
    public String getSingUp(Model model){
        model.addAttribute("user",new User());
        return "sing_up";

    }

    @PostMapping("/users/new")
    public String singUp(@ModelAttribute @Valid  User user, BindingResult result) throws SQLException {
        userValidator.validate(user,result);
        if(result.hasErrors()) return "sing_up";
        userService.add(user);
        return "redirect:/users";

    }
}
