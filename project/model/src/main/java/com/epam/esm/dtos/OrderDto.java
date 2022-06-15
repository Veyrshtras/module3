package com.epam.esm.dtos;

import com.epam.esm.configs.DBConfig;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Order;
import com.epam.esm.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.spi.SessionLazyDelegator;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto extends RepresentationModel<OrderDto> {

    private Long id;
    private BigDecimal cost;
    private LocalDateTime purchaseTime;
    private Long userId;
    private Long certificateId;

    public static OrderDto toDto(Order order){
        return new ModelMapper().map(order, OrderDto.class);
//        return new OrderDto(order.getId(), order.getCost(), order.getPurchaseTime(),
//                order.getUser().getId(), order.getCertificate().getId());
    }
    public static Order fromDto(OrderDto order){
        Order item=new Order();
        item.setId(order.getId());
        item.setCost(DBConfig.entityManager.getReference(GiftCertificate.class, order.getCertificateId()).getPrice());
        item.setPurchaseTime(LocalDateTime.now());
        item.setUser(DBConfig.entityManager.getReference(User.class, order.getUserId()));
        item.setCertificate(DBConfig.entityManager.getReference(GiftCertificate.class, order.getCertificateId()));
        return item;
    }

}
