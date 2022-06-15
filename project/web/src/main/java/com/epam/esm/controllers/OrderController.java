package com.epam.esm.controllers;

import com.epam.esm.dtos.OrderDto;
import com.epam.esm.hateoas.impl.OrderHateoasAdder;
import com.epam.esm.services.OrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {

    private final OrderService service;
    private final OrderHateoasAdder hateoasAdder;

    public OrderController(OrderService service, OrderHateoasAdder hateoasAdder) {
        this.service = service;
        this.hateoasAdder = hateoasAdder;
    }

    @GetMapping
    public ResponseEntity getAll(Pageable pageable){
        return ResponseEntity.ok(service.getAll(pageable).stream()
                .map(OrderDto::toDto)
                .peek(dto -> new OrderHateoasAdder().addLink(dto))
                .collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id){
        OrderDto orderDto=OrderDto.toDto(service.getById(id));
        hateoasAdder.addLink(orderDto);
        return ResponseEntity.ok(orderDto);
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody OrderDto dto){
        OrderDto orderDto=OrderDto.toDto(service.insert(dto));
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
                .map(OrderDto::toDto)
                .peek(dto -> new OrderHateoasAdder().addLink(dto))
                .collect(Collectors.toList()));
    }


}
