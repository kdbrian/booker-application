package io.github.junrdev.booker.domain.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Document
@Data
@Builder
public class Schedule {

    @Id private String id;

    @DBRef
    private Company company;

    private Long startTime;

    private Long endTime;

    @DBRef
    private Set<Route> routes;

    public void addRoute(Route route){
        routes.add(route);
    }


    public void addRoutes(List<Route> newRoutes){
        routes.addAll(newRoutes);
    }
}
