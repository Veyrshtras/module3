package com.epam.esm.service;

import com.epam.esm.EntitiesTest;
import com.epam.esm.dtos.TagDto;
import com.epam.esm.entities.Tag;
import com.epam.esm.services.TagService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TagServiceTest {

    @Mock
    private final TagService service;

    public TagServiceTest(TagService service) {
        this.service = service;
    }

    @Test
    public void getAllTest(){

        List<Tag> actual = (List<Tag>) service.getAll(Pageable.ofSize(10));
        List<Tag> expected = Arrays.asList(EntitiesTest.TAG_1, EntitiesTest.TAG_2, EntitiesTest.TAG_3, EntitiesTest.TAG_4, EntitiesTest.TAG_5);

        assertEquals(expected, actual);
    }

    @Test
    public void getByIdTest(){
        Optional<Tag> actual = Optional.of(service.getById(EntitiesTest.TAG_3.getId()));
        Optional<Tag> expected = Optional.of(EntitiesTest.TAG_3);

        assertEquals(expected, actual);
    }

    @Test
    public void insertTest(){
        Assertions.assertEquals(service.insert(TagDto.toDto(EntitiesTest.TAG_2)),EntitiesTest.TAG_2);
        Assertions.assertEquals(service.insert(TagDto.toDto(EntitiesTest.TAG_3)),EntitiesTest.TAG_3);
    }


    @Test
    public void deleteTest(){
        Assertions.assertTrue(service.delete(1L));
        Assertions.assertTrue(service.delete(2L));
        Assertions.assertTrue(service.delete(3L));
    }

    @Test
    public void getMostPopularTagOfUserWithHighestCostOfAllOrdersTest(){

        Optional<Tag> actual = Optional.of(TagDto.fromDto(service.getMostPopularTagOfUserWithHighestCostOfAllOrders()));
        Optional<Tag> expected = Optional.of(EntitiesTest.TAG_2);

        assertEquals(expected, actual);
    }

}
