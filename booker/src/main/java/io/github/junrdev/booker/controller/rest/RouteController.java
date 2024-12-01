package io.github.junrdev.booker.controller.rest;

import io.github.junrdev.booker.domain.dto.RouteDto;
import io.github.junrdev.booker.domain.model.Route;
import io.github.junrdev.booker.domain.service.RouteService;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.image.RescaleOp;
import java.util.List;
import java.util.Set;
import java.util.Timer;

@RequestMapping("/route")
@RestController
public class RouteController {

    private final RouteService routeService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RouteController.class);
    private final Validator validator;

    @Autowired
    public RouteController(RouteService routeService, Validator validator) {
        this.routeService = routeService;
        this.validator = validator;
    }


    @GetMapping("/")
    public ResponseEntity<List<Route>> getAllRoutes(){
        return ResponseEntity.ok(routeService.getRoutes());
    }

    @PostMapping("/new")
    public ResponseEntity<Route> addRouteToSchedule(
            @Valid @RequestBody RouteDto dto
            ){
        return new ResponseEntity<>(routeService.addRoute(dto), HttpStatus.CREATED);
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
