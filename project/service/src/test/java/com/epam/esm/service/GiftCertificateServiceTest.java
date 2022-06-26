package com.epam.esm.service;

import com.epam.esm.EntitiesForServicesTest;
import com.epam.esm.dtos.GiftCertificateDto;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.services.impl.GiftCertificateServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class GiftCertificateServiceTest {


    @Mock
    private ModelMapper mapper=Mockito.mock(ModelMapper.class);

    @Mock
    private GiftCertificateServiceImpl service=Mockito.mock(GiftCertificateServiceImpl.class);

    Pageable pageable= PageRequest.of(0,5);


    @Test
    public void getAllTest() {

        Page<GiftCertificate> listGiftCertificates=new PageImpl<>(Arrays.asList(EntitiesForServicesTest.GIFT_CERTIFICATE_1,
                EntitiesForServicesTest.GIFT_CERTIFICATE_2, EntitiesForServicesTest.GIFT_CERTIFICATE_3));
        when(service.getAll(pageable)).thenReturn(listGiftCertificates);

        Page<GiftCertificate> actual = service.getAll(pageable);
        Page<GiftCertificate> expected = listGiftCertificates;

        assertEquals(expected, actual);
    }

    @Test
    public void getByIdTest() {
        when(service.getById(EntitiesForServicesTest.GIFT_CERTIFICATE_2.getId())).thenReturn(EntitiesForServicesTest.GIFT_CERTIFICATE_2);

        GiftCertificate actual = service.getById(EntitiesForServicesTest.GIFT_CERTIFICATE_2.getId());
        GiftCertificate expected = EntitiesForServicesTest.GIFT_CERTIFICATE_2;

        assertEquals(expected, actual);
    }

    @Test
    public void insertTest(){

        when(service.insert(mapper.map(EntitiesForServicesTest.GIFT_CERTIFICATE_2, GiftCertificateDto.class)))
                .thenReturn(EntitiesForServicesTest.GIFT_CERTIFICATE_2);
        Assertions.assertEquals(service.insert(mapper.map(EntitiesForServicesTest.GIFT_CERTIFICATE_2, GiftCertificateDto.class))
                , EntitiesForServicesTest.GIFT_CERTIFICATE_2);
        Assertions.assertEquals(service.insert(mapper.map(EntitiesForServicesTest.GIFT_CERTIFICATE_1, GiftCertificateDto.class))
                , EntitiesForServicesTest.GIFT_CERTIFICATE_1);
        Assertions.assertEquals(service.insert(mapper.map(EntitiesForServicesTest.GIFT_CERTIFICATE_3, GiftCertificateDto.class))
                , EntitiesForServicesTest.GIFT_CERTIFICATE_3);
    }

    @Test
    public void deleteTest(){

        when(service.delete(1L)).thenReturn(true);
        Assertions.assertTrue(service.delete(1L));
        when(service.delete(2L)).thenReturn(true);
        Assertions.assertTrue(service.delete(2L));
        when(service.delete(3L)).thenReturn(true);
        Assertions.assertTrue(service.delete(3L));
        when(service.delete(4L)).thenReturn(true);
        Assertions.assertTrue(service.delete(4L));
        when(service.delete(5L)).thenReturn(true);
        Assertions.assertTrue(service.delete(5L));
    }

    @Test
    public void searchGiftCertificateByTagsTest() {

        Page<GiftCertificate> listGiftCertificates=(new PageImpl<>((
                Arrays.asList(EntitiesForServicesTest.GIFT_CERTIFICATE_1, EntitiesForServicesTest.GIFT_CERTIFICATE_2))));
        when(service.searchGiftCertificateByTags(Collections.singletonList(EntitiesForServicesTest.TAG_2), pageable))
                .thenReturn(listGiftCertificates);
        Page<GiftCertificate> actual = service.searchGiftCertificateByTags(Collections.singletonList(EntitiesForServicesTest.TAG_2), pageable);
        Page<GiftCertificate> expected = listGiftCertificates;

        assertEquals(expected, actual);
    }
}
