package com.epam.esm.hateoas.impl;

import com.epam.esm.controllers.GiftCertificateController;
import com.epam.esm.controllers.TagController;
import com.epam.esm.dtos.GiftCertificateDto;
import com.epam.esm.hateoas.HateoasAdder;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class GiftCertificateHateoasAdder implements HateoasAdder<GiftCertificateDto> {

    private static final Class<GiftCertificateController> CONTROLLER = GiftCertificateController.class;
    private static final Class<TagController> TAG_CONTROLLER = TagController.class;

    @Override
    public void addLink(GiftCertificateDto giftCertificateDto) {

        giftCertificateDto.add(linkTo(methodOn(CONTROLLER)
                .getById(giftCertificateDto.getId())).withSelfRel());
        giftCertificateDto.add(linkTo(methodOn(CONTROLLER)
                .update(giftCertificateDto.getId(), giftCertificateDto)).withRel("update"));
        giftCertificateDto.add(linkTo(methodOn(CONTROLLER)
                .delete(giftCertificateDto.getId())).withRel("delete"));
        giftCertificateDto.add(linkTo(methodOn(CONTROLLER).insert(giftCertificateDto)).withRel("new"));
        giftCertificateDto.getTags().forEach(
                tagDto -> tagDto.add(linkTo(methodOn(TAG_CONTROLLER).getById(tagDto.getId())).withSelfRel()));
    }
}
