package com.epam.esm.repositories;

import com.epam.esm.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseJpaRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
}
