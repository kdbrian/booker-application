package io.github.junrdev.booker.util.mappers;

import org.springframework.stereotype.Service;


/*
* Provides an abstruct underlying for custom mappers implementation
* Type parameter {# T the base entity }
* Type parameter {# K the mapped entity }
* */
public abstract class EntityToDtoMapper<T, K>{

    abstract T fromDto(K k);
    abstract K toDto(T t);
}
