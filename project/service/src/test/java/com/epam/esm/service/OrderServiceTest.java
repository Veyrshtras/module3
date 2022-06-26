package com.epam.esm.service;

import com.epam.esm.EntitiesForServicesTest;
import com.epam.esm.dtos.OrderDto;
import com.epam.esm.entities.Order;
import com.epam.esm.repositories.OrderRepository;
import com.epam.esm.services.OrderService;
import com.epam.esm.services.impl.OrderServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
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
import static org.mockito.Mockito.*;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private ModelMapper mapper=Mockito.mock(ModelMapper.class);

    @Mock
    private OrderServiceImpl service=Mockito.mock(OrderServiceImpl.class);
    Pageable pageable= PageRequest.of(0,5);
    @Test
    public void getAllTest(){

        Page<Order> listOrders=new PageImpl<>(Arrays.asList(EntitiesForServicesTest.ORDER_1,
                EntitiesForServicesTest.ORDER_2));

        when(service.getAll(pageable)).thenReturn(listOrders);
        Page<Order> actual = service.getAll(pageable);
        Page<Order> expected = listOrders;

        assertEquals(expected, actual);
    }

    @Test
    public void getByIdTest(){

        when(service.getById(EntitiesForServicesTest.ORDER_2.getId())).thenReturn(EntitiesForServicesTest.ORDER_2);
        Optional<Order> actual = Optional.of(service.getById(EntitiesForServicesTest.ORDER_2.getId()));
        Optional<Order> expected = Optional.of(EntitiesForServicesTest.ORDER_2);

        assertEquals(expected, actual);
    }

    @Test
    public void insertTest(){
        when(service.insert(mapper.map(EntitiesForServicesTest.ORDER_1, OrderDto.class))).thenReturn(EntitiesForServicesTest.ORDER_1);
        Assertions.assertEquals(service.insert(mapper.map(EntitiesForServicesTest.ORDER_1, OrderDto.class)), EntitiesForServicesTest.ORDER_1);
        when(service.insert(mapper.map(EntitiesForServicesTest.ORDER_2, OrderDto.class))).thenReturn(EntitiesForServicesTest.ORDER_2);
        Assertions.assertEquals(service.insert(mapper.map(EntitiesForServicesTest.ORDER_2, OrderDto.class)), EntitiesForServicesTest.ORDER_2);
    }

    @Test
    public void deleteTest(){
        when(service.delete(1L)).thenReturn(true);
        Assertions.assertTrue(service.delete(1L));
        when(service.delete(2L)).thenReturn(true);
        Assertions.assertTrue(service.delete(2L));
    }

    @Test
    public void getOrdersByUserIdTest(){
        Page<Order> orderList=new PageImpl<>(Arrays.asList(EntitiesForServicesTest.ORDER_1, EntitiesForServicesTest.ORDER_2));
        when(service.getOrdersByUserId(EntitiesForServicesTest.USER_1.getId(), pageable)).thenReturn(orderList);
        Page<Order> expected = orderList;
        Page<Order> actual = service.getOrdersByUserId(EntitiesForServicesTest.USER_1.getId(), pageable);

        assertEquals(expected, actual);
    }


}
