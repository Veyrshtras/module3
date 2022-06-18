package com.epam.esm.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "gift_certificates", indexes = @Index(columnList = "name"))
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor

public class GiftCertificate extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "duration")
    private int duration;

    @Column(name = "create_date")
    @CreatedDate
    private LocalDateTime createDate=LocalDateTime.now();

    @Column(name = "last_update_date")
    @LastModifiedDate
    private LocalDateTime lastUpdateDate=LocalDateTime.now();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "gift_certificate_tags", joinColumns = @JoinColumn(nullable = false), inverseJoinColumns = @JoinColumn(nullable = false))
    private List<Tag> tags;

    public GiftCertificate() {

    }

    public <T> GiftCertificate(int i, String giftCertificate1, String description1, BigDecimal bigDecimal, int i1, LocalDateTime parse, LocalDateTime parse1, List<T> asList) {
        super();
    }
}
