package com.tmonta.first.spring.DataAccessObjet;
import com.tmonta.first.spring.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {

    List<User> findAllByFirstnameAndName(String firstname, String name);
}
