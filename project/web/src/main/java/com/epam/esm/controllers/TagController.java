package com.epam.esm.controllers;

import com.epam.esm.dtos.TagDto;
import com.epam.esm.hateoas.impl.TagHateoasAdder;
import com.epam.esm.services.TagService;
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

    public TagController(TagService service, TagHateoasAdder hateoasAdder) {
        this.service = service;
        this.hateoasAdder = hateoasAdder;
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
                .map(TagDto::toDto)
                .peek(dto -> new TagHateoasAdder().addLink(dto))
                .collect(Collectors.toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id){
        TagDto tagDto=TagDto.toDto(service.getById(id));
        hateoasAdder.addLink(tagDto);
        return ResponseEntity.ok(tagDto);
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody TagDto dto){
        TagDto tagDto=TagDto.toDto(service.insert(dto));
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
