package com.epam.esm.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "orders")
@EntityListeners(AuditingEntityListener.class)
public class Order extends BaseEntity{

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "purchase_time")
    @CreatedDate
    private LocalDateTime purchaseTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private GiftCertificate certificate;

    public Order(long id, BigDecimal cost, LocalDateTime purchaseTime, User user, GiftCertificate certificate) {
        super(id);
        this.cost = cost;
        this.purchaseTime = purchaseTime;
        this.user = user;
        this.certificate = certificate;
    }

    public Order(){

    }

}
