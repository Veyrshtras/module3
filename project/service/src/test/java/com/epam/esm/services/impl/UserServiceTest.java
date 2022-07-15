package com.epam.esm.services.impl;

import com.epam.esm.entities.User;
import com.epam.esm.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static com.epam.esm.EntitiesForServicesTest.USER_1;
import static com.epam.esm.EntitiesForServicesTest.USER_2;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl service;

    @BeforeEach
    void setup(){
        this.service=new UserServiceImpl(repository);
    }

    Pageable pageable= PageRequest.of(0,5);

    @Test
    public void getAllTest(){
        Page<User> listUsers=new PageImpl<>(Arrays.asList(USER_1, USER_2));
        when(repository.findAll(pageable)).thenReturn(listUsers);
        Page<User> actual = service.getAll(pageable);
        Page<User> expected = listUsers;

        assertEquals(expected, actual);
    }


    @Test
    public void getByIdTest(){

        when(repository.findById(USER_2.getId())).thenReturn(Optional.of(USER_2));
        Optional<User> actual = Optional.of(service.getById(USER_2.getId()));
        Optional<User> expected = Optional.of(USER_2);

        assertEquals(expected, actual);
    }
}
