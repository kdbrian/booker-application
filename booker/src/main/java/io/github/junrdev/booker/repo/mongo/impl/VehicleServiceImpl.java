package io.github.junrdev.booker.repo.mongo.impl;

import io.github.junrdev.booker.domain.dto.VehicleDTO;
import io.github.junrdev.booker.domain.model.Route;
import io.github.junrdev.booker.domain.model.Vehicle;
import io.github.junrdev.booker.domain.service.VehicleService;
import io.github.junrdev.booker.repo.mongo.RouteRepository;
import io.github.junrdev.booker.repo.mongo.VehicleRepository;
import io.github.junrdev.booker.util.error.AppError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {


    private final static Logger LOGGER = LoggerFactory.getLogger(VehicleServiceImpl.class);
    private final VehicleRepository vehicleRepository;
    private final RouteRepository routeRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, RouteRepository routeRepository) {
        this.vehicleRepository = vehicleRepository;
        this.routeRepository = routeRepository;
    }

    @Override
    public List<Vehicle> getRouteVehicles(String routeID) {

        Optional<Route> _route = routeRepository.findById(routeID);

        if (_route.isEmpty())
            throw new AppError(String.format("Missing route with Id : %s", routeID), HttpStatus.NOT_FOUND);

        return vehicleRepository.findByRoute(_route.get());
    }

    @Override
    public Vehicle addVehicle(VehicleDTO dto) {

        Optional<Route> _route = routeRepository.findById(dto.getRouteID());

        if (_route.isEmpty())
            throw new AppError(String.format("Missing route with Id : %s", dto.getRouteID()), HttpStatus.NOT_FOUND);

        return vehicleRepository.save(
                Vehicle.builder()
                        .route(_route.get())
                        .price(dto.getPrice())
                        .features(dto.getFeatures())
                        .build()
        );
    }

}
