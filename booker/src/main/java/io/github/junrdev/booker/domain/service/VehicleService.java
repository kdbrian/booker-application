package io.github.junrdev.booker.domain.service;

import io.github.junrdev.booker.domain.dto.VehicleDTO;
import io.github.junrdev.booker.domain.model.Route;
import io.github.junrdev.booker.domain.model.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleService {

    List<Vehicle> getRouteVehicles(String routeID);

    Vehicle addVehicle(VehicleDTO dto);

}
