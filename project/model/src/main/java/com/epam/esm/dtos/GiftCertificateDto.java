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
    private String name;
    private String description;
    private BigDecimal price;
    private int duration;
    private LocalDateTime lastUpdateDate;
    private List<TagDto> tags;

}
