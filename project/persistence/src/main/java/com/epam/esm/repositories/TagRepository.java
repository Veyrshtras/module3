package com.epam.esm.repositories;

import com.epam.esm.entities.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TagRepository extends BaseJpaRepository<Tag> {
    Optional<Tag> findByName(String name);
    @Query(value = "SELECT * FROM tag WHERE tag.id IN " +
            "(SELECT tags_id FROM" +
            "(SELECT certificate_id from orders WHERE user_id = " +
            "(SELECT user_id FROM orders GROUP BY user_id ORDER BY SUM(cost) DESC LIMIT 1 )) AS tableA  " +
            " INNER JOIN gift_certificate_tags ON gift_certificate_tags.gift_certificate_id = tableA.certificate_id " +
            " GROUP BY tags_id " +
            " order by count(tags_id) desc limit 1)", nativeQuery = true)
    Tag getMostPopularTagOfUserWithHighestCostOfAllOrders();
}
