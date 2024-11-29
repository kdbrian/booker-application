package io.github.junrdev.booker.repo.mongo;

import io.github.junrdev.booker.domain.model.Route;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends MongoRepository<Route, String> {
}
