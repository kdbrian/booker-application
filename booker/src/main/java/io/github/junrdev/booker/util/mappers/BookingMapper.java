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
                .paymentStatus(bookingDto.getPaymentStatus().name())
                .build();
    }

    @Override
    public BookingDto toDto(Booking booking) {

        return BookingDto.builder()
                .id(booking.id())
                .userId(booking.userID())
                .paymentStatus(PAYMENT_STATUS.valueOf(booking.paymentStatus()))
                .vehicleId(booking.vehicle().getId())
                .build();
    }
}
