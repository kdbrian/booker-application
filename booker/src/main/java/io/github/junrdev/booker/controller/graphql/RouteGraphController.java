package io.github.junrdev.booker.controller.graphql;

import io.github.junrdev.booker.domain.dto.BookingDto;
import io.github.junrdev.booker.domain.dto.RouteDto;
import io.github.junrdev.booker.domain.model.Route;
import io.github.junrdev.booker.domain.service.RouteService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class RouteGraphController {

    private final RouteService routeService;


    public RouteGraphController(RouteService routeService) {
        this.routeService = routeService;
    }

    @QueryMapping
    public List<Route> getRoutes() {
        return routeService.getRoutes();
    }

    @QueryMapping
    public Route getRouteById(@Argument String id) {
        return routeService.getRouteById(id);
    }

    @QueryMapping
    public List<Route> getRouteByScheduleId(@Argument String scheduleId) {
        return routeService.getScheduleRoute(scheduleId);
    }

    @QueryMapping
    public List<Route> getRouteByCompanyId(@Argument String companyId) {
        return routeService.getCompanyRoute(companyId);
    }

    @QueryMapping
    public List<Route> getRouteByFrom(@Argument String from) {
        return routeService.findByFrom(from);
    }

    @QueryMapping
    public List<Route> getRouteByTo(@Argument String to) {
        return routeService.findByTo(to);
    }

    @QueryMapping
    public List<Route> getRouteByFromAndTo(@Argument String from, @Argument String to) {
        return routeService.findByFromContainingAndToContaining(from, to);
    }


    @MutationMapping
    public Route addRoute(@Argument RouteDto dto) {
        return routeService.addRoute(dto);
    }

    @MutationMapping
    public List<Route> addRoutes(@Argument List<RouteDto> dto) {
        return routeService.addRoutes(dto);
    }


    @MutationMapping
    public Boolean deleteAllRoutes() {
        routeService.deleteRoutes();
        return true;
    }

    @MutationMapping
    public Boolean deleteRouteById(String id) {
        routeService.deleteRouteById(id);
        return true;
    }

}
