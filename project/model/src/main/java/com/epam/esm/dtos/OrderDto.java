package com.epam.esm.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class OrderDto extends RepresentationModel<OrderDto> {

    private long id;
    private BigDecimal cost;
    private LocalDateTime purchaseTime;
    private long userId;
    private long certificateId;

}
