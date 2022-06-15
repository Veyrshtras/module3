package com.epam.esm.hateoas;

import org.springframework.hateoas.RepresentationModel;

public interface HateoasAdder<T extends RepresentationModel<T>> {

    void addLink(T entity);
}
