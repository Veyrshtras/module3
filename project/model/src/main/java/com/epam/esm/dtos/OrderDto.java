package com.epam.esm.dtos;

import com.epam.esm.configs.DBConfig;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Order;
import com.epam.esm.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
        return new DBConfig().modelMapper().map(order, OrderDto.class);
//        return new OrderDto(order.getId(), order.getCost(), order.getPurchaseTime(),
//                order.getUser().getId(), order.getCertificate().getId());
    }
    public static Order fromDto(OrderDto order){
//        Order item=new Order();
//        GiftCertificate giftCertificate=new DBConfig().getEntityManager().getReference(GiftCertificate.class, new Long(order.getCertificateId()));
//        User user=new DBConfig().getEntityManager().getReference(User.class, new Long(order.getUserId()));
//        item.setCost(giftCertificate.getPrice());
//        item.setPurchaseTime(LocalDateTime.now());
//        item.setUser(user);
//        item.setCertificate(giftCertificate);
        return new DBConfig().modelMapper().map(order, Order.class);
    }

}
