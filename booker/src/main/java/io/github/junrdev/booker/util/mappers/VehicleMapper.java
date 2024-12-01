package io.github.junrdev.booker.util.mappers;

import io.github.junrdev.booker.domain.dto.VehicleDto;
import io.github.junrdev.booker.domain.model.Vehicle;

public class VehicleMapper extends EntityToDtoMapper<Vehicle, VehicleDto> {
    @Override
    public Vehicle fromDto(VehicleDto vehicleDto) {
        return Vehicle.builder()
                .price(vehicleDto.price())
                .features(vehicleDto.features())
                .build();
    }

    @Override
    public VehicleDto toDto(Vehicle vehicle) {
        return VehicleDto.builder()
                .id(vehicle.getId())
                .price(vehicle.getPrice())
                .routeId(vehicle.getRoute().getId())
                .features(vehicle.getFeatures())
                .build();
    }
}
