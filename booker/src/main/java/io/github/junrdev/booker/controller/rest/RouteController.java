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


    @GetMapping(value = {"","/"})
    public ResponseEntity<List<Route>> getAllRoutes(
            @RequestParam(value = "schedule", required = false) String scheduleID,
            @RequestParam(value = "company", required = false) String companyID,
            @RequestParam(value = "from", required = false) String from,
            @RequestParam(value = "to", required = false) String to,
            @RequestParam(value = "id", required = false) String id
    ) {

        if (id != null){
            LOGGER.debug("id {}",id);
            return ResponseEntity.ok(List.of(routeService.getRouteById(id)));
        }


        if (scheduleID != null){
            return ResponseEntity.ok(routeService.getScheduleRoute(scheduleID));
        }

        if (companyID != null){
            return ResponseEntity.ok(routeService.getCompanyRoute(companyID));
        }

        if (from!=null && to != null){
            LOGGER.debug("from {} to{}",from,to);
            LOGGER.debug(from,to);
            return ResponseEntity.ok(routeService.findByFromContainingAndToContaining(from, to));
        }

        if (from != null){
            LOGGER.debug("from {}",from);
            return ResponseEntity.ok(routeService.findByFromContaining(from));
        }

        if (to != null){
            LOGGER.debug("to {}",to);
            return ResponseEntity.ok(routeService.findByToContaining(to));
        }


        return ResponseEntity.ok(routeService.getRoutes());
    }

    @PostMapping("/new")
    public ResponseEntity<Route> addRouteToSchedule(
            @Valid @RequestBody RouteDto dto
            ){
        Route route = routeService.addRoute(dto);
        LOGGER.debug("route : {}", route.toString());
        return new ResponseEntity<>(route, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Void> deleteRoute(
            @RequestBody Route route
    ){
        routeService.deleteRoute(route);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllRoute(){
        Long docs = routeService.deleteRoutes();
        return new ResponseEntity<>("Deleted "+docs+" rows.", HttpStatus.OK);
    }
}
