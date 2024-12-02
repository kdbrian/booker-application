package io.github.junrdev.booker.controller.rest;

import io.github.junrdev.booker.domain.dto.BookingDto;
import io.github.junrdev.booker.domain.dto.VehicleDto;
import io.github.junrdev.booker.domain.model.Vehicle;
import io.github.junrdev.booker.domain.service.VehicleService;
import io.github.junrdev.booker.util.mappers.BookingMapper;
import io.github.junrdev.booker.util.mappers.VehicleMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/vehicle")
@RestController
public class VehicleController {

    private static final Logger LOGGER = LoggerFactory.getLogger(VehicleController.class);
    private final VehicleService vehicleService;
    private final VehicleMapper vehicleMapper;
    private final BookingMapper bookingMapper;

    @Autowired
    public VehicleController(VehicleService vehicleService, VehicleMapper vehicleMapper, BookingMapper bookingMapper) {
        this.vehicleService = vehicleService;
        this.vehicleMapper = vehicleMapper;
        this.bookingMapper = bookingMapper;
    }


    @GetMapping(value = {"", "/"})
    public ResponseEntity<List<VehicleDto>> getAllVehicles() {

        List<Vehicle> vehicles = vehicleService.getVehicles();

        LOGGER.debug("got {}, {}", vehicles.size(), vehicles);
        return ResponseEntity.ok(
                vehicles
                        .stream()
                        .map(vehicleMapper::toDto)
                        .toList()
        );
    }

    @GetMapping(value = {"/{identifier}/", "/{identifier}"})
    public ResponseEntity<VehicleDto> getVehicleByIdentifier(
            @PathVariable(name = "identifier") String identifier
    ) {
        return ResponseEntity.ok(vehicleMapper.toDto(vehicleService.getVehicleByIdentifier(identifier)));
    }

    @PostMapping("/new")
    public ResponseEntity<VehicleDto> addVehicleToRoute(
            @Valid @RequestBody VehicleDto dto
    ) {
        return ResponseEntity.ok(vehicleMapper.toDto(vehicleService.addVehicle(dto)));
    }

    @PutMapping("/{identifier}/occupy/")
    public ResponseEntity<BookingDto> occupyVehicleSeat(
            @PathVariable(name = "identifier", required = false) String identifier,
            @Valid @RequestBody BookingDto bookingDto
    ) {

        if (identifier != null){
            Vehicle vehicle = vehicleService.getVehicleByIdentifier(identifier);
            bookingDto.setVehicleId(vehicle.getId());
        }

        return ResponseEntity.ok(bookingMapper.toDto(vehicleService.occupy(bookingDto)));
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteVehicles() {
        return ResponseEntity.ok(String.format("Deleted %d records.", vehicleService.deleteVehicles()));

    }

}
