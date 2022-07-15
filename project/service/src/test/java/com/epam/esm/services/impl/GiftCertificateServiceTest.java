package com.epam.esm.services.impl;

import com.epam.esm.dtos.GiftCertificateDto;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.repositories.GiftCertificateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import static com.epam.esm.EntitiesForServicesTest.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GiftCertificateServiceTest {


    @Mock
    private ModelMapper mapper;

    @Mock
    private GiftCertificateRepository repository;

    @InjectMocks
    private GiftCertificateServiceImpl service;


    Pageable pageable= PageRequest.of(0,5);

    @BeforeEach
    void setup(){
        this.service=new GiftCertificateServiceImpl(repository, mapper);
    }

    @Test
    public void getAllTest() {

        service.getAll(pageable);
        verify(repository).findAll(pageable);
    }

    @Test
    public void getByIdTest() {

        when(repository.findById(GIFT_CERTIFICATE_2.getId()))
                .thenReturn(Optional.of(GIFT_CERTIFICATE_2));

        GiftCertificate actual = service.getById(GIFT_CERTIFICATE_2.getId());
        GiftCertificate expected = GIFT_CERTIFICATE_2;

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void insertTest(){
        GiftCertificateDto dto=mapper.map(GIFT_CERTIFICATE_1, GiftCertificateDto.class);
        when(mapper.map(dto, GiftCertificate.class)).thenReturn(GIFT_CERTIFICATE_1);
        when(repository.findByName("giftCertificate1")).thenReturn(Optional.empty());
        when(repository.save(mapper.map(dto, GiftCertificate.class)))
                .thenReturn(GIFT_CERTIFICATE_1);
        GiftCertificate actual=service.insert(dto);

        Assertions.assertEquals(actual
                , GIFT_CERTIFICATE_1);

    }

    @Test
    public void updateTest(){

        GiftCertificateDto dto=new GiftCertificateDto();

        dto.setName(GIFT_CERTIFICATE_2.getName());
        when(mapper.map(dto, GiftCertificate.class)).thenReturn(GIFT_CERTIFICATE_2);
        when(repository.findById(GIFT_CERTIFICATE_2.getId())).thenReturn(Optional.of(GIFT_CERTIFICATE_2));
        when(repository.save(GIFT_CERTIFICATE_2))
                .thenReturn(GIFT_CERTIFICATE_2);
        when(mapper.map(GIFT_CERTIFICATE_2, GiftCertificateDto.class)).thenReturn(dto);

        GiftCertificateDto actual=service.update(GIFT_CERTIFICATE_2.getId(), dto);

        Assertions.assertEquals(actual.getName()
                , GIFT_CERTIFICATE_2.getName());

    }
    @Test
    public void deleteTest(){

        Long id = GIFT_CERTIFICATE_2.getId();
        when(repository.findById(id)).thenReturn(Optional.of(GIFT_CERTIFICATE_2));
        doNothing().when(repository).deleteById(GIFT_CERTIFICATE_2.getId());
        service.delete(id);
        verify(repository).findById(id);
        verify(repository).deleteById(GIFT_CERTIFICATE_2.getId());
    }

    @Test
    public void searchGiftCertificateByTagsTest() {

        Page<GiftCertificate> listGiftCertificates=(new PageImpl<>((
                Arrays.asList(GIFT_CERTIFICATE_1, GIFT_CERTIFICATE_2))));
        when(repository.findAll()).thenReturn(listGiftCertificates.getContent());

        Page<GiftCertificate> actual = service.searchGiftCertificateByTags(Collections.singletonList(TAG_2), pageable);
        Page<GiftCertificate> expected = listGiftCertificates;

        assertEquals(expected.getContent(), actual.getContent());
    }
}
