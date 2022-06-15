package com.epam.esm.hateoas.impl;

import com.epam.esm.controllers.UserController;
import com.epam.esm.dtos.UserDto;
import com.epam.esm.hateoas.HateoasAdder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class UserHateoasAdder implements HateoasAdder<UserDto> {

    private static final Class<UserController> CONTROLLER = UserController.class;

    @Override
    public void addLink(UserDto userDto) {

        userDto.add(linkTo(methodOn(CONTROLLER).getById(userDto.getId())).withSelfRel());
    }
}
