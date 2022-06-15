package com.epam.esm.service;

import com.epam.esm.entities.User;
import com.epam.esm.services.UserService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private final UserService service;

    public UserServiceTest(UserService service) {
        this.service = service;
    }

    private static final User USER_1 = new User(1, "name1");
    private static final User USER_2 = new User(2, "name2");

    @Test
    public void getAll(){
        List<User> actual = (List<User>) service.getAll(Pageable.ofSize(5));
        List<User> expected = Arrays.asList(USER_1, USER_2);

        assertEquals(expected, actual);
    }


    @Test
    public void getById(){
        Optional<User> actual = Optional.of(service.getById(USER_2.getId()));
        Optional<User> expected = Optional.of(USER_2);

        assertEquals(expected, actual);
    }
}
