package com.epam.esm.service;

import com.epam.esm.dtos.GiftCertificateDto;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Tag;
import com.epam.esm.services.GiftCertificateService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GiftCertificateServiceTest {

    @Mock
    private final GiftCertificateService service;

    public GiftCertificateServiceTest(GiftCertificateService service) {
        this.service = service;
    }

    private static final Tag TAG_2 = new Tag(2, "tagName3");

    private static final GiftCertificate GIFT_CERTIFICATE_1 = new GiftCertificate(1, "giftCertificate1",
            "description1", new BigDecimal("10.10"), 1, LocalDateTime.parse("2020-08-29T06:12:15"),
            LocalDateTime.parse("2020-08-29T06:12:15"), Arrays.asList(new Tag(2, "tagName3"), new Tag(5, "tagName2")));
    private static final GiftCertificate GIFT_CERTIFICATE_2 = new GiftCertificate(2, "giftCertificate3",
            "description3", new BigDecimal("30.30"), 3, LocalDateTime.parse("2019-08-29T06:12:15"),
            LocalDateTime.parse("2019-08-29T06:12:15"), Collections.singletonList(new Tag(2, "tagName3")));
    private static final GiftCertificate GIFT_CERTIFICATE_3 = new GiftCertificate(3, "giftCertificate2",
            "description2", new BigDecimal("20.20"), 2, LocalDateTime.parse("2018-08-29T06:12:15"),
            LocalDateTime.parse("2018-08-29T06:12:15"), Collections.singletonList(new Tag(3, "tagName5")));




    @Test
    public void getAll() {
        Page<GiftCertificate> actual = service.getAll(Pageable.ofSize(3));
        Page<GiftCertificate> expected = (PageImpl) Arrays.asList(GIFT_CERTIFICATE_1,
                GIFT_CERTIFICATE_2, GIFT_CERTIFICATE_3);

        assertEquals(expected, actual);
    }

    @Test
    public void getById() {
        Optional<GiftCertificate> actual = Optional.of(service.getById(GIFT_CERTIFICATE_2.getId()));
        Optional<GiftCertificate> expected = Optional.of(GIFT_CERTIFICATE_2);

        assertEquals(expected, actual);
    }

    @Test
    public void insert(){

        Assertions.assertEquals(service.insert(GiftCertificateDto.toDto(GIFT_CERTIFICATE_2)), GIFT_CERTIFICATE_2);
        Assertions.assertEquals(service.insert(GiftCertificateDto.toDto(GIFT_CERTIFICATE_1)), GIFT_CERTIFICATE_1);
        Assertions.assertEquals(service.insert(GiftCertificateDto.toDto(GIFT_CERTIFICATE_3)), GIFT_CERTIFICATE_3);
    }

    @Test
    public void delete(){

        Assertions.assertTrue(service.delete(1L));
        Assertions.assertTrue(service.delete(2L));
        Assertions.assertTrue(service.delete(3L));
        Assertions.assertTrue(service.delete(4L));
        Assertions.assertTrue(service.delete(5L));
    }

    @Test
    public void searchGiftCertificateByTags() {

        Page<GiftCertificate> actual = service.searchGiftCertificateByTags(Arrays.asList(TAG_2), Pageable.ofSize(3));
        Page<GiftCertificate> expected = (PageImpl) Arrays.asList(GIFT_CERTIFICATE_1, GIFT_CERTIFICATE_2);

        assertEquals(expected, actual);
    }
}
