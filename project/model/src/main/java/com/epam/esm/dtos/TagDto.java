package com.epam.esm.dtos;

import com.epam.esm.entities.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagDto extends RepresentationModel<TagDto> {

    private Long id;
    private String name;

    public static TagDto toDto(Tag tag) {
        return new ModelMapper().map(tag, TagDto.class);
//        return new TagDto(tag.getId(), tag.getName());
    }

    public static Tag fromDto(TagDto dto){
        Tag tag=new Tag();
        tag.setId(dto.getId());
        tag.setName(dto.getName());
        return tag;
    }
}
