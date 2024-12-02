package io.github.junrdev.booker.domain.service;

import io.github.junrdev.booker.domain.dto.BookingDto;
import io.github.junrdev.booker.domain.dto.VehicleDto;
import io.github.junrdev.booker.domain.model.Booking;
import io.github.junrdev.booker.domain.model.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> getRouteVehicles(String routeID);

    Vehicle addVehicle(VehicleDto dto);

    Vehicle getVehicleByIdentifier(String identifier);

    List<Vehicle> getVehicles();

    List<Vehicle> getVehiclesByPrice(double price);

    List<Vehicle> getVehiclesBySeatCount(int count);

    List<Vehicle> getVehiclesSeatsInRange(int[] range);

    Long deleteVehicles();

    Booking occupy(BookingDto dto);

}
