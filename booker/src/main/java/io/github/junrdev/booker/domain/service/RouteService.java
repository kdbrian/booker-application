package io.github.junrdev.booker.domain.service;

import io.github.junrdev.booker.domain.dto.RouteDto;
import io.github.junrdev.booker.domain.model.Route;
import org.springframework.stereotype.Service;

import java.util.List;

public interface    RouteService {

    Route addRoute(RouteDto dto);

    List<Route> getRoutes();

    List<Route> getScheduleRoute(String scheduleID);

    void deleteRoute(Route route);

}
