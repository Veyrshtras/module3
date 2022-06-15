package com.epam.esm.service;

import com.epam.esm.EntitiesTest;
import com.epam.esm.dtos.OrderDto;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Order;
import com.epam.esm.entities.Tag;
import com.epam.esm.entities.User;
import com.epam.esm.services.OrderService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private final OrderService service;

    public OrderServiceTest(OrderService service) {
        this.service = service;
    }

    @Test
    public void getAllTest(){

        Page<Order> actual = service.getAll(Pageable.ofSize(2));
        Page<GiftCertificate> expected = (PageImpl) Arrays.asList(EntitiesTest.ORDER_1,
                EntitiesTest.ORDER_2);

        assertEquals(expected, actual);
    }

    @Test
    public void getByIdTest(){

        Optional<Order> actual = Optional.of(service.getById(EntitiesTest.ORDER_2.getId()));
        Optional<Order> expected = Optional.of(EntitiesTest.ORDER_2);

        assertEquals(expected, actual);
    }

    @Test
    public void insertTest(){
        Assertions.assertEquals(service.insert(OrderDto.toDto(EntitiesTest.ORDER_1)),EntitiesTest.ORDER_1);
        Assertions.assertEquals(service.insert(OrderDto.toDto(EntitiesTest.ORDER_2)),EntitiesTest.ORDER_2);
    }

    @Test
    public void deleteTest(){
        Assertions.assertTrue(service.delete(1L));
        Assertions.assertTrue(service.delete(2L));
    }

    @Test
    public void getOrdersByUserIdTest(){
        List<Order> expected = Arrays.asList(EntitiesTest.ORDER_1, EntitiesTest.ORDER_2);
        List<Order> actual = (List<Order>) service.getOrdersByUserId(EntitiesTest.ORDER_1.getUser().getId(), Pageable.ofSize(10));

        assertEquals(expected, actual);
    }


}
