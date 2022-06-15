package com.epam.esm.services;

import com.epam.esm.dtos.TagDto;
import com.epam.esm.entities.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TagService {

    void fillEntity();

    Page<Tag> getAll(Pageable pageable);

    Tag insert(TagDto dto);

    boolean delete(Long id);

    Tag getById(Long id);

    TagDto getMostPopularTagOfUserWithHighestCostOfAllOrders();
}
