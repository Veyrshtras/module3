package com.epam.esm.services.impl;

import com.epam.esm.configs.TablesFiller;
import com.epam.esm.dtos.GiftCertificateDto;
import com.epam.esm.entities.GiftCertificate;
import com.epam.esm.entities.Tag;
import com.epam.esm.exceptions.*;
import com.epam.esm.repositories.GiftCertificateRepository;
import com.epam.esm.services.GiftCertificateService;
import com.epam.esm.validations.GiftCertificateValidation;
import com.epam.esm.validations.IdValidation;
import com.epam.esm.validations.TagValidation;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.epam.esm.exceptions.ExceptionMessagesKeys.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GiftCertificateServiceImpl implements GiftCertificateService {

    private final GiftCertificateRepository repository;
    private final ModelMapper mapper;

    public GiftCertificateServiceImpl(GiftCertificateRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public void fillEntity() {
        repository.saveAll(TablesFiller.giftCertificates);
    }

    @Override
    @Transactional
    public Page<GiftCertificate> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public GiftCertificate getById(Long id) {

        ExceptionResult er=new ExceptionResult();
        IdValidation.validateId(id, er);

        if (!er.getExceptionMessages().isEmpty()){
            throw new IncorrectParameterException(er);
        }
        Optional<GiftCertificate> giftCertificate=repository.findById(id);
        if(!giftCertificate.isPresent()){
            throw new NoSuchEntityException(GIFT_CERTIFICATE_NOT_FOUND);
        }
        return giftCertificate.get();
    }

    @Override
    @Transactional
    public GiftCertificate insert(GiftCertificateDto dto) {

        ExceptionResult er=new ExceptionResult();
        GiftCertificate giftCertificate=mapper.map(dto, GiftCertificate.class);

        GiftCertificateValidation.validate(giftCertificate, er);

        if (!er.getExceptionMessages().isEmpty()){
            throw new IncorrectParameterException(er);
        }

        if (repository.findByName(giftCertificate.getName()).isPresent()) {
            throw new DuplicateEntityException(ExceptionMessagesKeys.GIFT_CERTIFICATE_EXIST);
        }

        return repository.save(giftCertificate);
    }

    @Override
    @Transactional
    public GiftCertificateDto update(Long id, GiftCertificateDto dto) {

        ExceptionResult er=new ExceptionResult();
        GiftCertificate giftCertificate=mapper.map(dto, GiftCertificate.class);
        GiftCertificateValidation.validateForUpdate(giftCertificate, er);
        if (!er.getExceptionMessages().isEmpty()){
            throw new IncorrectParameterException(er);
        }


        Optional<GiftCertificate> isGiftCertificateExist= repository.findById(id);
        if (!isGiftCertificateExist.isPresent()){
            throw new NoSuchEntityException(ExceptionMessagesKeys.NO_ENTITY);
        }


        GiftCertificate updateCert=isGiftCertificateExist.get();
        updateCert.setName(dto.getName());
        updateCert.setDescription(dto.getDescription());
        updateCert.setPrice(dto.getPrice());
        updateCert.setDuration(dto.getDuration());
        updateCert.setLastUpdateDate(LocalDateTime.now());
        return mapper.map(repository.save(updateCert), GiftCertificateDto.class);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        ExceptionResult er=new ExceptionResult();
        IdValidation.validateId(id, er);
        if (!er.getExceptionMessages().isEmpty()){
            throw new IncorrectParameterException(er);
        }

        Optional<GiftCertificate> isGiftCertificateExist= repository.findById(id);
        if (!isGiftCertificateExist.isPresent()){
            throw new NoSuchEntityException(ExceptionMessagesKeys.NO_ENTITY);
        }

        repository.deleteById(id);
        return true;
    }

    @Override
    public Page<GiftCertificate> searchGiftCertificateByTags(List<Tag> tags, Pageable pageable) {

        ExceptionResult er=new ExceptionResult();

        for (Tag item:
             tags) {
            TagValidation.validate(item, er);
            if (!er.getExceptionMessages().isEmpty()){
                throw new IncorrectParameterException(er);
            }
        }

        return new PageImpl<>(repository.findAll().stream().filter(certificate -> {
            for (Tag item:
                    certificate.getTags()) {
                if (tags.contains(item))
                    return true;
            }
            return false;
        }).collect(Collectors.toList()));

    }
}
