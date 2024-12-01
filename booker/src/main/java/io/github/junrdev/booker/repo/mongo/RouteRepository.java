package io.github.junrdev.booker.repo.mongo;

import io.github.junrdev.booker.domain.model.Company;
import io.github.junrdev.booker.domain.model.Route;
import io.github.junrdev.booker.domain.model.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RouteRepository extends MongoRepository<Route, String> {
    List<Route> findBySchedule(Schedule schedule);

    List<Route> findByFrom(String from);
    List<Route> findByFromContaining(String from);
    List<Route> findByFromContainingAndToContaining(String from,String to);

     List<Route> findByTo(String to);
    List<Route> findByToContaining(String to);


}
