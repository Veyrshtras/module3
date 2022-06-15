package com.epam.esm.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Setter
@Getter
@Configuration
@EntityScan(basePackages = "com.epam.esm")
public class DBConfig {

    public static EntityManager entityManager;
}
