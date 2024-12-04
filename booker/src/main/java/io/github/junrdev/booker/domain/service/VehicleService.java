package io.github.junrdev.booker.domain.service;

import io.github.junrdev.booker.domain.dto.BookingDto;
import io.github.junrdev.booker.domain.dto.VehicleDto;
import io.github.junrdev.booker.domain.model.Booking;
import io.github.junrdev.booker.domain.model.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> getVehiclesByRouteId(String routeID);
    List<Vehicle> getVehiclesByRouteFrom(String from);

    List<Vehicle> getRouteVehiclesByRouteTo(String to);
    List<Vehicle> getRouteVehiclesByRouteFromAndTo(String from, String to);

    Vehicle addVehicle(VehicleDto dto);
    List<Vehicle> addVehicles(List<VehicleDto> dto);

    Vehicle getVehicleByIdentifier(String identifier);

    List<Vehicle> getVehicles();

    List<Vehicle> getVehiclesByPrice(double price);

    List<Vehicle> getVehiclesBySeatCount(int count);

    List<Vehicle> getVehiclesSeatsInRange(int[] range);

    Long deleteVehicles();
    void deleteVehicleById(String vehicleId);

    Booking occupy(BookingDto dto);

}
