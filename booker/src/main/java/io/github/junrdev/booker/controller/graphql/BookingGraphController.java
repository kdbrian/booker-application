package io.github.junrdev.booker.controller.graphql;

import io.github.junrdev.booker.domain.dto.BookingDto;
import io.github.junrdev.booker.domain.enumarations.BOOKING_STATUS;
import io.github.junrdev.booker.domain.enumarations.PAYMENT_STATUS;
import io.github.junrdev.booker.domain.model.Booking;
import io.github.junrdev.booker.domain.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookingGraphController {

    private final BookingService bookingService;

    @Autowired
    public BookingGraphController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @MutationMapping
    public Booking addBooking(@Argument BookingDto bookingDto) {
        return bookingService.addBooking(bookingDto);
    }

    @MutationMapping
    public List<Booking> addBookings(List<BookingDto> dto) {
        return bookingService.addBookings(dto);
    }

    @MutationMapping
    public Booking updateBooking(@Argument String id, @Argument BookingDto bookingDto) {
        return bookingService.updateBooking(id, bookingDto);
    }

    @MutationMapping
    public Booking cancelBooking(@Argument String bookingId) {
        return bookingService.cancelBooking(bookingId);
    }

    @MutationMapping
    public Boolean deleteBooking(@Argument String id) {
        bookingService.deleteBooking(id);
        return true;
    }

    @MutationMapping
    public Boolean deleteBookings() {
        assert bookingService.deleteBookings() != -1;
        return true;
    }

    @QueryMapping
    public List<Booking> getBookings() {
        return bookingService.getBookings();
    }

    @QueryMapping
    public Booking getBookingWithId(@Argument String bookingId) {
        return bookingService.getBookingWithId(bookingId);
    }

    @QueryMapping
    public List<Booking> getBookingsByPaymentStatus(@Argument PAYMENT_STATUS paymentStatus) {
        return bookingService.getBookingsByPaymentStatus(paymentStatus);
    }

    @QueryMapping
    public List<Booking> getBookingsByUserId(@Argument String userId) {
        return bookingService.getBookingsByUserId(userId);
    }


    @QueryMapping
    public List<Booking> getBookingsByBookingStatus(@Argument BOOKING_STATUS bookingStatus) {
        return bookingService.getBookingsByBookingStatus(bookingStatus);
    }


    @QueryMapping
    public List<Booking> getBookingsByUserIdAndBookingStatus(@Argument String userID, @Argument BOOKING_STATUS bookingStatus) {
        return bookingService.getBookingsByUserIdAndBookingStatus(userID, bookingStatus);
    }

    @QueryMapping
    public List<Booking> getBookingsByUserIdAndPaymentStatus(@Argument String userID, @Argument PAYMENT_STATUS paymentStatus) {
        return bookingService.getBookingsByUserIdAndPaymentStatus(userID, paymentStatus);
    }

    @QueryMapping
    public List<Booking> getBookingsByVehicles(@Argument String vehicleID) {
        return bookingService.getBookingsByVehicles(vehicleID);
    }


}
