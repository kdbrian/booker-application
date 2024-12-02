package io.github.junrdev.booker.repo.mongo;

import io.github.junrdev.booker.domain.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {


}
