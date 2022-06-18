package com.epam.esm.entities;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
@NoArgsConstructor
public class Tag extends BaseEntity {

    @Column(name = "name")//, unique = true)
    private String name;

    public Tag(int i, String tagName3) {
        super();
    }
}
