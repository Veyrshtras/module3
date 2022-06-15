package com.epam.esm.service;

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

    private static final User USER_1 = new User(1, "name1");
    // TODO: 15.06.2022 create new class for static entities
    private static final GiftCertificate GIFT_CERTIFICATE_1 = new GiftCertificate(1, "giftCertificate1",
            "description1", new BigDecimal("10.10"), 1, LocalDateTime.parse("2020-08-29T06:12:15"),
            LocalDateTime.parse("2020-08-29T06:12:15"), Arrays.asList(new Tag(2, "tagName3"), new Tag(5, "tagName2")));
    private static final GiftCertificate GIFT_CERTIFICATE_2 = new GiftCertificate(2, "giftCertificate3",
            "description3", new BigDecimal("30.30"), 3, LocalDateTime.parse("2019-08-29T06:12:15"),
            LocalDateTime.parse("2019-08-29T06:12:15"), Collections.singletonList(new Tag(2, "tagName3")));

    private static final Order ORDER_1 = new Order(1, new BigDecimal("10.10"),
            LocalDateTime.parse("2018-08-29T06:12:15"), USER_1, GIFT_CERTIFICATE_1);
    private static final Order ORDER_2 = new Order(2, new BigDecimal("30.30"),
            LocalDateTime.parse("2018-08-29T06:12:15"), USER_1, GIFT_CERTIFICATE_2);


    // TODO: 15.06.2022 adding Test all methods after name
    @Test
    public void getAll(){

        Page<Order> actual = service.getAll(Pageable.ofSize(2));
        Page<GiftCertificate> expected = (PageImpl) Arrays.asList(ORDER_1,
                ORDER_2);

        assertEquals(expected, actual);
    }

    @Test
    public void getById(){

        Optional<Order> actual = Optional.of(service.getById(ORDER_2.getId()));
        Optional<Order> expected = Optional.of(ORDER_2);

        assertEquals(expected, actual);
    }

    @Test
    public void insert(){
        Assertions.assertEquals(service.insert(OrderDto.toDto(ORDER_1)),ORDER_1);
        Assertions.assertEquals(service.insert(OrderDto.toDto(ORDER_2)),ORDER_2);
    }

    @Test
    public void delete(){
        Assertions.assertTrue(service.delete(1L));
        Assertions.assertTrue(service.delete(2L));
    }

    @Test
    public void getOrdersByUserId(){
        List<Order> expected = Arrays.asList(ORDER_1, ORDER_2);
        List<Order> actual = (List<Order>) service.getOrdersByUserId(ORDER_1.getUser().getId(), Pageable.ofSize(10));

        assertEquals(expected, actual);
    }


}
