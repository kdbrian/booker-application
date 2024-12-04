package io.github.junrdev.booker.repo.mongo.impl;

import io.github.junrdev.booker.domain.dto.RouteDto;
import io.github.junrdev.booker.domain.model.Company;
import io.github.junrdev.booker.domain.model.Route;
import io.github.junrdev.booker.domain.model.Schedule;
import io.github.junrdev.booker.domain.service.RouteService;
import io.github.junrdev.booker.repo.mongo.CompanyRepository;
import io.github.junrdev.booker.repo.mongo.RouteRepository;
import io.github.junrdev.booker.repo.mongo.ScheduleRepository;
import io.github.junrdev.booker.util.error.AppError;
import io.github.junrdev.booker.util.mappers.RouteMappers;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RouteServiceImpl implements RouteService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RouteServiceImpl.class);

    private final RouteRepository routeRepository;
    private final ScheduleRepository scheduleRepository;
    private final CompanyRepository companyRepository;
    private final RouteMappers routeMappers;


    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, ScheduleRepository scheduleRepository, CompanyRepository companyRepository, RouteMappers routeMappers) {
        this.routeRepository = routeRepository;
        this.scheduleRepository = scheduleRepository;
        this.companyRepository = companyRepository;
        this.routeMappers = routeMappers;
    }

    @Override
    public Route addRoute(RouteDto dto) {
        checkId(dto.getScheduleID());
        var schedule = scheduleRepository.findById(dto.getScheduleID())
                .orElseThrow(() -> new AppError("Missing route with id : %s", HttpStatus.NOT_FOUND));

        Route route = Route.builder()
                .schedule(schedule)
                .from(dto.getFrom())
                .to(dto.getTo())
                .build();
        LOGGER.debug("saving {}", route.toString());
        return routeRepository.save(
                route
        );
    }

    @Override
    public List<Route> addRoutes(List<RouteDto> dto) {
        return routeRepository.saveAll(dto.stream().map(routeMappers::fromDto).toList());
    }

    @Override
    public List<Route> getRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public Route getRouteById(String routeId) {
        checkId(routeId);
        return routeRepository.findById(routeId)
                .orElseThrow(() -> new AppError("Missing route with id : " + routeId, HttpStatus.NOT_FOUND));

    }

    @Override
    public List<Route> getScheduleRoute(String scheduleID) {

        checkId(scheduleID);

        Optional<Schedule> _schedule = scheduleRepository.findById(scheduleID);

        if (_schedule.isEmpty())
            throw new AppError(System.out.printf("Missing schedule with Id %s", scheduleID).toString(), HttpStatus.NOT_FOUND);

        return routeRepository.findBySchedule(_schedule.get());
    }

    @Override
    public List<Route> getCompanyRoute(String companyID) {
//        LOGGER.debug("invoked with {}", companyID);
        checkId(companyID);

        Optional<Company> _company = companyRepository.findById(companyID);

        if (_company.isEmpty())
            throw new AppError("Company not found.", HttpStatus.NOT_FOUND);
        return routeRepository.findAll()
                .stream()
                .filter(
                        r -> r.getSchedule().getCompany().getId().equals(companyID)
                ).toList();
    }

    @Override
    public void deleteRoute(Route route) {
        try {
            routeRepository.delete(route);
        } catch (RuntimeException e) {
            LOGGER.debug(e.getMessage());
            throw new AppError(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void deleteRouteById(String routeId) {
        checkId(routeId);
        routeRepository.deleteById(routeId);
    }

    @Override
    public Long deleteRoutes() {
        Long count = routeRepository.count();
        routeRepository.deleteAll();
        return count;
    }

    @Override
    public List<Route> findByFrom(String from) {
        return routeRepository.findByFrom(from);
    }

    @Override
    public List<Route> findByFromContaining(String from) {
        return routeRepository.findByFromContaining(from);
    }

    @Override
    public List<Route> findByTo(String to) {
        return routeRepository.findByTo(to);
    }

    @Override
    public List<Route> findByToContaining(String to) {
        return routeRepository.findByToContaining(to);
    }

    @Override
    public List<Route> findByFromContainingAndToContaining(String from, String to) {
        return routeRepository.findByFromContainingAndToContaining(from, to);
    }

}
