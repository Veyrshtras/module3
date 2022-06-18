package com.epam.esm.service;

import com.epam.esm.EntitiesForServicesTest;
import com.epam.esm.dtos.OrderDto;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Order;
import com.epam.esm.services.OrderService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private final OrderService service;
    private final ModelMapper mapper;

    public OrderServiceTest(OrderService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Test
    public void getAllTest(){

        Page<Order> actual = service.getAll(Pageable.ofSize(2));
        Page<GiftCertificate> expected = (PageImpl) Arrays.asList(EntitiesForServicesTest.ORDER_1,
                EntitiesForServicesTest.ORDER_2);

        assertEquals(expected, actual);
    }

    @Test
    public void getByIdTest(){

        Optional<Order> actual = Optional.of(service.getById(EntitiesForServicesTest.ORDER_2.getId()));
        Optional<Order> expected = Optional.of(EntitiesForServicesTest.ORDER_2);

        assertEquals(expected, actual);
    }

    @Test
    public void insertTest(){
        Assertions.assertEquals(service.insert(mapper.map(EntitiesForServicesTest.ORDER_1, OrderDto.class)), EntitiesForServicesTest.ORDER_1);
        Assertions.assertEquals(service.insert(mapper.map(EntitiesForServicesTest.ORDER_2, OrderDto.class)), EntitiesForServicesTest.ORDER_2);
    }

    @Test
    public void deleteTest(){
        Assertions.assertTrue(service.delete(1L));
        Assertions.assertTrue(service.delete(2L));
    }

    @Test
    public void getOrdersByUserIdTest(){
        List<Order> expected = Arrays.asList(EntitiesForServicesTest.ORDER_1, EntitiesForServicesTest.ORDER_2);
        List<Order> actual = (List<Order>) service.getOrdersByUserId(EntitiesForServicesTest.ORDER_1.getUser().getId(), Pageable.ofSize(10));

        assertEquals(expected, actual);
    }


}
