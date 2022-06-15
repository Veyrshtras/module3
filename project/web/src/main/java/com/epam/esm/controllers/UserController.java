package com.epam.esm.controllers;

import com.epam.esm.dtos.UserDto;
import com.epam.esm.hateoas.impl.UserHateoasAdder;
import com.epam.esm.services.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    private final UserService service;
    private final UserHateoasAdder hateoasAdder;

    public UserController(UserService service, UserHateoasAdder hateoasAdder) {
        this.service = service;
        this.hateoasAdder = hateoasAdder;
    }

    @GetMapping("/fillTable")
    public ResponseEntity fillTable(){
        service.fillEntity();
        return ResponseEntity.status(HttpStatus.OK).body("Table filled successfully");
    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam int pageSize){
        return ResponseEntity.ok(service.getAll(Pageable.ofSize(pageSize)));
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id){
        UserDto userDto=UserDto.toDto(service.getById(id));
        hateoasAdder.addLink(userDto);
        return ResponseEntity.ok(userDto);
    }


}
