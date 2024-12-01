package io.github.junrdev.booker.domain.dto;

import io.github.junrdev.booker.domain.enumarations.PAYMENT_STATUS;
import io.github.junrdev.booker.domain.model.Booking;
import io.github.junrdev.booker.util.mappers.EntityToDtoMapper;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Builder
public class BookingDto {
    private String id;
    private String userId;
    private String vehicleId;
    private PAYMENT_STATUS paymentStatus;
}


