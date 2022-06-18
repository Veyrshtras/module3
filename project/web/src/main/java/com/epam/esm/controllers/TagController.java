package com.epam.esm.controllers;

import com.epam.esm.dtos.TagDto;
import com.epam.esm.hateoas.impl.TagHateoasAdder;
import com.epam.esm.services.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/tags")
public class TagController {

    private final TagService service;
    private final TagHateoasAdder hateoasAdder;
    private final ModelMapper mapper;

    public TagController(TagService service, TagHateoasAdder hateoasAdder, ModelMapper mapper) {
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
                .map(tag -> mapper.map(tag, TagDto.class))
                .peek(dto -> new TagHateoasAdder().addLink(dto))
                .collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id){
        TagDto tagDto=mapper.map(service.getById(id), TagDto.class);
        hateoasAdder.addLink(tagDto);
        return ResponseEntity.ok(tagDto);
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody TagDto dto){
        TagDto tagDto=mapper.map(service.insert(dto),TagDto.class);
        hateoasAdder.addLink(tagDto);
        return ResponseEntity.ok(tagDto);

    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/get-most-popular")
    public ResponseEntity getMostPopularTagOfUserWithHighestCostOfAllOrders(){
        return ResponseEntity.ok(service.getMostPopularTagOfUserWithHighestCostOfAllOrders());
    }


}
