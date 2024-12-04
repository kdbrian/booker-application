package io.github.junrdev.booker.controller.graphql;

import io.github.junrdev.booker.domain.dto.BookingDto;
import io.github.junrdev.booker.domain.dto.VehicleDto;
import io.github.junrdev.booker.domain.model.Booking;
import io.github.junrdev.booker.domain.model.Vehicle;
import io.github.junrdev.booker.domain.service.VehicleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class VehicleGraphController {

    private final VehicleService vehicleService;

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleGraphController.class);

    @Autowired
    public VehicleGraphController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @QueryMapping
    public List<Vehicle> getVehicles() {
        return vehicleService.getVehicles();
    }


    @QueryMapping
    public List<Vehicle> getVehicleByPrice(@Argument Float price) {
        return vehicleService.getVehiclesByPrice(price);
    }


    @QueryMapping
    public List<Vehicle> getVehicleBySeatCount(@Argument Integer seatCount) {
        return vehicleService.getVehiclesBySeatCount(seatCount);
    }


    @QueryMapping
    public List<Vehicle> getVehicleBySeatRange(@Argument int[] seatRange) {
        return vehicleService.getVehiclesSeatsInRange(seatRange);
    }


    @QueryMapping
    public Vehicle getVehicleByIdentifier(@Argument String identifier) {
        return vehicleService.getVehicleByIdentifier(identifier);
    }

    @QueryMapping
    public List<Vehicle> getVehicleByRoute(@Argument String routeID) {
        return vehicleService.getVehiclesByRouteId(routeID);
    }

    @QueryMapping
    public List<Vehicle> getVehicleByRouteFrom(@Argument String from) {
        return vehicleService.getVehiclesByRouteFrom(from);
    }

    @QueryMapping
    public List<Vehicle> getVehicleByRouteTo(@Argument String to) {
        return vehicleService.getRouteVehiclesByRouteTo(to);
    }

    @QueryMapping
    public List<Vehicle> getVehicleByRouteFromAndTo(@Argument String from, @Argument String to) {
        return vehicleService.getRouteVehiclesByRouteFromAndTo(from, to);
    }

    @MutationMapping
    public Vehicle addVehicle(@Argument VehicleDto vehicleDto) {
        return vehicleService.addVehicle(vehicleDto);
    }

    @MutationMapping
    public List<Vehicle> addVehicles(@Argument List<VehicleDto> vehicleDtos) {
        return vehicleService.addVehicles(vehicleDtos);
    }

    @MutationMapping
    public Booking bookVehicle(@Argument BookingDto bookingDto) {
        return vehicleService.occupy(bookingDto);
    }


    @MutationMapping
    public boolean deleteVehicle(@Argument String vehicleID) {
        vehicleService.deleteVehicleById(vehicleID);
        return true;
    }

    @MutationMapping
    public boolean deleteVehicles() {
        return vehicleService.deleteVehicles() != -1;
    }


}

