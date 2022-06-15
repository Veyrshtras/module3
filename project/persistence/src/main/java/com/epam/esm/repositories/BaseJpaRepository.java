package com.epam.esm.repositories;

import com.epam.esm.entities.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BaseJpaRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
}
