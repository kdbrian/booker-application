package io.github.junrdev.booker.util.mappers;

import org.springframework.stereotype.Service;


/*
* Provides an abstruct underlying for custom mappers implementation
* Type parameter {# T the base entity }
* Type parameter {# K the mapped entity }
* */
public abstract class EntityToDtoMapper<T, K>{
    public abstract T fromDto(K k);
    public abstract K toDto(T t);
}
