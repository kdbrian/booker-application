package io.github.junrdev.booker.domain.dto;

import io.github.junrdev.booker.domain.enumarations.BOOKING_STATUS;
import io.github.junrdev.booker.domain.enumarations.PAYMENT_STATUS;
import io.github.junrdev.booker.domain.model.Booking;
import io.github.junrdev.booker.util.mappers.EntityToDtoMapper;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Data
@Builder
public class BookingDto {

    private String id;

//    @NotBlank(message = "User id is required.")
    //TODO:update to pick from users ref
    @Builder.Default
    private String userId = UUID.randomUUID().toString();

//    @NotBlank(message = "Vehicle id is required")
    private String vehicleId;

    @Builder.Default
    private PAYMENT_STATUS paymentStatus = PAYMENT_STATUS.PENDING;

    @Builder.Default
    private BOOKING_STATUS bookingStatus = BOOKING_STATUS.PENDING;

    private String createdAt;
    private String updatedAt;

}


