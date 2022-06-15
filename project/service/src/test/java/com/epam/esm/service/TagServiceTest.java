package com.epam.esm.service;

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

    private static final Tag TAG_1 = new Tag(1, "tagName1");
    private static final Tag TAG_2 = new Tag(2, "tagName3");
    private static final Tag TAG_3 = new Tag(3, "tagName5");
    private static final Tag TAG_4 = new Tag(4, "tagName4");
    private static final Tag TAG_5 = new Tag(5, "tagName2");


    @Test
    public void getAll(){

        List<Tag> actual = (List<Tag>) service.getAll(Pageable.ofSize(10));
        List<Tag> expected = Arrays.asList(TAG_1, TAG_2, TAG_3, TAG_4, TAG_5);

        assertEquals(expected, actual);
    }

    @Test
    public void getById(){
        Optional<Tag> actual = Optional.of(service.getById(TAG_3.getId()));
        Optional<Tag> expected = Optional.of(TAG_3);

        assertEquals(expected, actual);
    }

    @Test
    public void insert(){
        Assertions.assertEquals(service.insert(TagDto.toDto(TAG_2)),TAG_2);
        Assertions.assertEquals(service.insert(TagDto.toDto(TAG_3)),TAG_3);
    }


    @Test
    public void delete(){
        Assertions.assertTrue(service.delete(1L));
        Assertions.assertTrue(service.delete(2L));
        Assertions.assertTrue(service.delete(3L));
    }

    @Test
    public void getMostPopularTagOfUserWithHighestCostOfAllOrders(){

        Optional<Tag> actual = Optional.of(TagDto.fromDto(service.getMostPopularTagOfUserWithHighestCostOfAllOrders()));
        Optional<Tag> expected = Optional.of(TAG_2);

        assertEquals(expected, actual);
    }

}
