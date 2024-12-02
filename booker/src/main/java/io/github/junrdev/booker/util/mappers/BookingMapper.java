package io.github.junrdev.booker.util.mappers;

import io.github.junrdev.booker.domain.dto.BookingDto;
import io.github.junrdev.booker.domain.enumarations.BOOKING_STATUS;
import io.github.junrdev.booker.domain.enumarations.PAYMENT_STATUS;
import io.github.junrdev.booker.domain.model.Booking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class BookingMapper extends EntityToDtoMapper<Booking, BookingDto> {

    @Override
    public Booking fromDto(BookingDto bookingDto) {
        return Booking.builder()
                .userID(bookingDto.getUserId()!=null?bookingDto.getUserId(): UUID.randomUUID().toString())
                .createdAt(bookingDto.getCreatedAt()!=null?bookingDto.getCreatedAt(): LocalDateTime.now().toString())
                .paymentStatus(bookingDto.getPaymentStatus()!=null?bookingDto.getPaymentStatus():PAYMENT_STATUS.PENDING)
                .bookingStatus(bookingDto.getBookingStatus()!=null?bookingDto.getBookingStatus(): BOOKING_STATUS.PENDING)
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
