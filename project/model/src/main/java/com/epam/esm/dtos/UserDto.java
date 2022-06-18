package com.epam.esm.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.util.List;

@Getter
@Setter
public class UserDto extends RepresentationModel<UserDto> {

    private Long id;
    private String name;
    private List<OrderDto> order;

}
