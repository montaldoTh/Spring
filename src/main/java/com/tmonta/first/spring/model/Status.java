package com.tmonta.first.spring.model;
import com.fasterxml.jackson.annotation.JsonView;
import com.tmonta.first.spring.view.StatusView;
import com.tmonta.first.spring.view.UserView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonView({StatusView.class})
    private Integer id;

    @JsonView({UserView.class, StatusView.class})
    private String label;

    @OneToMany(mappedBy = "status")
    @JsonView(StatusView.class)
    private Set<User> userList = new HashSet<>();
}
