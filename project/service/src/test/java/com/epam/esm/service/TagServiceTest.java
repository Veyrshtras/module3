package com.epam.esm.service;

import com.epam.esm.EntitiesForServicesTest;
import com.epam.esm.dtos.TagDto;
import com.epam.esm.entities.Tag;
import com.epam.esm.services.impl.TagServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TagServiceTest {

    @Mock
    private final TagServiceImpl service= Mockito.mock(TagServiceImpl.class);
    @Mock
    private final ModelMapper mapper=Mockito.mock(ModelMapper.class);

   Pageable pageable= PageRequest.of(0,5);

    @Test
    public void getAllTest(){
        Page<Tag> liTags=new PageImpl<>(Arrays.asList(EntitiesForServicesTest.TAG_1, EntitiesForServicesTest.TAG_2,
                EntitiesForServicesTest.TAG_3, EntitiesForServicesTest.TAG_4, EntitiesForServicesTest.TAG_5));
        when(service.getAll(pageable)).thenReturn(liTags);

        Page<Tag> actual = service.getAll(pageable);
        Page<Tag> expected =liTags ;

        assertEquals(expected, actual);
    }

    @Test
    public void getByIdTest(){

        when(service.getById(EntitiesForServicesTest.TAG_3.getId())).thenReturn(EntitiesForServicesTest.TAG_3);

        Optional<Tag> actual = Optional.of(service.getById(EntitiesForServicesTest.TAG_3.getId()));
        Optional<Tag> expected = Optional.of(EntitiesForServicesTest.TAG_3);

        assertEquals(expected, actual);
    }

    @Test
    public void insertTest(){
        when(service.insert(mapper.map(EntitiesForServicesTest.TAG_2, TagDto.class))).thenReturn(EntitiesForServicesTest.TAG_2);
        Assertions.assertEquals(service.insert(mapper.map(EntitiesForServicesTest.TAG_2, TagDto.class)), EntitiesForServicesTest.TAG_2);
        when(service.insert(mapper.map(EntitiesForServicesTest.TAG_3, TagDto.class))).thenReturn(EntitiesForServicesTest.TAG_3);
        Assertions.assertEquals(service.insert(mapper.map(EntitiesForServicesTest.TAG_3, TagDto.class)), EntitiesForServicesTest.TAG_3);
    }


    @Test
    public void deleteTest(){
        when(service.delete(1L)).thenReturn(true);
        Assertions.assertTrue(service.delete(1L));
        when(service.delete(2L)).thenReturn(true);
        Assertions.assertTrue(service.delete(2L));
        when(service.delete(3L)).thenReturn(true);
        Assertions.assertTrue(service.delete(3L));
    }

    @Test
    public void getMostPopularTagOfUserWithHighestCostOfAllOrdersTest(){

        when(mapper.map(service.getMostPopularTagOfUserWithHighestCostOfAllOrders(), Tag.class)).thenReturn(EntitiesForServicesTest.TAG_2);
        Optional<Tag> actual = Optional.of(mapper.map(service.getMostPopularTagOfUserWithHighestCostOfAllOrders(), Tag.class));
        Optional<Tag> expected = Optional.of(EntitiesForServicesTest.TAG_2);

        assertEquals(expected, actual);
    }

}
