package com.epam.esm.dtos;

import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GiftCertificateDto extends RepresentationModel<GiftCertificateDto> {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private int duration;
    private LocalDateTime lastUpdateDate;
    private List<TagDto> tags;

    public static GiftCertificateDto toDto(GiftCertificate certificate) {
        return new GiftCertificateDto(certificate.getId(), certificate.getName(), certificate.getDescription(), certificate.getPrice(),
                certificate.getDuration(), certificate.getLastUpdateDate(), certificate.getTags().stream()
                .map(TagDto::toDto)
                .collect(Collectors.toList()));
    }

    // TODO: 15.06.2022 I should check ModelMapper for converting  
    public static GiftCertificate fromDto(GiftCertificateDto dto){
        GiftCertificate obj=new GiftCertificate();
        obj.setName(dto.getName());
        obj.setDescription(dto.getDescription());
        obj.setPrice(dto.getPrice());
        obj.setDuration(dto.getDuration());
        if(dto.getTags()!=null)
            obj.setTags(dto.getTags().stream()
                .map(tagDto -> new Tag(tagDto.getName()))
                .collect(Collectors.toList()));
        return obj;
    }
}
