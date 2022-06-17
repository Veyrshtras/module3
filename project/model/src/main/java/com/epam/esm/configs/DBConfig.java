package com.epam.esm.configs;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Setter
@Getter
@Configuration
@EntityScan(basePackages = "com.epam.esm")
public class DBConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
