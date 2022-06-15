package com.epam.esm.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
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

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private GiftCertificate certificate;

    public Order(int i, BigDecimal bigDecimal, LocalDateTime parse, User user1, GiftCertificate giftCertificate1) {
        super();
    }
    public Order(){

    }
}
