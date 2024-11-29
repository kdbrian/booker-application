package io.github.junrdev.booker.repo.mongo;

import io.github.junrdev.booker.domain.model.Route;
import io.github.junrdev.booker.domain.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends MongoRepository<Route, String> {
    List<Route> findBySchedule(Schedule schedule);
}
