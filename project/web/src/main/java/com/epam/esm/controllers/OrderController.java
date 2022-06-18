package com.epam.esm.controllers;

import com.epam.esm.dtos.OrderDto;
import com.epam.esm.hateoas.impl.OrderHateoasAdder;
import com.epam.esm.services.OrderService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService service;
    private final OrderHateoasAdder hateoasAdder;
    private final ModelMapper mapper;

    public OrderController(OrderService service, OrderHateoasAdder hateoasAdder, ModelMapper mapper) {
        this.service = service;
        this.hateoasAdder = hateoasAdder;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity getAll(Pageable pageable){
        return ResponseEntity.ok(service.getAll(pageable).stream()
                .map(order -> mapper.map(order, OrderDto.class))
                .peek(dto -> new OrderHateoasAdder().addLink(dto))
                .collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id){
        OrderDto orderDto=mapper.map(service.getById(id), OrderDto.class);
        hateoasAdder.addLink(orderDto);
        return ResponseEntity.ok(orderDto);
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody OrderDto dto){
        OrderDto orderDto= mapper.map(service.insert(dto), OrderDto.class);
        hateoasAdder.addLink(orderDto);
        return ResponseEntity.ok(orderDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/get-by-user-id")
    public ResponseEntity getOrdersByUserId(@RequestParam Long userId, Pageable pageable){
        return ResponseEntity.ok(service.getOrdersByUserId(userId, pageable)
                .stream()
                .map(order -> mapper.map(order, OrderDto.class))
                .peek(dto -> new OrderHateoasAdder().addLink(dto))
                .collect(Collectors.toList()));
    }


}
