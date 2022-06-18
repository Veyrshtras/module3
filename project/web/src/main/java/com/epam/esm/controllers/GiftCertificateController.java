package com.epam.esm.controllers;

import com.epam.esm.dtos.GiftCertificateDto;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Tag;
import com.epam.esm.hateoas.impl.GiftCertificateHateoasAdder;
import com.epam.esm.services.GiftCertificateService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/v1/certificates")
public class GiftCertificateController {

    private final GiftCertificateService service;
    private final GiftCertificateHateoasAdder hateoasAdder;
    private final ModelMapper mapper;

    public GiftCertificateController(GiftCertificateService service, GiftCertificateHateoasAdder hateoasAdder, ModelMapper mapper) {
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
        return ResponseEntity.ok(service.getAll(pageable).stream()
                        .map(giftCertificate -> mapper.map(giftCertificate, GiftCertificateDto.class))
                        .peek(dto -> new GiftCertificateHateoasAdder().addLink(dto))
                        .collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity insert(@RequestBody GiftCertificateDto dto){
        GiftCertificate giftCertificate=service.insert(dto);
        GiftCertificateDto giftCertificateDto= mapper.map(giftCertificate, GiftCertificateDto.class);
        hateoasAdder.addLink(giftCertificateDto);
        return ResponseEntity.ok(giftCertificateDto);
    }

    @PatchMapping("{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody GiftCertificateDto dto){
        GiftCertificateDto giftCertificateDto=service.update(id, dto);
        hateoasAdder.addLink(giftCertificateDto);
        return ResponseEntity.ok(giftCertificateDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id){

        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("{id}")
    public ResponseEntity getById(@PathVariable Long id){

        GiftCertificate giftCertificate=service.getById(id);
        GiftCertificateDto giftCertificateDto=mapper.map(giftCertificate, GiftCertificateDto.class);
        hateoasAdder.addLink(giftCertificateDto);
        return ResponseEntity.ok(giftCertificateDto);
    }


    // TODO: 17.06.2022 I fix it. 
    @GetMapping("/search")
    public ResponseEntity searchGiftCertificateByTags(@RequestBody List<Tag> tags, Pageable pageable ){
        return ResponseEntity.ok(service.searchGiftCertificateByTags(tags, pageable)
                .stream()
                .map(giftCertificate -> mapper.map(giftCertificate, GiftCertificateDto.class))
                .peek(dto -> new GiftCertificateHateoasAdder().addLink(dto))
                .collect(Collectors.toList()));
    }

}
