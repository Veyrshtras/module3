package com.epam.esm.service;

import com.epam.esm.EntitiesForServicesTest;
import com.epam.esm.entities.User;
import com.epam.esm.services.UserService;
import com.epam.esm.services.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private final UserServiceImpl service= Mockito.mock(UserServiceImpl.class);


    @Test
    public void getAllTest(){
        Pageable pageable= PageRequest.of(0,5);
        Page<User> listUsers=new PageImpl<>(Arrays.asList(EntitiesForServicesTest.USER_1, EntitiesForServicesTest.USER_2));
        when(service.getAll(pageable)).thenReturn(listUsers);
        Page<User> actual = service.getAll(pageable);
        Page<User> expected = listUsers;

        assertEquals(expected, actual);
    }


    @Test
    public void getByIdTest(){

        when(service.getById(EntitiesForServicesTest.USER_2.getId())).thenReturn(EntitiesForServicesTest.USER_2);
        Optional<User> actual = Optional.of(service.getById(EntitiesForServicesTest.USER_2.getId()));
        Optional<User> expected = Optional.of(EntitiesForServicesTest.USER_2);

        assertEquals(expected, actual);
    }
}
