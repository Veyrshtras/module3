package com.epam.esm.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table( indexes = @Index(columnList = "name"))
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Tag extends BaseEntity {

    @Column(name = "name")//, unique = true)
    private String name;

    public Tag(long id, String name) {
        super(id);
        this.name = name;
    }

    public Tag(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this==obj)
            return true;
        if(!(obj instanceof Tag))
            return false;
        return this.getName().equals(((Tag)obj).getName());
    }
}
