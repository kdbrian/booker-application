package io.github.junrdev.booker.controller.rest;

import io.github.junrdev.booker.domain.dto.RouteDto;
import io.github.junrdev.booker.domain.model.Route;
import io.github.junrdev.booker.domain.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.RescaleOp;
import java.util.List;

@RequestMapping("/route")
@RestController
public class RouteController {

    private final RouteService routeService;

    @Autowired
    public RouteController(RouteService routeService) {
        this.routeService = routeService;
    }


    @GetMapping("/")
    public ResponseEntity<List<Route>> getAllRoutes(){
        return ResponseEntity.ok(routeService.getRoutes());
    }

    @GetMapping("/new")
    public ResponseEntity<Route> addRouteToSchedule(
            @RequestBody RouteDto dto
            ){
        return ResponseEntity.ok(routeService.addRoute(dto));
    }

    @GetMapping("/route/schedule/{id}/")
    public ResponseEntity<List<Route>> getScheduleRoutes(
            @PathVariable(value = "id") String scheduleID
    ){
        return ResponseEntity.ok(routeService.getScheduleRoute(scheduleID));
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteRoute(
            @RequestBody Route route
    ){
        routeService.deleteRoute(route);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
