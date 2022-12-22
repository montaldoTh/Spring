package com.tmonta.first.spring.DataAccessObjet;
import com.tmonta.first.spring.model.Skill;
import com.tmonta.first.spring.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillDAO extends JpaRepository<Skill, Integer> {

}
