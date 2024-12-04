package io.github.junrdev.booker.util.mappers;

import io.github.junrdev.booker.domain.dto.VehicleDto;
import io.github.junrdev.booker.domain.model.Vehicle;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper extends EntityToDtoMapper<Vehicle, VehicleDto> {
    @Override
    public Vehicle fromDto(VehicleDto vehicleDto) {
        return Vehicle.builder()
                .seatsOccuppied(vehicleDto.seatsOccupied()!=null?vehicleDto.seatsOccupied():0L)
                .seatCount(vehicleDto.seatCount())
                .identifier(vehicleDto.identifier())
                .price(vehicleDto.price())
                .features(vehicleDto.features())
                .leavingTime(vehicleDto.leavingTime())
                .estimatedTimeOfTravel(vehicleDto.estimatedTimeOfTravel())
                .build();
    }

    @Override
    public VehicleDto toDto(Vehicle vehicle) {
        return VehicleDto.builder()
                .id(vehicle.getId())
                .leavingTime(vehicle.getLeavingTime())
                .estimatedTimeOfTravel(vehicle.getEstimatedTimeOfTravel())
                .seatCount(vehicle.getSeatCount())
                .seatsOccupied(vehicle.getSeatsOccuppied())
                .identifier(vehicle.getIdentifier())
                .price(vehicle.getPrice())
                .routeId(vehicle.getRoute().getId())
                .features(vehicle.getFeatures())
                .build();
    }
}
