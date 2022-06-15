package com.epam.esm.services;

import com.epam.esm.dtos.UserDto;
import com.epam.esm.entities.User;
import org.hibernate.IdentifierLoadAccess;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<User> getAll(Pageable pageable);

    User getById(Long id);

    void fillEntity();

}
