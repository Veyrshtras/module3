package com.epam.esm.services.impl;

import com.epam.esm.configs.DBConfig;
import com.epam.esm.configs.TablesFiller;
import com.epam.esm.dtos.TagDto;
import com.epam.esm.entities.Tag;
import com.epam.esm.exceptions.*;
import com.epam.esm.repositories.TagRepository;
import com.epam.esm.services.TagService;
import com.epam.esm.validations.IdValidation;
import com.epam.esm.validations.TagValidation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TagServiceImpl implements TagService {

    private static final String FIND_MOST_WIDELY_USED_TAG_OF_USER_WITH_HIGHEST_COST_OF_ALL_ORDERS_QUERY ="SELECT t FROM "
            + "GiftCertificate g INNER JOIN g.tags t WHERE g.id IN (SELECT o.giftCertificate.id FROM Order o "
            + "WHERE o.user.id IN (SELECT o.user.id FROM Order o GROUP BY o.user.id ORDER BY SUM(o.price) DESC)) "
            + "GROUP BY t.id ORDER BY COUNT(t.id) DESC";

    private final TagRepository repository;

    public TagServiceImpl(TagRepository repository) {
        this.repository = repository;
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
        Tag tag=TagDto.fromDto(dto);
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
    public TagDto getMostPopularTagOfUserWithHighestCostOfAllOrders() {

        return TagDto.toDto((Tag) new DBConfig().getEntityManager()
                .createQuery( FIND_MOST_WIDELY_USED_TAG_OF_USER_WITH_HIGHEST_COST_OF_ALL_ORDERS_QUERY)
                .getResultList()
                .stream().findFirst().get());
    }
}
