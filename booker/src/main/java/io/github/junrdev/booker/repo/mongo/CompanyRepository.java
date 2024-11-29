package io.github.junrdev.booker.repo.mongo;

import io.github.junrdev.booker.domain.model.Company;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends MongoRepository<Company, String> {
    Optional<Company> findByName(String name);
    List<Company> findByLocation(String location);

}
