package io.github.junrdev.booker.domain.service;

import io.github.junrdev.booker.domain.dto.RouteDto;
import io.github.junrdev.booker.domain.model.Route;
import org.springframework.stereotype.Service;

import java.util.List;

public interface RouteService {

    Route addRoute(RouteDto dto);

    List<Route> getRoutes();
    Route getRouteById(String id);

    List<Route> getScheduleRoute(String scheduleID);

    List<Route> getCompanyRoute(String companyID);

    void deleteRoute(Route route);

    Long deleteRoutes();


    List<Route> findByFrom(String from);

    List<Route> findByFromContaining(String from);

    List<Route> findByTo(String to);

    List<Route> findByToContaining(String to);

    List<Route> findByFromContainingAndToContaining(String from, String to);

}
