package com.tmonta.first.spring.controller;
import com.tmonta.first.spring.DataAccessObjet.SkillDAO;
import com.tmonta.first.spring.model.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SkillController {

    @Autowired
    SkillDAO skillDao;

    @GetMapping("/skills")
    public List<Skill> getSkillList(){

        return skillDao.findAll();
    }

    @GetMapping("/skill/{id}")
    public ResponseEntity<Skill> getSkill(@PathVariable int id){

        Optional<Skill> response = skillDao.findById(id);

        if(response.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response.get(), HttpStatus.OK);
    }

    @PostMapping("/skill")
    public ResponseEntity<Skill> addSkill(@RequestBody Skill skill){

        Skill skillExist = null;

        if(skill.getId() != null){
            skillExist = skillDao.findById(skill.getId()).orElse(null);
            // Si essayé de définir un id a un utilisateur alors qu'auto increment
            if(skillExist == null){
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }

            skillDao.save(skill);
            return new ResponseEntity<>(skill, HttpStatus.OK);
        }
        skillDao.save(skill);
        return new ResponseEntity<>(skill, HttpStatus.CREATED);
    }

    @DeleteMapping("/skill/delete/{id}")
    public ResponseEntity<String> deleteSkill(@PathVariable int id){

        Optional<Skill> skillExist = skillDao.findById(id);

        if(skillExist.isPresent()) {
            skillDao.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
