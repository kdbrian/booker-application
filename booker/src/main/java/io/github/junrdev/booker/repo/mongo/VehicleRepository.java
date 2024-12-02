package io.github.junrdev.booker.repo.mongo;

import io.github.junrdev.booker.domain.model.Route;
import io.github.junrdev.booker.domain.model.Vehicle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends MongoRepository<Vehicle, String> {
    List<Vehicle> findByRoute(Route route);

//    TODO: insert mongo query for loading using $gte and $lte and do same for price
    @Query("db.vehicle.find({ price : 999})")
    List<Vehicle> getVehiclesBySeatRange(int start, int end);

    List<Vehicle> findBySeatCount(int count);

    List<Vehicle> findByPrice(double price);

    Optional<Vehicle> findByIdentifier(String identifier);
}
