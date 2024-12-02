package io.github.junrdev.booker.domain.service;

import io.github.junrdev.booker.domain.dto.BookingDto;
import io.github.junrdev.booker.domain.enumarations.BOOKING_STATUS;
import io.github.junrdev.booker.domain.enumarations.PAYMENT_STATUS;
import io.github.junrdev.booker.domain.model.Booking;
import io.github.junrdev.booker.domain.model.Vehicle;

import java.util.List;

public interface BookingService {

    List<Booking> getBookings();

    List<Booking> getBookingsByPaymentStatus(PAYMENT_STATUS paymentStatus);

    List<Booking> getBookingsByUserPaymentStatusAndBookingStatusAndVehicle(String user, PAYMENT_STATUS paymentStatus, BOOKING_STATUS bookingStatus, String vehicleID);

    List<Booking> getBookingsByUserId(String userID);

    List<Booking> getBookingsByBookingStatus(BOOKING_STATUS bookingStatus);

    List<Booking> getBookingsByUserIdAndBookingStatus(String userID, BOOKING_STATUS bookingStatus);

    List<Booking> getBookingsByUserIdAndPaymentStatus(String userID, PAYMENT_STATUS paymentStatus);

    List<Booking> getBookingsByVehicles(String vehicleID);

    Booking updateBooking(String bookingID, BookingDto dto);

    Booking addBooking(BookingDto dto);

    Booking cancelBooking(String bookingID);

    void deleteBooking(String bookingID);

    Long deleteBookings();

}
