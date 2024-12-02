package io.github.junrdev.booker.controller.rest;

import io.github.junrdev.booker.domain.dto.BookingDto;
import io.github.junrdev.booker.domain.dto.VehicleDto;
import io.github.junrdev.booker.domain.model.Vehicle;
import io.github.junrdev.booker.domain.service.VehicleService;
import io.github.junrdev.booker.util.error.AppError;
import io.github.junrdev.booker.util.mappers.BookingMapper;
import io.github.junrdev.booker.util.mappers.VehicleMapper;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<VehicleDto>> getAllVehicles(
            @RequestParam(value = "routeId", required = false) String routeId,
            @RequestParam(value = "routeFrom", required = false) String routeFrom,
            @RequestParam(value = "seats", required = false) Integer seatcount,
            @RequestParam(value = "price", required = false) Double price,

            //TODO: update these to reflect repo
            @RequestParam(value = "seat-range", required = false) int[] seatRange,
            @RequestParam(value = "price-range", required = false) double[] pricesRange
    ) {

        List<Vehicle> vehicles = vehicleService.getVehicles();

        if (routeId != null)
            vehicles = vehicleService.getRouteVehiclesByRouteId(routeId);

        if (seatcount != null) {
            vehicles = vehicleService.getVehiclesBySeatCount(seatcount);
            return ResponseEntity.ok(vehicles.stream().map(vehicleMapper::toDto).toList());
        }

        if (seatRange != null) {
            if (seatRange.length == 2) {

                if (seatRange[1] < seatRange[0])
                    throw new AppError("Range end value should be great than start.", HttpStatus.BAD_REQUEST);

                //has both ranges
                List<Vehicle> vehiclesInseatRange = vehicleService.getVehiclesSeatsInRange(seatRange);
                LOGGER.debug("matching for range {} , {} -> {}", seatRange, vehiclesInseatRange.size(), vehiclesInseatRange);
                return ResponseEntity.ok(vehiclesInseatRange.stream().map(vehicleMapper::toDto).toList());

            } else {
                //has one range
            }
            LOGGER.debug("seatRange {} {}", seatRange, seatRange.length);

        }

        if (pricesRange != null) {
            if (pricesRange.length == 2) {

                if (pricesRange[1] < pricesRange[0])
                    throw new AppError("Range end value should be great than start.", HttpStatus.BAD_REQUEST);

            } else {
                //has one range
            }
            LOGGER.debug("seatRange {} {}", pricesRange, pricesRange.length);

        }

        if (price != null) {
            vehicles = vehicleService.getVehiclesByPrice(price);
            return ResponseEntity.ok(vehicles.stream().map(vehicleMapper::toDto).toList());
        }

        if (routeFrom != null) {
            vehicles = vehicleService.getRouteVehiclesByRouteFrom(routeFrom);
            return ResponseEntity.ok(vehicles.stream().map(vehicleMapper::toDto).toList());
        }


        return ResponseEntity.ok(vehicles.stream().map(vehicleMapper::toDto).toList());
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

        if (identifier != null) {
            Vehicle vehicle = vehicleService.getVehicleByIdentifier(identifier);
            bookingDto.setVehicleId(vehicle.getId());
        }

        return ResponseEntity.ok(bookingMapper.toDto(vehicleService.occupy(bookingDto)));
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteVehicles() {
        return ResponseEntity.ok(String.format("Deleted %d records.", vehicleService.deleteVehicles()));

    }

    @DeleteMapping("/{id}/delete/")
    public ResponseEntity<String> deleteVehicle(
            @PathVariable String vehicleId
    ) {
        vehicleService.deleteVehicleById(vehicleId );
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
