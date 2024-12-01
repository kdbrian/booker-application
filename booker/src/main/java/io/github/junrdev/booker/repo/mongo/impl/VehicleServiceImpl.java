package io.github.junrdev.booker.repo.mongo.impl;

import io.github.junrdev.booker.domain.dto.VehicleDto;
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
import java.util.Set;

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
    public Vehicle addVehicle(VehicleDto dto) {

        if (vehicleRepository.findByIdentifier(dto.identifier()).isPresent())
            throw new AppError(String.format("Found vehicle with identifier %s.", dto.identifier()), HttpStatus.BAD_REQUEST);


        Route route = routeRepository
                .findById(dto.routeId())
                .orElseThrow(() -> new AppError(String.format("Missing route with Id : %s", dto.routeId()), HttpStatus.NOT_FOUND));

        LOGGER.debug("Price {}", dto.price());
        Vehicle vehicle = Vehicle.builder()
                .identifier(dto.identifier())
                .price(dto.price())
                .features(dto.features())
                .route(route)
                .build();

        LOGGER.debug("Price {}", dto.price());
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getVehicleByIdentifier(String identifier) {
        return vehicleRepository.findByIdentifier(identifier)
                .orElseThrow(()-> new AppError("Missing vehicle "+identifier, HttpStatus.NOT_FOUND));
    }

    @Override
    public List<Vehicle> getVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    public Long deleteVehicles() {
        long count = vehicleRepository.count();
        vehicleRepository.deleteAll();
        return count;
    }
}
