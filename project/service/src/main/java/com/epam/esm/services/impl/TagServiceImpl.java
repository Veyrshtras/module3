package com.epam.esm.services.impl;

import com.epam.esm.configs.TablesFiller;
import com.epam.esm.dtos.TagDto;
import com.epam.esm.entities.Tag;
import com.epam.esm.exceptions.*;
import com.epam.esm.repositories.TagRepository;
import com.epam.esm.services.TagService;
import com.epam.esm.validations.IdValidation;
import com.epam.esm.validations.TagValidation;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class TagServiceImpl implements TagService {



    @PersistenceContext
    private EntityManager entityManager;



    private String FIND_MOST_WIDELY_USED_TAG_OF_USER_WITH_HIGHEST_COST_OF_ALL_ORDERS_QUERY ="SELECT * FROM tag WHERE tag.id IN " +
            "(SELECT tags_id FROM" +
            "(SELECT certificate_id from orders WHERE user_id = " +
            "(SELECT user_id FROM orders GROUP BY user_id ORDER BY SUM(cost) DESC LIMIT 1 )) AS tableA  " +
            " INNER JOIN gift_certificate_tags ON gift_certificate_tags.gift_certificate_id = tableA.certificate_id " +
            " GROUP BY tags_id " +
            " order by count(tags_id) desc limit 1)";

    private final TagRepository repository;
    private final ModelMapper mapper;

    public TagServiceImpl( TagRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void fillEntity() {
        repository.saveAll(TablesFiller.tags);
    }

    @Override
    public Page<Tag> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    @Transactional
    public Tag insert(TagDto dto) {
        ExceptionResult er=new ExceptionResult();
        Tag tag=mapper.map(dto, Tag.class);
        TagValidation.validate(tag,er);
        if (!er.getExceptionMessages().isEmpty()){
            throw new IncorrectParameterException(er);
        }

        String tagName= dto.getName();
        if (repository.findByName(tagName).isPresent()){
            throw new DuplicateEntityException(ExceptionMessagesKeys.TAG_EXIST);
        }
        return repository.save(tag);
    }

    @Override
    @Transactional
    public boolean delete(Long id) {
        ExceptionResult er=new ExceptionResult();
        IdValidation.validateId(id, er);
        if (!er.getExceptionMessages().isEmpty()){
            throw new IncorrectParameterException(er);
        }

        if (!repository.findById(id).isPresent()){
            throw new NoSuchEntityException(ExceptionMessagesKeys.TAG_NOT_FOUND);
        }
        repository.deleteById(id);
        return true;
    }

    @Override
    public Tag getById(Long id) {
        ExceptionResult er=new ExceptionResult();
        IdValidation.validateId(id, er);
        if (!er.getExceptionMessages().isEmpty()){
            throw new IncorrectParameterException(er);
        }

        if (!repository.findById(id).isPresent()){
            throw new NoSuchEntityException(ExceptionMessagesKeys.TAG_NOT_FOUND);
        }
        return repository.findById(id).get();
    }

    @Override
    @Transactional
    public TagDto getMostPopularTagOfUserWithHighestCostOfAllOrders() {

        //todo loose new DbConfig
        return mapper.map(entityManager
                .createNativeQuery( FIND_MOST_WIDELY_USED_TAG_OF_USER_WITH_HIGHEST_COST_OF_ALL_ORDERS_QUERY,Tag.class)
                .getResultList()
                .stream().findFirst().get(), TagDto.class);
    }
}
