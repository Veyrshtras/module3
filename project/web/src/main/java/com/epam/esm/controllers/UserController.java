package com.epam.esm.controllers;

import com.epam.esm.dtos.UserDto;
import com.epam.esm.hateoas.impl.UserHateoasAdder;
import com.epam.esm.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService service;
    private final UserHateoasAdder hateoasAdder;
    private final ModelMapper mapper;

    public UserController(UserService service, UserHateoasAdder hateoasAdder, ModelMapper mapper) {
        this.service = service;
        this.hateoasAdder = hateoasAdder;
        this.mapper = mapper;
    }

    @GetMapping("/fillTable")
    public ResponseEntity fillTable(){
        service.fillEntity();
        return ResponseEntity.status(HttpStatus.OK).body("Table filled successfully");
    }

    @GetMapping
    public ResponseEntity getAll(Pageable pageable){
        return ResponseEntity.ok(service.getAll(pageable)
                .stream()
                .map(user -> mapper.map(user, UserDto.class))
                .peek(dto -> new UserHateoasAdder().addLink(dto))
                .collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id){
        UserDto userDto=mapper.map(service.getById(id), UserDto.class);
        hateoasAdder.addLink(userDto);
        return ResponseEntity.ok(userDto);
    }


}
