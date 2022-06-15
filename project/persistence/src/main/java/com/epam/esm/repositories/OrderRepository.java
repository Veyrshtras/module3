package com.epam.esm.repositories;

import com.epam.esm.entities.Order;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseJpaRepository<Order> {
}
