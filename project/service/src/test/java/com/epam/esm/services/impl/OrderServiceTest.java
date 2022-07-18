package com.epam.esm.services.impl;

import com.epam.esm.dtos.OrderDto;
import com.epam.esm.entities.Order;
import com.epam.esm.repositories.GiftCertificateRepository;
import com.epam.esm.repositories.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static com.epam.esm.EntitiesForServicesTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private ModelMapper mapper;

    @Mock
    private OrderRepository repository;

    @Mock
    private GiftCertificateRepository giftCertificateRepository;

    @InjectMocks
    private OrderServiceImpl service;


    Pageable pageable= PageRequest.of(0,5);

    @Test
    public void getAllTest(){

        Page<Order> listOrders=new PageImpl<>(Arrays.asList(ORDER_1,
                ORDER_2));


        when(repository.findAll(pageable)).thenReturn((PageImpl)listOrders);
        Page<Order> actual = service.getAll(pageable);
        Page<Order> expected = listOrders;

        assertEquals(expected.getContent(), actual.getContent());
    }

    @Test
    public void getByIdTest(){

        when(repository.findById(ORDER_2.getId())).thenReturn(Optional.of(ORDER_2));
        Optional<Order> actual = Optional.of(service.getById(ORDER_2.getId()));
        Optional<Order> expected = Optional.of(ORDER_2);

        assertEquals(expected, actual);
    }

    @Test
    public void insertTest(){

        OrderDto dto=new OrderDto();
        dto.setId(ORDER_1.getId());
        dto.setCertificateId(ORDER_1.getCertificate().getId());
        dto.setUserId(ORDER_1.getUser().getId());
        when(mapper.map(dto, Order.class)).thenReturn(ORDER_1);
        when(giftCertificateRepository.findById(dto.getCertificateId())).thenReturn(Optional.of(GIFT_CERTIFICATE_1));
        when(repository.save(mapper.map(dto, Order.class)))
                .thenReturn(ORDER_1);
        Order actual=service.insert(dto);

        Assertions.assertEquals(actual
                , ORDER_1);

    }

    @Test
    public void deleteTest(){

        Long id = ORDER_1.getId();
        when(repository.findById(id)).thenReturn(Optional.of(ORDER_1));
        doNothing().when(repository).deleteById(ORDER_1.getId());
        service.delete(id);
        verify(repository).findById(id);
        verify(repository).deleteById(ORDER_1.getId());

    }

    @Test
    public void getOrdersByUserIdTest(){

        long id=USER_1.getId();
        Page<Order> orderList=new PageImpl<>(Arrays.asList(ORDER_1, ORDER_2));
        when(repository.findAll()).thenReturn(orderList.getContent());
        Page<Order> expected = orderList;
        Page<Order> actual = service.getOrdersByUserId(id, pageable);

        assertEquals(expected.getContent(), actual.getContent());
    }


}
