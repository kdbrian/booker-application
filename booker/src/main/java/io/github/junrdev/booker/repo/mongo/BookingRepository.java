package io.github.junrdev.booker.repo.mongo;

import io.github.junrdev.booker.domain.enumarations.PAYMENT_STATUS;
import io.github.junrdev.booker.domain.model.Booking;
import io.github.junrdev.booker.domain.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {

    List<Booking> findByUserID(String userID);

    List<Booking> findByPaymentStatus(PAYMENT_STATUS paymentStatus);

    List<Booking> findByVehicle(Vehicle vehicle);

}
