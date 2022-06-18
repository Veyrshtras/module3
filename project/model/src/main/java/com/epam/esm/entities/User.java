package com.epam.esm.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "users", indexes = @Index(columnList = "name"))
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity{

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<Order> order;

    public User(int i, String name1) {
        super();
    }
    public User(){

    }

}
