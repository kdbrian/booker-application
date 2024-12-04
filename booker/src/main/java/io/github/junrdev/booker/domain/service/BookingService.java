package io.github.junrdev.booker.domain.service;

import io.github.junrdev.booker.domain.dto.BookingDto;
import io.github.junrdev.booker.domain.enumarations.BOOKING_STATUS;
import io.github.junrdev.booker.domain.enumarations.PAYMENT_STATUS;
import io.github.junrdev.booker.domain.model.Booking;
import io.github.junrdev.booker.domain.model.Vehicle;
import io.github.junrdev.booker.util.error.AppError;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;

import java.util.List;

public interface BookingService {

    List<Booking> getBookings();

    Booking getBookingWithId(String bookingId);

    List<Booking> getBookingsByPaymentStatus(PAYMENT_STATUS paymentStatus);

    List<Booking> getBookingsByUserPaymentStatusAndBookingStatusAndVehicle(String user, PAYMENT_STATUS paymentStatus, BOOKING_STATUS bookingStatus, String vehicleID);

    List<Booking> getBookingsByUserId(String userID);

    List<Booking> getBookingsByBookingStatus(BOOKING_STATUS bookingStatus);

    List<Booking> getBookingsByUserIdAndBookingStatus(String userID, BOOKING_STATUS bookingStatus);

    List<Booking> getBookingsByUserIdAndPaymentStatus(String userID, PAYMENT_STATUS paymentStatus);

    List<Booking> getBookingsByVehicles(String vehicleID);

    Booking updateBooking(String bookingID, BookingDto dto);

    Booking addBooking(BookingDto dto);

    List<Booking> addBookings(List<BookingDto> dto);

    Booking cancelBooking(String bookingID);

    void deleteBooking(String bookingID);

    default void checkId(String bookingID) {
        if (!ObjectId.isValid(bookingID))
            throw new AppError("Invalid id : " + bookingID, HttpStatus.BAD_REQUEST);

    }

    Long deleteBookings();


}
