package com.epam.esm.hateoas.impl;

import com.epam.esm.controllers.OrderController;
import com.epam.esm.dtos.OrderDto;
import com.epam.esm.hateoas.HateoasAdder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class OrderHateoasAdder implements HateoasAdder<OrderDto> {

    private static final Class<OrderController> CONTROLLER = OrderController.class;

    @Override
    public void addLink(OrderDto orderDto) {

        orderDto.add(linkTo(methodOn(CONTROLLER).getById(orderDto.getId())).withSelfRel());
        orderDto.add(linkTo(methodOn(CONTROLLER).insert(orderDto)).withRel("new"));
    }
}
