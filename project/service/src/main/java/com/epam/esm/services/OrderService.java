package com.epam.esm.services;

import com.epam.esm.dtos.OrderDto;
import com.epam.esm.entities.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface OrderService {

    Page<Order> getAll(Pageable pageable);

    Order getById(Long id);

    Order insert(OrderDto dto);

    boolean delete(Long id);

    Page<Order> getOrdersByUserId(Long userId, Pageable pageable);
}
