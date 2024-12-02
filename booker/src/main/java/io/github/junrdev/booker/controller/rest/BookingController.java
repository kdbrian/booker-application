package io.github.junrdev.booker.controller.rest;

import io.github.junrdev.booker.domain.dto.BookingDto;
import io.github.junrdev.booker.domain.enumarations.BOOKING_STATUS;
import io.github.junrdev.booker.domain.enumarations.PAYMENT_STATUS;
import io.github.junrdev.booker.domain.model.Booking;
import io.github.junrdev.booker.domain.service.BookingService;
import io.github.junrdev.booker.repo.mongo.VehicleRepository;
import io.github.junrdev.booker.util.error.AppError;
import io.github.junrdev.booker.util.mappers.BookingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RequestMapping("/booking")
@RestController
public class BookingController {

    private final BookingService bookingService;
    private final BookingMapper bookingMapper;
    private final VehicleRepository vehicleRepository;
    private static final Logger LOGGER = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    public BookingController(BookingService bookingService, BookingMapper bookingMapper, VehicleRepository vehicleRepository) {
        this.bookingService = bookingService;
        this.bookingMapper = bookingMapper;
        this.vehicleRepository = vehicleRepository;
    }

    @GetMapping
    public ResponseEntity<List<BookingDto>> getBookings(
            @RequestParam(required = false, value = "user") String user,
            @RequestParam(required = false, value = "vehicle") String vehicle,
            @RequestParam(required = false, value = "payment") String payment,
            @RequestParam(required = false, value = "status") String status
    ) {
        var statuses = Arrays.stream(PAYMENT_STATUS.values()).map(Enum::toString);
        List<Booking> bookings = bookingService.getBookings();

        if (user != null) {

            //hard search almost zero results
            if (payment != null && vehicle != null && status != null) {
                bookings = bookingService.getBookingsByUserPaymentStatusAndBookingStatusAndVehicle(
                        user,
                        PAYMENT_STATUS.valueOf(payment),
                        BOOKING_STATUS.valueOf(status),
                        vehicle
                );
                return ResponseEntity.ok(bookings.stream().map(bookingMapper::toDto).toList());
            }

            //user + payment status
            if (payment != null) {
                bookings = switch (payment) {
                    case "pending", "PENDING" ->
                            bookingService.getBookingsByUserIdAndPaymentStatus(user, PAYMENT_STATUS.PENDING);
                    case "completed", "COMPLETED" ->
                            bookingService.getBookingsByUserIdAndPaymentStatus(user, PAYMENT_STATUS.COMPLETED);
                    case "onsite", "ONSITE" ->
                            bookingService.getBookingsByUserIdAndPaymentStatus(user, PAYMENT_STATUS.ON_SITE);
                    case "processing", "PROCESSING" ->
                            bookingService.getBookingsByUserIdAndPaymentStatus(user, PAYMENT_STATUS.PROCESSING);
                    default -> bookings;
                };

                return ResponseEntity.ok(bookings.stream().map(bookingMapper::toDto).toList());
            }

            //user + status
            if (status != null) {
                bookings = bookingService.getBookingsByUserIdAndBookingStatus(user, BOOKING_STATUS.valueOf(status.toUpperCase()));
                return ResponseEntity.ok(bookings.stream().map(bookingMapper::toDto).toList());
            }

            return ResponseEntity.ok(bookingService.getBookingsByUserId(user).stream().map(bookingMapper::toDto).toList());
        }

        //payment status
        if (payment != null) {
            bookings = switch (payment) {
                case "pending", "PENDING" ->
                        bookingService.getBookingsByUserIdAndPaymentStatus(user, PAYMENT_STATUS.PENDING);
                case "completed", "COMPLETED" ->
                        bookingService.getBookingsByUserIdAndPaymentStatus(user, PAYMENT_STATUS.COMPLETED);
                case "onsite", "ONSITE" ->
                        bookingService.getBookingsByUserIdAndPaymentStatus(user, PAYMENT_STATUS.ON_SITE);
                case "processing", "PROCESSING" ->
                        bookingService.getBookingsByUserIdAndPaymentStatus(user, PAYMENT_STATUS.PROCESSING);
                default -> bookingService.getBookingsByUserId(user);
            };
            return ResponseEntity.ok(bookings.stream().map(bookingMapper::toDto).toList());
        }

        //status
        if (status != null) {
            bookings = bookingService.getBookingsByBookingStatus(BOOKING_STATUS.valueOf(status));
            return ResponseEntity.ok(bookings.stream().map(bookingMapper::toDto).toList());
        }

        //vehicle
        if (vehicle != null) {
            bookings = bookingService.getBookingsByVehicles(vehicle);
            return ResponseEntity.ok(bookings.stream().map(bookingMapper::toDto).toList());
        }

        return ResponseEntity.ok(bookings.stream().map(bookingMapper::toDto).toList());
    }


    @PutMapping("/{id}/update")
    public ResponseEntity<BookingDto> updateBooking(
            @PathVariable("id") String id,
            @RequestParam(value = "cancel", required = false) boolean cancel,
            @RequestBody(required = false) BookingDto bookingDto
    ) {
        if (cancel) {
            return ResponseEntity.ok(bookingMapper.toDto(bookingService.cancelBooking(id)));
        }

        if (bookingDto == null)
            throw new AppError("Missing Body for update request", HttpStatus.BAD_REQUEST);

        return ResponseEntity.ok(bookingMapper.toDto(bookingService.updateBooking(id, bookingDto)));
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<String> deleteAllBookings() {
        return ResponseEntity.ok("Deleted " + bookingService.deleteBookings() + " records.");
    }

}
