package com.epam.esm.service;

import com.epam.esm.EntitiesForServicesTest;
import com.epam.esm.dtos.GiftCertificateDto;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.services.GiftCertificateService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GiftCertificateServiceTest {

    @Mock
    private final GiftCertificateService service;
    private final ModelMapper mapper;

    public GiftCertificateServiceTest(GiftCertificateService service, ModelMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Test
    public void getAllTest() {
        Page<GiftCertificate> actual = service.getAll(Pageable.ofSize(3));
        Page<GiftCertificate> expected = (PageImpl) Arrays.asList(EntitiesForServicesTest.GIFT_CERTIFICATE_1,
                EntitiesForServicesTest.GIFT_CERTIFICATE_2, EntitiesForServicesTest.GIFT_CERTIFICATE_3);

        assertEquals(expected, actual);
    }

    @Test
    public void getByIdTest() {
        Optional<GiftCertificate> actual = Optional.of(service.getById(EntitiesForServicesTest.GIFT_CERTIFICATE_2.getId()));
        Optional<GiftCertificate> expected = Optional.of(EntitiesForServicesTest.GIFT_CERTIFICATE_2);

        assertEquals(expected, actual);
    }

    @Test
    public void insertTest(){

        Assertions.assertEquals(service.insert(mapper.map(EntitiesForServicesTest.GIFT_CERTIFICATE_2, GiftCertificateDto.class))
                , EntitiesForServicesTest.GIFT_CERTIFICATE_2);
        Assertions.assertEquals(service.insert(mapper.map(EntitiesForServicesTest.GIFT_CERTIFICATE_1, GiftCertificateDto.class))
                , EntitiesForServicesTest.GIFT_CERTIFICATE_1);
        Assertions.assertEquals(service.insert(mapper.map(EntitiesForServicesTest.GIFT_CERTIFICATE_3, GiftCertificateDto.class))
                , EntitiesForServicesTest.GIFT_CERTIFICATE_3);
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

        Page<GiftCertificate> actual = service.searchGiftCertificateByTags(Arrays.asList(EntitiesForServicesTest.TAG_2), Pageable.ofSize(3));
        Page<GiftCertificate> expected = (PageImpl) Arrays.asList(EntitiesForServicesTest.GIFT_CERTIFICATE_1, EntitiesForServicesTest.GIFT_CERTIFICATE_2);

        assertEquals(expected, actual);
    }
}
