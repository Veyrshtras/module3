package com.epam.esm.repositories;

import com.epam.esm.entities.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseJpaRepository<User> {
}
