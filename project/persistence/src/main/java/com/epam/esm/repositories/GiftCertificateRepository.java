package com.epam.esm.repositories;

import com.epam.esm.entities.GiftCertificate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GiftCertificateRepository extends BaseJpaRepository<GiftCertificate> {
    Optional<GiftCertificate> findByName(String name);
}
