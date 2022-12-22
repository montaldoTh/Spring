package com.tmonta.first.spring.model;
import com.fasterxml.jackson.annotation.JsonView;
import com.tmonta.first.spring.view.StatusView;
import com.tmonta.first.spring.view.UserView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({UserView.class, StatusView.class})
    private Integer id;

    @JsonView({UserView.class, StatusView.class})
    private String name, firstname;

    @JsonView(UserView.class)
    @ManyToOne
    private Status status;

    @JsonView(UserView.class)
    @ManyToMany
    @JoinTable(
            name = "user_skill",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id")
    )
    private Set<Skill> skillList = new HashSet<>();
}