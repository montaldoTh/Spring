package com.tmonta.first.spring.DataAccessObjet;
import com.tmonta.first.spring.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusDAO extends JpaRepository<Status, Integer> {

}
