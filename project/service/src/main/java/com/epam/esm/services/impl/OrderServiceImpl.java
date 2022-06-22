package com.epam.esm.services.impl;

import com.epam.esm.dtos.OrderDto;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Order;
import com.epam.esm.exceptions.ExceptionResult;
import com.epam.esm.exceptions.IncorrectParameterException;
import com.epam.esm.exceptions.NoSuchEntityException;
import com.epam.esm.repositories.GiftCertificateRepository;
import com.epam.esm.repositories.OrderRepository;
import com.epam.esm.services.OrderService;
import com.epam.esm.validations.IdValidation;
import com.epam.esm.validations.OrderValidation;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.epam.esm.exceptions.ExceptionMessagesKeys.GIFT_CERTIFICATE_NOT_FOUND;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final ModelMapper mapper;
    private final GiftCertificateRepository giftCertificateRepository;

    public OrderServiceImpl(OrderRepository repository, ModelMapper mapper, GiftCertificateRepository giftCertificateRepository) {
        this.repository = repository;
        this.mapper = mapper;
        this.giftCertificateRepository = giftCertificateRepository;
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
        Optional<Order> order=repository.findById(id);
        if (!order.isPresent()){
            throw new NoSuchEntityException();
        }
        return order.get();
    }

    @Override
    @Transactional
    public Order insert(OrderDto dto) {

        Order order =mapper.map(dto, Order.class);
        order.setCost(giftCertificateRepository.findById(dto.getCertificateId()).get().getPrice());
        order.setPurchaseTime(LocalDateTime.now());

        ExceptionResult er=new ExceptionResult();
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
        Optional<Order> order=repository.findById(id);
        if(!order.isPresent()){
            throw new NoSuchEntityException(GIFT_CERTIFICATE_NOT_FOUND);
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
