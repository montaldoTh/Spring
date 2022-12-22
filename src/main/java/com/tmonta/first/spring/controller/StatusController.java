package com.tmonta.first.spring.controller;
import com.fasterxml.jackson.annotation.JsonView;
import com.tmonta.first.spring.DataAccessObjet.StatusDAO;
import com.tmonta.first.spring.model.Status;
import com.tmonta.first.spring.view.StatusView;
import com.tmonta.first.spring.view.UserView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class StatusController {

    @Autowired
    StatusDAO statusDao;

    @GetMapping("/status-list")
    @JsonView(StatusView.class)
    public List<Status> getStatusList(){

        return statusDao.findAll();
    }

    @GetMapping("/status/{id}")
    @JsonView(StatusView.class)
    public ResponseEntity<Status> getStatus(@PathVariable int id){

        Optional<Status> response = statusDao.findById(id);

        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }

    @PostMapping("/status")
    public ResponseEntity<Status> addStatus(@RequestBody Status status){

        Status statusExist = null;

        if(status.getId() != null){
            statusExist = statusDao.findById(status.getId()).orElse(null);
            // Si essayé de définir un id a un utilisateur alors qu'auto increment
            if(statusExist == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            statusDao.save(status);
            return new ResponseEntity<>(status, HttpStatus.OK);
        }
        statusDao.save(status);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @DeleteMapping("/status/delete/{id}")
    public ResponseEntity<String> deleteStatus(@PathVariable int id){

        Optional<Status> statusExist = statusDao.findById(id);

        if(statusExist.isPresent()) {
            statusDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
