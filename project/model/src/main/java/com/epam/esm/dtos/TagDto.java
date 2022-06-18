package com.epam.esm.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
public class TagDto extends RepresentationModel<TagDto> {

    private Long id;
    private String name;
}
