package com.epam.esm.services.impl;

import com.epam.esm.dtos.OrderDto;
import com.epam.esm.entities.Order;
import com.epam.esm.exceptions.ExceptionResult;
import com.epam.esm.exceptions.IncorrectParameterException;
import com.epam.esm.exceptions.NoSuchEntityException;
import com.epam.esm.repositories.OrderRepository;
import com.epam.esm.services.OrderService;
import com.epam.esm.validations.IdValidation;
import com.epam.esm.validations.OrderValidation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<Order> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Order getById(Long id) {
        ExceptionResult er=new ExceptionResult();
        IdValidation.validateId(id, er);
        if (!er.getExceptionMessages().isEmpty()){
            throw new IncorrectParameterException(er);
        }
        if (!repository.findById(id).isPresent()){
            throw new NoSuchEntityException();
        }
        return repository.findById(id).get();
    }

    @Override
    public Order insert(OrderDto dto) {

        ExceptionResult er=new ExceptionResult();
        Order order=OrderDto.fromDto(dto);
        OrderValidation.validate(order,er);
        if (!er.getExceptionMessages().isEmpty()){
            throw new IncorrectParameterException(er);
        }

        return repository.save(order);
    }

    @Override
    public boolean delete(Long id) {
        ExceptionResult er=new ExceptionResult();
        IdValidation.validateId(id, er);
        if (!er.getExceptionMessages().isEmpty()){
            throw new IncorrectParameterException(er);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public Page<Order> getOrdersByUserId(Long userId, Pageable pageable) {

        ExceptionResult er=new ExceptionResult();
        IdValidation.validateId(userId, er);
        if (!er.getExceptionMessages().isEmpty()){
            throw new IncorrectParameterException(er);
        }

        return new PageImpl<>(repository.findAll().stream()
                .filter(order -> order.getUser().getId()==userId)
                .collect(Collectors.toList()));
    }
}
