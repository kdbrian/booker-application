package io.github.junrdev.booker.domain.service;

import io.github.junrdev.booker.domain.dto.RouteDto;
import io.github.junrdev.booker.domain.model.Route;
import io.github.junrdev.booker.util.error.AppError;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RouteService {

    Route addRoute(RouteDto dto);
    List<Route> addRoutes(List<RouteDto> dto);

    List<Route> getRoutes();
    Route getRouteById(String id);

    List<Route> getScheduleRoute(String scheduleID);

    List<Route> getCompanyRoute(String companyID);

    void deleteRoute(Route route);

    void deleteRouteById(String routeId);

    Long deleteRoutes();


    List<Route> findByFrom(String from);

    List<Route> findByFromContaining(String from);

    List<Route> findByTo(String to);

    List<Route> findByToContaining(String to);

    List<Route> findByFromContainingAndToContaining(String from, String to);

    default void checkId(String id){
        if (!ObjectId.isValid(id))
            throw new AppError("Invalid route id "+id, HttpStatus.BAD_REQUEST);
    }
}
