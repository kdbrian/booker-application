package io.github.junrdev.booker.domain.service;

import io.github.junrdev.booker.domain.dto.BookingDto;
import io.github.junrdev.booker.domain.enumarations.PAYMENT_STATUS;
import io.github.junrdev.booker.domain.model.Booking;
import io.github.junrdev.booker.domain.model.Vehicle;

import java.util.List;

public interface BookingService {

    List<Booking> getBookings();
    List<Booking> getBookingsByPaymentStatus(PAYMENT_STATUS paymentStatus);
    List<Booking> getBookingsByUserId(String userID);
    List<Booking> getBookingsByVehicles(Vehicle vehicle);
    Booking addBooking(BookingDto dto);
    Booking cancelBooking(String bookingID);
    void deleteBooking(String bookingID);

}
