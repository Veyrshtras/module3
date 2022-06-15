package com.epam.esm.repositories;

import com.epam.esm.entities.Tag;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends BaseJpaRepository<Tag> {
    Optional<Tag> findByName(String name);
}
