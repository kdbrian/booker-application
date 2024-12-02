package io.github.junrdev.booker.domain.model;

import io.github.junrdev.booker.domain.enumarations.BOOKING_STATUS;
import io.github.junrdev.booker.domain.enumarations.PAYMENT_STATUS;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookings")
@Builder
@Data
public class Booking{
        @Id private String id;

        @NotBlank(message = "User id cannot be null.")
        private String userID;//TODO: ADD user ref after adding auth

        @DBRef
        private Vehicle vehicle;
        private PAYMENT_STATUS paymentStatus;
        private BOOKING_STATUS bookingStatus;
        private String createdAt;
        private String updatedAt;
}

