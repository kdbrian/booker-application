package io.github.junrdev.booker.domain.model;

import io.github.junrdev.booker.domain.enumarations.PAYMENT_STATUS;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bookings")
@Builder
public record Booking(
        @Id String id,
        String userID,//TODO: ADD user ref after adding auth
        @DBRef Vehicle vehicle,
        String paymentStatus
) {
}

