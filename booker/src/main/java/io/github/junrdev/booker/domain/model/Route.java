package io.github.junrdev.booker.domain.model;

import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document
@Builder
public class Route {

    @Id
    private String id;

    private String from;

    private String to;

    @DBRef
    private Schedule schedule;

    private Set<Vehicle> vehicles;

}
