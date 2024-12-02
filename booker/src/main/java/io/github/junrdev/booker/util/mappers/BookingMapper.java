package io.github.junrdev.booker.util.mappers;

import io.github.junrdev.booker.domain.dto.BookingDto;
import io.github.junrdev.booker.domain.enumarations.PAYMENT_STATUS;
import io.github.junrdev.booker.domain.model.Booking;
import org.springframework.stereotype.Service;

@Service
public class BookingMapper extends EntityToDtoMapper<Booking, BookingDto> {

    @Override
    public Booking fromDto(BookingDto bookingDto) {
        return Booking.builder()
                .userID(bookingDto.getUserId())
                .paymentStatus(bookingDto.getPaymentStatus())
                .createdAt(bookingDto.getCreatedAt())
                .bookingStatus(bookingDto.getBookingStatus())
                .build();
    }

    @Override
    public BookingDto toDto(Booking booking) {

        return BookingDto.builder()
                .id(booking.getId())
                .userId(booking.getUserID())
                .createdAt(booking.getCreatedAt())
                .paymentStatus(booking.getPaymentStatus())
                .vehicleId(booking.getVehicle().getId())
                .bookingStatus(booking.getBookingStatus())
                .build();
    }
}
