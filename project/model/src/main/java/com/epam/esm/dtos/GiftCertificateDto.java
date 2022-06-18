package com.epam.esm.dtos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class GiftCertificateDto extends RepresentationModel<GiftCertificateDto> {

    private Long id;
    // TODO: 17.06.2022 read about ModelMapper fields
    private String name;
    private String description;
    private BigDecimal price;
    private int duration;
    private LocalDateTime lastUpdateDate;
    private List<TagDto> tags;

    // TODO: 17.06.2022 I should move too servive All toDto and fromDto
}
