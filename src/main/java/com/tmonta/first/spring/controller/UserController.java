package com.tmonta.first.spring.controller;
import com.tmonta.first.spring.DataAccessObjet.UserDAO;
import com.tmonta.first.spring.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserDAO userDao;

    @GetMapping("/users")
    public List<User> getUsers(){

        return userDao.findAll();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){

        Optional<User> response = userDao.findById(id);

        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody User user){

        User userExist = null;

        if(user.getId() != null){
            userExist = userDao.findById(user.getId()).orElse(null);
            // Si essayé de définir un id a un utilisateur alors qu'auto increment
            if(userExist == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            userDao.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        userDao.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){

        Optional<User> userExist = userDao.findById(id);

        if(userExist.isPresent()) {
            userDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @GetMapping("/user/firstname&name/{firstname}&{name}")
    public List<User> getUsersFirstnameAndName(@PathVariable String firstname, @PathVariable String name){
        return userDao.findAllByFirstnameAndName(firstname, name);
    }

}
