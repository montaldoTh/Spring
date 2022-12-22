package com.tmonta.first.spring.controller;
import com.tmonta.first.spring.DataAccessObjet.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {

    @Autowired
    UserDAO userDao;


    @GetMapping("/")
    public String hello(){

        return "<h1>Welcome to this RestAPI made on Java Spring</h1>" +
                "<p>Use the right route for use this API</p>" +
                "<h2>Server Info</h2>" +
                "<p>Server: on</p>";
    }
}
