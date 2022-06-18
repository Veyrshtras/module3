package com.epam.esm.service;

import com.epam.esm.EntitiesForServicesTest;
import com.epam.esm.dtos.TagDto;
import com.epam.esm.entities.Tag;
import com.epam.esm.services.TagService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TagServiceTest {

    @Mock
    private final TagService service;
    private final ModelMapper mapper;

    public TagServiceTest(TagService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Test
    public void getAllTest(){

        List<Tag> actual = (List<Tag>) service.getAll(Pageable.ofSize(10));
        List<Tag> expected = Arrays.asList(EntitiesForServicesTest.TAG_1, EntitiesForServicesTest.TAG_2, EntitiesForServicesTest.TAG_3, EntitiesForServicesTest.TAG_4, EntitiesForServicesTest.TAG_5);

        assertEquals(expected, actual);
    }

    @Test
    public void getByIdTest(){
        Optional<Tag> actual = Optional.of(service.getById(EntitiesForServicesTest.TAG_3.getId()));
        Optional<Tag> expected = Optional.of(EntitiesForServicesTest.TAG_3);

        assertEquals(expected, actual);
    }

    @Test
    public void insertTest(){
        Assertions.assertEquals(service.insert(mapper.map(EntitiesForServicesTest.TAG_2, TagDto.class)), EntitiesForServicesTest.TAG_2);
        Assertions.assertEquals(service.insert(mapper.map(EntitiesForServicesTest.TAG_3, TagDto.class)), EntitiesForServicesTest.TAG_3);
    }


    @Test
    public void deleteTest(){
        Assertions.assertTrue(service.delete(1L));
        Assertions.assertTrue(service.delete(2L));
        Assertions.assertTrue(service.delete(3L));
    }

    @Test
    public void getMostPopularTagOfUserWithHighestCostOfAllOrdersTest(){

        Optional<Tag> actual = Optional.of(mapper.map(service.getMostPopularTagOfUserWithHighestCostOfAllOrders(), Tag.class));
        Optional<Tag> expected = Optional.of(EntitiesForServicesTest.TAG_2);

        assertEquals(expected, actual);
    }

}
