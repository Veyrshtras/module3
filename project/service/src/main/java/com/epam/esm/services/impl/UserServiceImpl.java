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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
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

        Optional<User> user=repository.findById(id);
        if (!user.isPresent()){
            throw new NoSuchEntityException(ExceptionMessagesKeys.USER_NOT_FOUND);
        }

        return user.get();
    }

    @Override
    public void fillEntity() {
        repository.saveAll(TablesFiller.users);
    }
}
