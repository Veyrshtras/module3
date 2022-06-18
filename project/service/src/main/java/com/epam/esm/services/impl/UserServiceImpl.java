package com.epam.esm.services.impl;

import com.epam.esm.configs.TablesFiller;
import com.epam.esm.entities.User;
import com.epam.esm.exceptions.ExceptionMessagesKeys;
import com.epam.esm.exceptions.ExceptionResult;
import com.epam.esm.exceptions.IncorrectParameterException;
import com.epam.esm.exceptions.NoSuchEntityException;
import com.epam.esm.repositories.UserRepository;
import com.epam.esm.services.UserService;
import com.epam.esm.validations.IdValidation;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final ModelMapper mapper;

    public UserServiceImpl(UserRepository repository, ModelMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Page<User> getAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public User getById(Long id) {
        ExceptionResult er=new ExceptionResult();
        IdValidation.validateId(id, er);
        if (!er.getExceptionMessages().isEmpty()){
            throw new IncorrectParameterException(er);
        }

        if (!repository.findById(id).isPresent()){
            throw new NoSuchEntityException(ExceptionMessagesKeys.USER_NOT_FOUND);
        }

        return repository.findById(id).get();
    }

    @Override
    public void fillEntity() {
        repository.saveAll(TablesFiller.users);
    }
}
