package io.github.junrdev.booker.domain.service;

import io.github.junrdev.booker.domain.dto.VehicleDto;
import io.github.junrdev.booker.domain.model.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> getRouteVehicles(String routeID);

    Vehicle addVehicle(VehicleDto dto);

    Vehicle getVehicleByIdentifier(String identifier);

    List<Vehicle> getVehicles();

    Long deleteVehicles();

    Long deleteVehicles();

}
