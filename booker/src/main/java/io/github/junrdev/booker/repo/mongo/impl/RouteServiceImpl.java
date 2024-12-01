package io.github.junrdev.booker.repo.mongo.impl;

import io.github.junrdev.booker.domain.dto.RouteDto;
import io.github.junrdev.booker.domain.model.Route;
import io.github.junrdev.booker.domain.model.Schedule;
import io.github.junrdev.booker.domain.service.RouteService;
import io.github.junrdev.booker.repo.mongo.RouteRepository;
import io.github.junrdev.booker.repo.mongo.ScheduleRepository;
import io.github.junrdev.booker.util.error.AppError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class RouteServiceImpl implements RouteService {

    private static final Logger LOGGER= LoggerFactory.getLogger(RouteServiceImpl.class);

    private final RouteRepository routeRepository;
    private final ScheduleRepository scheduleRepository;


    @Autowired
    public RouteServiceImpl(RouteRepository routeRepository, ScheduleRepository scheduleRepository) {
        this.routeRepository = routeRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Route addRoute(RouteDto dto) {

        var schedule = scheduleRepository.findById(dto.getScheduleID())
                .orElseThrow(()-> new AppError("Missing route with id : %s", HttpStatus.NOT_FOUND));

        return routeRepository.save(
                Route.builder()
                        .schedule(schedule)
                        .from(dto.getFrom())
                        .to(dto.getTo())
                        .vehicles(dto.getVehicles())
                        .build()
        );
    }

    @Override
    public List<Route> getRoutes() {
        return routeRepository.findAll();
    }

    @Override
    public List<Route> getScheduleRoute(String scheduleID) {

        Optional<Schedule> _schedule = scheduleRepository.findById(scheduleID);

        if (_schedule.isEmpty())
            throw new AppError(System.out.printf("Missing schedule with Id %s", scheduleID).toString(), HttpStatus.NOT_FOUND);

        return routeRepository.findBySchedule(_schedule.get());
    }

    @Override
    public void deleteRoute(Route route) {
        try{
            routeRepository.delete(route);
        } catch (RuntimeException e) {
            LOGGER.debug(e.getMessage());
            throw new AppError(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

}
