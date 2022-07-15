package com.epam.esm.services;

import com.epam.esm.dtos.GiftCertificateDto;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GiftCertificateService {

    void fillEntity();

    Page<GiftCertificate> getAll(Pageable pageable);

    GiftCertificate getById(long id);

    GiftCertificate insert(GiftCertificateDto dto);

    GiftCertificateDto update(long id, GiftCertificateDto dto);

    boolean delete(long id);

    Page<GiftCertificate> searchGiftCertificateByTags(List<Tag> tags, Pageable pageable);

}
