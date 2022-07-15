package com.epam.esm.services.impl;

import com.epam.esm.EntitiesForServicesTest;
import com.epam.esm.dtos.TagDto;
import com.epam.esm.entities.Tag;
import com.epam.esm.repositories.TagRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.Arrays;
import java.util.Optional;

import static com.epam.esm.EntitiesForServicesTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TagServiceTest {


    @Mock
    private ModelMapper mapper=Mockito.mock(ModelMapper.class);

    @Mock
    private EntityManager entityManager;

    @Mock
    private TagRepository repository;

    @InjectMocks
    private TagServiceImpl service;

    @BeforeEach
    void setup(){
        this.service=new TagServiceImpl(repository, mapper);
    }
   Pageable pageable= PageRequest.of(0,5);

    @Test
    public void getAllTest(){
        Page<Tag> liTags=new PageImpl<>(Arrays.asList(EntitiesForServicesTest.TAG_1, EntitiesForServicesTest.TAG_2,
                EntitiesForServicesTest.TAG_3, EntitiesForServicesTest.TAG_4, EntitiesForServicesTest.TAG_5));
        when(repository.findAll(pageable)).thenReturn(liTags);

        Page<Tag> actual = service.getAll(pageable);
        Page<Tag> expected =liTags ;

        assertEquals(expected, actual);
    }

    @Test
    public void getByIdTest(){

        when(repository.findById(TAG_3.getId())).thenReturn(Optional.of(TAG_3));

        Optional<Tag> actual = Optional.of(service.getById(TAG_3.getId()));
        Optional<Tag> expected = Optional.of(TAG_3);

        assertEquals(expected, actual);
    }

    @Test
    public void insertTest(){

        TagDto dto=new TagDto();
        dto.setId(TAG_2.getId());
        dto.setName(TAG_2.getName());
        when(mapper.map(dto, Tag.class)).thenReturn(TAG_2);
        when(repository.findByName(dto.getName())).thenReturn(Optional.empty());
        when(repository.save(mapper.map(dto, Tag.class)))
                .thenReturn(TAG_2);
        Tag actual=service.insert(dto);

        Assertions.assertEquals(actual
                , TAG_2);

    }


    @Test
    public void deleteTest(){

        Long id = TAG_1.getId();
        when(repository.findById(id)).thenReturn(Optional.of(TAG_1));
        doNothing().when(repository).deleteById(TAG_1.getId());
        service.delete(id);
        verify(repository).findById(id);
        verify(repository).deleteById(TAG_1.getId());

    }

    @Test
    public void getMostPopularTagOfUserWithHighestCostOfAllOrdersTest(){

        TagDto tagDto = mapper.map(TAG_2, TagDto.class);
        when(repository.getMostPopularTagOfUserWithHighestCostOfAllOrders()).thenReturn(TAG_2);
        TagDto expected = tagDto;

        TagDto actual = service.getMostPopularTagOfUserWithHighestCostOfAllOrders();
        assertEquals(actual, expected);

    }

}
