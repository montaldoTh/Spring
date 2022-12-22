package com.tmonta.first.spring.controller;
import com.tmonta.first.spring.DataAccessObjet.UserDAO;
import com.tmonta.first.spring.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserDAO userDao;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id){

        return userDao.findById(id);
    }

    @GetMapping("/users")
    public List<User> getUsers(){

        return userDao.findAll();
    }

}
