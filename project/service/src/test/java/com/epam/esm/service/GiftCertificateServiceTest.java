package com.epam.esm.service;

import com.epam.esm.EntitiesTest;
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

    @Test
    public void getAllTest() {
        Page<GiftCertificate> actual = service.getAll(Pageable.ofSize(3));
        Page<GiftCertificate> expected = (PageImpl) Arrays.asList(EntitiesTest.GIFT_CERTIFICATE_1,
                EntitiesTest.GIFT_CERTIFICATE_2, EntitiesTest.GIFT_CERTIFICATE_3);

        assertEquals(expected, actual);
    }

    @Test
    public void getByIdTest() {
        Optional<GiftCertificate> actual = Optional.of(service.getById(EntitiesTest.GIFT_CERTIFICATE_2.getId()));
        Optional<GiftCertificate> expected = Optional.of(EntitiesTest.GIFT_CERTIFICATE_2);

        assertEquals(expected, actual);
    }

    @Test
    public void insertTest(){

        Assertions.assertEquals(service.insert(GiftCertificateDto.toDto(EntitiesTest.GIFT_CERTIFICATE_2)), EntitiesTest.GIFT_CERTIFICATE_2);
        Assertions.assertEquals(service.insert(GiftCertificateDto.toDto(EntitiesTest.GIFT_CERTIFICATE_1)), EntitiesTest.GIFT_CERTIFICATE_1);
        Assertions.assertEquals(service.insert(GiftCertificateDto.toDto(EntitiesTest.GIFT_CERTIFICATE_3)), EntitiesTest.GIFT_CERTIFICATE_3);
    }

    @Test
    public void deleteTest(){

        Assertions.assertTrue(service.delete(1L));
        Assertions.assertTrue(service.delete(2L));
        Assertions.assertTrue(service.delete(3L));
        Assertions.assertTrue(service.delete(4L));
        Assertions.assertTrue(service.delete(5L));
    }

    @Test
    public void searchGiftCertificateByTagsTest() {

        Page<GiftCertificate> actual = service.searchGiftCertificateByTags(Arrays.asList(EntitiesTest.TAG_2), Pageable.ofSize(3));
        Page<GiftCertificate> expected = (PageImpl) Arrays.asList(EntitiesTest.GIFT_CERTIFICATE_1, EntitiesTest.GIFT_CERTIFICATE_2);

        assertEquals(expected, actual);
    }
}
