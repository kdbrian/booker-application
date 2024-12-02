package io.github.junrdev.booker.repo.mongo.impl;

import io.github.junrdev.booker.domain.dto.BookingDto;
import io.github.junrdev.booker.domain.dto.VehicleDto;
import io.github.junrdev.booker.domain.enumarations.PAYMENT_STATUS;
import io.github.junrdev.booker.domain.model.Booking;
import io.github.junrdev.booker.domain.model.Route;
import io.github.junrdev.booker.domain.model.Vehicle;
import io.github.junrdev.booker.domain.service.VehicleService;
import io.github.junrdev.booker.repo.mongo.BookingRepository;
import io.github.junrdev.booker.repo.mongo.RouteRepository;
import io.github.junrdev.booker.repo.mongo.VehicleRepository;
import io.github.junrdev.booker.util.error.AppError;
import io.github.junrdev.booker.util.mappers.BookingMapper;
import io.github.junrdev.booker.util.mappers.VehicleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class VehicleServiceImpl implements VehicleService {


    private final static Logger LOGGER = LoggerFactory.getLogger(VehicleServiceImpl.class);
    private final VehicleRepository vehicleRepository;
    private final RouteRepository routeRepository;
    private final BookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final VehicleMapper vehicleMapper;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, RouteRepository routeRepository, BookingRepository bookingRepository, BookingMapper bookingMapper, VehicleMapper vehicleMapper) {
        this.vehicleRepository = vehicleRepository;
        this.routeRepository = routeRepository;
        this.bookingRepository = bookingRepository;
        this.bookingMapper = bookingMapper;
        this.vehicleMapper = vehicleMapper;
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
        Vehicle vehicle = vehicleMapper.fromDto(dto);
        vehicle.setRoute(route);

        LOGGER.debug("Price {}", dto.price());
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getVehicleByIdentifier(String identifier) {
        return vehicleRepository.findByIdentifier(identifier)
                .orElseThrow(() -> new AppError("Missing vehicle " + identifier, HttpStatus.NOT_FOUND));
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

    @Override
    public Booking occupy(BookingDto dto) {

        Vehicle vehicle = vehicleRepository.findById(dto.getVehicleId())
                .orElseThrow(() -> new AppError("Missing vehicle " + dto.getVehicleId(), HttpStatus.NOT_FOUND));

        if (
                Objects.equals(vehicle.getSeatsOccuppied(), vehicle.getSeatCount())
        )
            throw new AppError("Error booking Vehicle : " + vehicle.getIdentifier() + " full.", HttpStatus.BAD_REQUEST);

        Booking build = bookingMapper.fromDto(dto);
        build.setVehicle(vehicle);

        Booking booking = bookingRepository.save(
                build
        );

        vehicle.setSeatsOccuppied(vehicle.getSeatsOccuppied() + 1);
        vehicleRepository.save(vehicle);
//        return String.format("Occupied seat %s, tracking %s", saved.getId(), booking.userID());
        return booking;
    }

}
